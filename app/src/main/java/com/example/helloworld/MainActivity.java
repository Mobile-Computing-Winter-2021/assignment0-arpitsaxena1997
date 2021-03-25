package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener {
    public final String TAG="MainActivity";

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
    private LocationManager locationManager;
    private Sensor acc;
    private Sensor linAcc;
    private Sensor temp;
    private Sensor light;
    private Sensor gps;
    private Sensor proximity;
    private LocationListener listener;


    List<Data> dataList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        btnAvgAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnAvgTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnMotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                }

                else {
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
                }

                else {
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
                }

                else {
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
                }

                else {
                    sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of Light Data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    outputHeading.setVisibility(View.VISIBLE);
                    //public LocationListener locationListener = new LocationListener();

                    //gps = locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListenerNetwork);
                    Toast.makeText(getApplicationContext(), "Started Saving Data of GPS Data", Toast.LENGTH_SHORT).show();
                }

                else {
                    sensorManager.unregisterListener(MainActivity.this);
                    Toast.makeText(getApplicationContext(), "Stopped Saving Data of GPS Data", Toast.LENGTH_SHORT).show();
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
            outputAll.setText("Output of Accelerometer Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);
        }

        if (sensor.getType() == sensor.TYPE_LINEAR_ACCELERATION) {
            outputAll.setText("Output of Linear Acceleration Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);
        }

        if (sensor.getType() == sensor.TYPE_GYROSCOPE) {
            outputAll.setText("Output of Gyroscope Sensor\nGyroScope used because Temperature sensor was not available\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);
        }

        if (sensor.getType() == sensor.TYPE_LIGHT) {
            outputAll.setText("Output of Light Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]);
        }

      /*  if (sensor.getType() == sensor.TYPE_ACCELEROMETER) {
            outputAll.setText("Output of Accelerometer Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]+"\n"+sensorEvent.values[1]+"\n"+sensorEvent.values[2]);
        }*/

        if (sensor.getType() == sensor.TYPE_PROXIMITY) {
            outputAll.setText("Output of Proximity Sensor\nTimestamp: "+sensorEvent.timestamp+"\n"+sensorEvent.values[0]);
        }



    }

    @Override
    public void onAccuracyChanged(android.hardware.Sensor sensor, int i) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        outputAll.setText("Output of GPS Sensor\nTimestamp: "+location.getTime()+"\n"+"\nLattitude: "+location.getLatitude()+"\nLongitude: "+ location.getLongitude());

    }
}


