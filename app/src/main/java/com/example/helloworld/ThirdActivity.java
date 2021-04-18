package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity implements SensorEventListener, LocationListener {

    private SensorManager sensorManager;
    public LocationManager locationManager;
    private Sensor acc;
    private Sensor gyro;
    private Sensor magnet;
//    private Sensor step;

    Button btnSensor;
    TextView sensortext;
    TextView stepResult;
    TextView distance;

    boolean senStat = false;
    boolean first = true;
    int stepCount = 0;
    final float stride_length = (float) 0.7366; //29 inch
    float acc_x;
    float acc_y;
    float acc_z;
    float totalAcc;
    float prevAcc = 0f;
    float diffAcc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //view variables
        btnSensor = findViewById(R.id.btnSensor);
        sensortext = findViewById(R.id.textImu);
        stepResult = findViewById(R.id.textStepResult);
        distance = findViewById(R.id.textDistanceResult);

        //Sensor Manager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Database initialization
        AppDatabase dbAcc = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_acc").allowMainThreadQueries().build();
        AppDatabase dbGyro = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_gyro").allowMainThreadQueries().build();
        AppDatabase dbMagnet = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_magnet").allowMainThreadQueries().build();
        AppDatabase dbStep = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_step").allowMainThreadQueries().build();

        btnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!senStat) {
                    senStat = true;
                    sensortext.setText("ON");
                    btnSensor.setText("Stop IMU Sensors");

                    stepCount = 0;
                    stepResult.setText(String.valueOf(stepCount));

                    acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    sensorManager.registerListener(ThirdActivity.this, acc, sensorManager.SENSOR_DELAY_NORMAL);

                    /*gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                    sensorManager.registerListener(ThirdActivity.this, gyro, sensorManager.SENSOR_DELAY_NORMAL);

                    magnet = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                    sensorManager.registerListener(ThirdActivity.this, magnet, sensorManager.SENSOR_DELAY_NORMAL);*/

                    /*step = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                    sensorManager.registerListener(ThirdActivity.this, step, sensorManager.SENSOR_DELAY_NORMAL);*/
                }

                else {
                    senStat = false;
                    btnSensor.setText("Start IMU Sensors");
                    sensortext.setText("OFF");
                    sensorManager.unregisterListener(ThirdActivity.this);
                }

            }
        });


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if (sensor.getType() == sensor.TYPE_ACCELEROMETER) {
            /*AppDatabase dbAcc1 = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_acc1").allowMainThreadQueries().build();
            DataAcc dataAcc = new DataAcc(sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            dbAcc1.userDao().insert_acc(dataAcc);*/

            acc_x = sensorEvent.values[0];
            acc_y = sensorEvent.values[1];
            acc_z = sensorEvent.values[2];

            totalAcc = (float) Math.sqrt(acc_x*acc_x + acc_y*acc_y + acc_z*acc_z);
            diffAcc = totalAcc - prevAcc;
            prevAcc = totalAcc;

            if (!first && diffAcc > 6) {
                stepCount++;
            }
            else {
                first = false;
            }

            stepResult.setText(String.valueOf(stepCount));
            distance.setText(String.valueOf(stepCount*stride_length) + " m");

        }

/*        if (sensor.getType() == sensor.TYPE_GYROSCOPE) {
            AppDatabase dbGyro1 = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_gyro1").allowMainThreadQueries().build();
            DataGyro dataGyro = new DataGyro(sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            dbGyro1.userDao().insert_gyro(dataGyro);
        }

        if (sensor.getType() == sensor.TYPE_MAGNETIC_FIELD) {
            AppDatabase dbMagnet1 = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_magnet1").allowMainThreadQueries().build();
            DataMagneto dataMagneto = new DataMagneto(sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
            dbMagnet1.userDao().insert_magnet(dataMagneto);
        }*/

        /*if (sensor.getType() == sensor.TYPE_STEP_COUNTER) {
            AppDatabase dbStep1 = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_step1").allowMainThreadQueries().build();
            DataStep dataStep = new DataStep(sensorEvent.timestamp, sensorEvent.values[0]);
            dbStep1.userDao().insert_step(dataStep);
            stepCount += (int) sensorEvent.values[0];
            stepResult.setText(stepCount);
        }*/

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}