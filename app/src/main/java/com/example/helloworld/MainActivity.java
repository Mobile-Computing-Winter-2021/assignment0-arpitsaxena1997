package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    public final String TAG="Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_BATTERY_LOW));
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_BATTERY_OKAY));

        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_container);
        if(frag==null){
            Log.i(TAG,"fragment is  null");
            frag = new BlankFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container,frag)
                    .commit();
        }
        Log.i(TAG,"after inflating");

    }

}