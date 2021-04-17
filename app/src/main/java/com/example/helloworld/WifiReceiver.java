package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WifiReceiver extends BroadcastReceiver {
    WifiManager wifiManager;
    ListView receiverWifiList;

    public WifiReceiver(WifiManager wifiManager, ListView receiverWifiList) {
        this.wifiManager = wifiManager;
        this.receiverWifiList = receiverWifiList;
    }

    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();

        if (WifiManager.SCAN_RESULTS_AVAILABLE_ACTION.equals(action)) {
            List<ScanResult> resultList = wifiManager.getScanResults();
            ArrayList<String> apList = new ArrayList<>();

            for(ScanResult scanResult :resultList) {
                apList.add(scanResult.SSID + ", RSSI: " + scanResult.level);
            }

            Toast.makeText(context, "Scan Completed", Toast.LENGTH_SHORT).show();
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_expandable_list_item_1, apList.toArray());
            receiverWifiList.setAdapter(arrayAdapter);
        }
    }
}
