package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;

public class SecondActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private ListView wifiList;
    List<ScanResult> resultPrev;
    List<ScanResult> resultList;
    WifiReceiver wifiReceiver;

    boolean boolPredict =  false;
    boolean def = false;
    AppDatabase dbRoom;
    AppDatabase dbWar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        dbRoom = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_room").allowMainThreadQueries().build();
        dbWar = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database_war").allowMainThreadQueries().build();

        EditText textRoom = (EditText) findViewById(R.id.textRoom);
        Button btnWar = findViewById(R.id.btnStartWar);
        Button btnPredict = findViewById(R.id.btnPredict);
        //Button btnUpdate = findViewById(R.id.btnGetWifi);
        Button btnWarCont = findViewById(R.id.btnStartWar2);
        TextView result1 = (TextView) findViewById(R.id.result1);
        TextView result2 = (TextView) findViewById(R.id.result2);

        // Working with wifiManager
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.startScan();

        btnWar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                warDrive();
            }
        });

        btnWarCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!def) {
                    def = true;
                    btnWarCont.setText("Stop saving War Drive Data Continously");
                    warDrive();
                }
                else {
                    def = false;
                    btnWarCont.setText("Save War Drive Data Continously");
                }
            }
        });

        /*btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result1.setText("Processing");
                wifiManager.startScan();

                //result1.setText("Scan Done. Wifi List Updated. Ready to Predict");

            }
        });*/

        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolPredict = true;
                Log.d("PredictButton", "Before startscan");
                result1.setText("Processing");
                result2.setText("Processing");
                wifiManager.startScan();

                Toast.makeText(getApplicationContext(), "Started scanning and predicting", Toast.LENGTH_SHORT).show();
                Log.d("PredictButton", "After startscan");
            }
        });
    }

    public  void warDrive() {
        EditText textRoom = (EditText) findViewById(R.id.textRoom);
        Button btnWarCont = findViewById(R.id.btnStartWar2);
        String roomName = textRoom.getText().toString();
        wifiManager.startScan();

        List<DataRoom> listRoom = dbRoom.userDao().getAllRooms();
        boolean flag = false;

        if (roomName.equals("")){
            Toast.makeText(getApplicationContext(), "Enter Room Name. Don't keep it Empty", Toast.LENGTH_SHORT).show();
            def = false;
            btnWarCont.setText("Save War Drive Data Continously");
        }
        else {
            for (DataRoom dataRoom : listRoom) {
                if (dataRoom.roomName.equals(roomName)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                dbRoom.userDao().insert_room(new DataRoom(roomName));
            }

            for (ScanResult scanResult : resultList) {
                //if (!scanResult.SSID.equals("")) {
                if (scanResult.SSID.equals("knowledge-Fi 5gh") || scanResult.SSID.equals("Knowledge-Fi")) {
                    dbWar.userDao().insert_war(new DataWardrive(scanResult.level, scanResult.SSID, roomName));
                }
            }
            Toast.makeText(getApplicationContext(), "Scan Completed and Data added to Database", Toast.LENGTH_SHORT).show();
        }
    }

    public void predict(){
        TextView result2 = (TextView) findViewById(R.id.result2);
        String TAG = "PredictButton";
        List<ScanResult> currentList = resultList;
        Log.d("PredictButton", "AfterList");

        List<DataRoom> listRoom = dbRoom.userDao().getAllRooms();
        List<DataWardrive> listWar = dbWar.userDao().getWarData();
        Log.d(TAG, "AfterDB: "+listRoom.size() + " " +listWar.size());

        //Log.d(TAG, "listWar" + listWar.get(0) + "," + listWar.get(7));
        Collections.sort(listWar, new Comparator<DataWardrive>() {
            @Override
            public int compare(DataWardrive dataWardrive, DataWardrive t1) {
                return dataWardrive.getData_roomLabel().compareTo(t1.getData_roomLabel());
            }
        });
        //Log.d(TAG, "listWar" + listWar.get(0) + "," + listWar.get(7));

        //Log.d("PredictButton", "After First Sort");

        //Log.d(TAG, "listRoom" + listRoom.get(0) + "," + listRoom.get(2));
        Collections.sort(listRoom, new Comparator<DataRoom>() {
            @Override
            public int compare(DataRoom dataRoom, DataRoom t1) {
                return dataRoom.getRoomName().compareTo(t1.getRoomName());
            }
        });
        //Log.d(TAG, "listRoom" + listRoom.get(0) + "," + listRoom.get(2));

        //Log.d("PredictButton", "AfterSecondSort");

        List<ListData> temp = new ArrayList<>();
        for (DataRoom dataRoom : listRoom) {
            float sum = 0;
            int repeat = 0;

            for (DataWardrive dataWardrive : listWar) {

                Log.d(TAG, "roomdta " + dataRoom.roomName + ", roomwar " + dataWardrive.data_roomLabel);
                if (dataRoom.roomName.equals(dataWardrive.data_roomLabel)) {
                    Log.d(TAG, "roomwar " + dataWardrive.data_roomLabel);


                    for (ScanResult scan : currentList) {
                        //Log.d(TAG, "ssid"+ scan.SSID + dataWardrive.data_ssid);
                        if ((scan.SSID.equals("knowledge-Fi 5gh") || scan.SSID.equals("Knowledge-Fi")) && scan.SSID.equals(dataWardrive.data_ssid)) {
                            sum += distance(Math.abs(scan.level), Math.abs(dataWardrive.data_rssi));
                            repeat++;
                            Log.d(TAG, "room " + dataWardrive.data_roomLabel + " level " + scan.level + " rssi " + dataWardrive.data_rssi + " sum " + sum);
                        }
                    }
                }
            }

            temp.add(new ListData(dataRoom.roomName, (sum/repeat)));
            Log.d(TAG, "sumFinal " + sum/repeat);

        }

        Log.d("PredictButton", "Before 3d sort" + temp.size());
        Collections.sort(temp, new Comparator<ListData>() {
            @Override
            public int compare(ListData listData, ListData t1) {
                //Log.d("PredictButton", "Before Retutn");
                return Float.compare(listData.getValue(), t1.getValue());
//                        Log.d("PredictButton", "After Return");
            }
        });

        Log.d("PredictButton", "After 3rd sort");
        // Using k as 5 in knn
        List<String> knnList = new ArrayList<>();
        int i = 0;
        for (ListData listData : temp) {
            if (i < 5) {
                knnList.add(listData.label);
                Log.d("PredictButton", listData.label+ ": " + listData.value);
                i++;
            }
            else {
                break;
            }
        }

        Log.d("PredictButton", "Before First Result");
        if (temp.size() == 0) {
            Log.d("PredictionButton", "onClick: " + temp.size());
        }

                /*result1.setText("Current Location finding Simply: " + knnList.get(0) );
                Log.d("PredictButton", "After First Result");
                Log.d("PredictButton", knnList.get(0));*/

        int[] array1  = new int[5];
        int max = 0;
        int index = 0;

        for (String s1 : knnList) {
            max = 0;
            for (String s2 : knnList) {
                if (s1.equals(s2)) {
                    max++;
                }
            }
            array1[index] = max;
            Log.d(TAG, "max: " + max);
            index++;
        }

        max = 0;
        for (int j = 0; j < array1.length; j++) {
            if (array1[j] > array1[max]) {
                Log.d(TAG, "onClick: "+ j + array1[j]);
                max = j;
            }
        }

        Log.d("PredictButton", "before second result");
        //+knnList.get(max)
        //result1.setText("Processed");
        result2.setText("Current location: " + knnList.get(max));
        Log.d("PredictButton", "After second result");
    }

    public float distance(float x, float y) {
        float z = Math.abs(x - y);
        //Log.d("PredictButton", "Dist fun x" + x + "y" + y + "z" + z);
        return z;
    }

    public void trigger() {
        TextView result1 = (TextView) findViewById(R.id.result1);
        //result1.setText("Scan Done. Wifi List Updated. Ready to Predict");
        if (def) {
            warDrive();
        }
        if (boolPredict) {
            result1.setText("Scan Done. Wifi List Updated. Predicting");
            predict();
            boolPredict = false;
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        wifiReceiver = new WifiReceiver(wifiManager, wifiList);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiReceiver, intentFilter);
        wifiManager.startScan();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(wifiReceiver);
    }

    class WifiReceiver extends BroadcastReceiver {
        WifiManager wifiManager;
        ListView wifiDeviceList;

        public WifiReceiver(WifiManager wifiManager, ListView wifiDeviceList) {
            this.wifiManager = wifiManager;
            this.wifiDeviceList = wifiDeviceList;
        }

        public void onReceive(Context context, Intent intent) {
            resultList = wifiManager.getScanResults();
            Toast.makeText(getApplicationContext(), "Scanning Done", Toast.LENGTH_SHORT).show();
            Log.d("PredictButton", "After gettingList");
            trigger();

        }
    }
}