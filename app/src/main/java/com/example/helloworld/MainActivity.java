package com.example.helloworld;


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
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final String TAG = "MainActivity";

    private WifiManager wifiManager;
    private ListView wifiList;
    List<ScanResult> resultList;
    WifiReceiver wifiReceiver;

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntries;
    ArrayList<String> labelEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing view variables and objects
        Button btnScan = findViewById(R.id.btnScan);
        Button btnChart = findViewById(R.id.btnChart);
        Button btnList = findViewById(R.id.btnList);
        Button btnWardrive = findViewById(R.id.btnWardrive);
        wifiList = findViewById(R.id.wifiList);
        barChart = findViewById(R.id.barChart);

        // Working with wifiManager
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            Toast.makeText(this, "Wifi Disabled. Turing on Wifi", Toast.LENGTH_SHORT).show();
            wifiManager.setWifiEnabled(true);
        }

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiManager.startScan();
                Toast.makeText(getApplicationContext(), "Scanning Wifi", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barChart.setVisibility(View.INVISIBLE);
                wifiList.setVisibility(View.VISIBLE);
                //wifiManager.startScan();
                //Toast.makeText(getApplicationContext(), "Scanning Wifi", Toast.LENGTH_SHORT).show();
            }
        });

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiList.setVisibility(View.INVISIBLE);
                barChart.setVisibility(View.VISIBLE);
                //wifiManager.startScan();
                //Toast.makeText(getApplicationContext(), "Scanning Wifi", Toast.LENGTH_SHORT).show();
            }
        });

        btnWardrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSecond = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intentSecond, 2);
            }
        });
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


  /*  public List<ScanResult> returnScannedList(){
        wifiManager.startScan();
        Toast.makeText(getApplicationContext(), "Scanning Wifi", Toast.LENGTH_SHORT).show();
        return (resultList);
    }*/

    public class WifiReceiver extends BroadcastReceiver {
        WifiManager wifiManager;
        ListView receiverWifiList;

        public WifiReceiver(WifiManager wifiManager, ListView receiverWifiList) {
            this.wifiManager = wifiManager;
            this.receiverWifiList = receiverWifiList;
        }

        public void onReceive(Context context, Intent intent){
            resultList = wifiManager.getScanResults();
            ArrayList<String> apList = new ArrayList<>();

            barEntries = new ArrayList<>();
            labelEntries = new ArrayList<String>();
            float i = 1f;

            for(ScanResult scanResult :resultList) {
                apList.add((int)i + ". " + scanResult.SSID + ", RSSI: " + scanResult.level);
                barEntries.add(new BarEntry(i, scanResult.level));
                i += 1f;
                labelEntries.add(scanResult.SSID);

            }

            Toast.makeText(context, "Scan Completed", Toast.LENGTH_SHORT).show();
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, apList.toArray());
            receiverWifiList.setAdapter(arrayAdapter);

            barDataSet = new BarDataSet(barEntries, "Strength of RSSI for each Access Point");
            barData = new BarData( barDataSet);

            barChart.setData(barData);

            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

            XAxis xAxis = barChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(labelEntries));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
//            xAxis.setDrawGridLines(false);
//            xAxis.setDrawAxisLine(false);
            xAxis.setGranularity(1f);
            xAxis.setLabelRotationAngle(270);

            //xAxis.setLabelCount(labelEntries.size());
            barChart.animateY(2000);
            barChart.setFitBars(true);

        }
    }
}




