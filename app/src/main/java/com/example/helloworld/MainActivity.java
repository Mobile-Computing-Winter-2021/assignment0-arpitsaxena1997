package com.example.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.FloatMath;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {
    public final String TAG = "MainActivity";

    //View variables
    Switch switchAcc;
    Switch switchLinearAcc;
    Switch switchTemp;
    Switch switchLight;
    Switch switchGPS;
    Switch switchProximity;
    Button btnAvgAcc;
    Button btnAvgTemp;
    Button btnMotion;
    TextView motionStatus;
    TextView outputAll;
    TextView avgTemp;
    TextView avgAcc;
    TextView outputHeading;

    //Sensors
    private SensorManager sensorManager;
    public LocationManager locationManager;
    private Sensor acc;
    private Sensor linAcc;
    private Sensor temp;
    private Sensor light;
    private Sensor gps;
    private Sensor proximity;
    private LocationListener listener;

    AppDatabase dbAcc1;
    AppDatabase dbLight;

    int entries_in_hour = 1000000 / 200000 * 60 * 60;

    // For MotionDetection
    private boolean motion = false;
    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase dbAcc1 = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_acc").allowMainThreadQueries().build();
        AppDatabase dbLight = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_light").allowMainThreadQueries().build();

        //Assigning view variables
        switchAcc = findViewById(R.id.switchAccelerometer);
        switchLinearAcc = findViewById(R.id.switchLinearAcceleration);
        switchTemp = findViewById(R.id.switchTemperature);
        switchLight = findViewById(R.id.switchLight);
        switchGPS = findViewById(R.id.switchGPS);
        switchProximity = findViewById(R.id.switchProximity);
        btnAvgAcc = findViewById(R.id.buttonAvgAcc);
        btnAvgTemp = findViewById(R.id.buttonAvgTemp);
        btnMotion = findViewById(R.id.buttonMotion);
        motionStatus = findViewById(R.id.motionStatus);
        outputAll = findViewById(R.id.outputSensor);
        avgTemp = findViewById(R.id.outputTemp);
        avgAcc = findViewById(R.id.outputAcc);
        outputHeading = findViewById(R.id.outputSensor3);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // For MotionDetection
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

        btnAvgAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AppDatabase dbAcc = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_Acc").allowMainThreadQueries().build();
                List<DataAcc> listAcc = dbAcc1.userDao().getAccData();

                int length = listAcc.size();

                float sumX = 0;
                float sumY = 0;
                float sumZ = 0;
                float avg_X;
                float avg_Y;
                float avg_Z;

                if (length < entries_in_hour) {

                    for (int i = 0; i < length; i++) {
                        sumX += listAcc.get(i).getData_acc_0();
                        sumY += listAcc.get(i).getData_acc_1();
                        sumZ += listAcc.get(i).getData_acc_2();
                    }
                } else {
                    for (int i = length - 1; i > length - entries_in_hour; i--) {
                        sumX += listAcc.get(i).getData_acc_0();
                        sumY += listAcc.get(i).getData_acc_1();
                        sumZ += listAcc.get(i).getData_acc_2();
                        if (i == 1) {
                            avgAcc.setText(Float.toString(sumX));
                            Toast.makeText(getApplicationContext(), Float.toString(sumX), Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                avg_X = (sumX / (float) length);
                avg_Y = sumY / (float) length;
                avg_Z = sumZ / (float) length;

                //Toast.makeText(getApplicationContext(), (int) sumX, Toast.LENGTH_SHORT).show();

                avgAcc.setText("Average value of Accelerometer sensor for last 1 hour are\nFor X coordinate: " + Float.toString(avg_X) + "\nFor Y coordinate:" + Float.toString(avg_Y) + "\nFor Z coordinate:" + Float.toString(avg_Z));
            }
        });

        btnAvgTemp.setOnClickListener(new View.OnClickListener() {
            //instead of temp taking avg of light sensor
            @Override
            public void onClick(View view) {
                //AppDatabase dbLight = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_linAcc").allowMainThreadQueries().build();
                List<DataLight> listLight = dbLight.userDao().getLightData();

                int length = listLight.size();
                float sumX = 0;
                float avg_X;

                if (length < entries_in_hour) {

                    for (int i = 0; i < length; i++) {
                        sumX += listLight.get(i).getData_light_0();
                    }
                } else {
                    for (int i = length - 1; i > length - entries_in_hour; i--) {
                        sumX += listLight.get(i).getData_light_0();
                    }

                }

                avg_X = sumX / (float) length;
                avgTemp.setText("Average value of Light Sensor for last1  hour is :" + Float.toString(avg_X));


            }
        });

        btnMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (motion) {
                    motion = false;
                    Toast.makeText(getApplicationContext(), "Motion Detection Disabled", Toast.LENGTH_SHORT).show();
                    motionStatus.setText("Motion Status Disabled");
                }
                else {
                    motion = true;
                    Toast.makeText(getApplicationContext(), "Motion Detection Enabled", Toast.LENGTH_SHORT).show();
                    motionStatus.setText("Motion Status Enabled");
                }

            }
        });

        switchAcc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                    outputHeading.setVisibility(View.VISIBLE);
                    acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    sensorManager.registerListener(MainActivity.this, acc, sensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(getApplicationContext(), "Started Saving Data of Accelerometer Data", Toast.LENGTH_SHORT).show();
                } else {
                    sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of Accelerometer Data", Toast.LENGTH_SHORT).show();
                }

            }
        });

        switchLinearAcc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    outputHeading.setVisibility(View.VISIBLE);
                    linAcc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
                    sensorManager.registerListener(MainActivity.this, linAcc, sensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(getApplicationContext(), "Started Saving Data of Linear Accelerometer Data", Toast.LENGTH_SHORT).show();
                } else {
                    sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of Linear Accelerometer Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchTemp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    outputHeading.setVisibility(View.VISIBLE);
                    temp = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                    sensorManager.registerListener(MainActivity.this, temp, sensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(getApplicationContext(), "Started Saving Data of Gyroscope Data", Toast.LENGTH_SHORT).show();
                } else {
                    sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of Gyroscope Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    outputHeading.setVisibility(View.VISIBLE);
                    light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                    sensorManager.registerListener(MainActivity.this, light, sensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(getApplicationContext(), "Started Saving Data of Light Data", Toast.LENGTH_SHORT).show();
                } else {
                    sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of Light Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*switchGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {

                    //outputHeading.setVisibility(View.VISIBLE);
                    //public LocationListener locationListener = new LocationListener();

                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 67);
                    }

                    Log.d("switch", "before");
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, MainActivity.this);
                    Log.d("switch", "after");
                    //Toast.makeText(getApplicationContext(), "Started Saving Data of GPS Data", Toast.LENGTH_SHORT).show();
                }

                else {
                    //sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of GPS Data", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        switchGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchGPS.isChecked()) {
                    outputHeading.setVisibility(View.VISIBLE);
                    outputAll.setText("Fetching GPS Coordinates");
                    Boolean gpsenabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                    if (gpsenabled) {

                        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},90);
                            return;
                        }
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, MainActivity.this);

                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Gps Not Enabled",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    locationManager.removeUpdates(MainActivity.this);
                }
            }
        });

        switchProximity.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    outputHeading.setVisibility(View.VISIBLE);
                    proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                    sensorManager.registerListener(MainActivity.this, proximity, sensorManager.SENSOR_DELAY_NORMAL);
                    Toast.makeText(getApplicationContext(), "Started Saving Data of Proximity Data", Toast.LENGTH_SHORT).show();
                }

                else {
                    sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of Proximity Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == sensor.TYPE_ACCELEROMETER) {
            dbAcc1 = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_acc").allowMainThreadQueries().build();
            DataAcc dataAcc = new DataAcc(sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            dbAcc1.userDao().insert_acc(dataAcc);
            outputAll.setText("Output of Accelerometer Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);
           // outputAll.setText((int) ((new Date()).getTime() + (sensorEvent.timestamp - System.nanoTime())/ 1000000L));

            //Motion Detection
            if (motion) {
                mGravity = sensorEvent.values.clone();

                float x = mGravity[0];
                float y = mGravity[1];
                float z = mGravity[2];

                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt(x*x + y*y + z*z);
                float delta = mAccelCurrent - mAccelLast;
                mAccel = mAccel * 0.9f + delta;
                if (mAccel > 1) {
                    motionStatus.setText("In Motion");
                }
                else {
                    motionStatus.setText("Stationary");
                }

            }


        }

        if (sensor.getType() == sensor.TYPE_LINEAR_ACCELERATION) {
            AppDatabase dbLinAcc = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_linAcc").allowMainThreadQueries().build();
            DataLinAcc dataLinAcc = new DataLinAcc(sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            dbLinAcc.userDao().insert_linAcc(dataLinAcc);
            outputAll.setText("Output of Linear Acceleration Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);
        }

        if (sensor.getType() == sensor.TYPE_GYROSCOPE) {
            AppDatabase dbGyro = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_gyro").allowMainThreadQueries().build();
            DataGyro dataGyro = new DataGyro(sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            dbGyro.userDao().insert_gyro(dataGyro);
            outputAll.setText("Output of Gyroscope Sensor\nGyroScope used because Temperature sensor was not available\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);
        }

        if (sensor.getType() == sensor.TYPE_LIGHT) {
            dbLight = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_light").allowMainThreadQueries().build();
            DataLight dataLight = new DataLight(sensorEvent.timestamp, sensorEvent.values[0]);
            dbLight.userDao().insert_light(dataLight);
            outputAll.setText("Output of Light Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]);
        }


        if (sensor.getType() == sensor.TYPE_PROXIMITY) {
            AppDatabase dbProximity = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_proximity").allowMainThreadQueries().build();
            DataProximity dataProximity = new DataProximity(sensorEvent.timestamp, sensorEvent.values[0]);
            dbProximity.userDao().insert_proximity(dataProximity);
            outputAll.setText("Output of Proximity Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]);
        }



    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int i) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

        AppDatabase dbGPS = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_gps").allowMainThreadQueries().build();
        DataGPS dataGPS = new DataGPS(location.getLatitude(), location.getLongitude());
        dbGPS.userDao().insert_gps(dataGPS);
        Log.d("change", "before");
        outputAll.setText("Output of GPS Sensor\nTimestamp: "+"\n"+"\nLattitude: "+location.getLatitude()+"\nLongitude: "+ location.getLongitude());
        Log.d("change", "after");
    }
}


