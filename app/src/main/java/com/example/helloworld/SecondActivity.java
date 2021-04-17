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
    List<ScanResult> resultList;
    WifiReceiver wifiReceiver;

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
        TextView result1 = (TextView) findViewById(R.id.result1);
        TextView result2 = (TextView) findViewById(R.id.result2);

        // Working with wifiManager
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        btnWar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomName = textRoom.getText().toString();
                wifiManager.startScan();

                List<DataRoom> listRoom = dbRoom.userDao().getAllRooms();
                boolean flag = false;

                if (roomName.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Room Name. Don't keep it Empty", Toast.LENGTH_SHORT).show();
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
                        if (!scanResult.SSID.equals("")) {
                            dbWar.userDao().insert_war(new DataWardrive(scanResult.level, scanResult.SSID, roomName));
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Scan Completed and Data added to Database", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiManager.startScan();
                result1.setText("Processing");
                Toast.makeText(getApplicationContext(), "Started scanning and predicting", Toast.LENGTH_SHORT).show();

                List<ScanResult> currentList = resultList;

                List<DataRoom> listRoom = dbRoom.userDao().getAllRooms();
                List<DataWardrive> listWar = dbRoom.userDao().getWardriveData();

                Collections.sort(listWar, new Comparator<DataWardrive>() {
                    @Override
                    public int compare(DataWardrive dataWardrive, DataWardrive t1) {
                        return dataWardrive.getData_roomLabel().compareTo(t1.getData_roomLabel());
                    }
                });

                Collections.sort(listRoom, new Comparator<DataRoom>() {
                    @Override
                    public int compare(DataRoom dataRoom, DataRoom t1) {
                        return dataRoom.getRoomName().compareTo(t1.getRoomName());
                    }
                });

                List<ListData> temp = new ArrayList<ListData>();
                for (DataRoom dataRoom : listRoom) {
                    float sum = 0;
                    for (DataWardrive dataWardrive : listWar) {
                        if (dataRoom.roomName == dataWardrive.data_roomLabel){

                            for (ScanResult scanResult : currentList) {
                                if (scanResult.SSID == dataRoom.roomName) {
                                    sum += distance(abs(scanResult.level), abs(dataWardrive.data_rssi));
                                }
                            }
                        }
                        else {
                            temp.add(new ListData(dataRoom.roomName, sum));
                            break;
                        }

                    }

                }

                Collections.sort(temp, new Comparator<ListData>() {
                    @Override
                    public int compare(ListData listData, ListData t1) {
                        return Float.compare(listData.getValue(), t1.getValue());
                    }
                });

                // Using k as 5 in knn
                List<String> knnList = new ArrayList<>();
                int i = 0;
                for (ListData listData : temp) {
                    if (i < 5) {
                        knnList.add(listData.label);
                        i++;
                    }
                    else {
                        break;
                    }
                }

                result1.setText("Current location Normally: "+knnList.get(0));

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
                    index++;
                }

                max = 0;
                for (int j = 0; j < array1.length; j++) {
                    if (array1[i] > array1[max]) {
                        max = i;
                    }
                }

                result2.setText("Current location using K-NN at k==5: "+knnList.get(0));

            }
        });



    }

    public float distance(float x, float y) {
        return (abs(x - y));

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

        }
    }
}