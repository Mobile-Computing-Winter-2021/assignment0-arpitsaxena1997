package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    //MediaPlayer myPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_BATTERY_LOW));
        registerReceiver(new ChargerReceiver(), new IntentFilter(Intent.ACTION_BATTERY_OKAY));
    }

    public void startService(View v) {
        Intent intent = new Intent(this, PlayerService.class);
        startService(intent);
    }

    //public void pause(View v) { }

    public void stopService(View v) {
        Intent intent = new Intent(this, PlayerService.class);
        stopService(intent);
    }

    public void activity2(View v) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        startActivityForResult(intent, 0);
    }

    /*public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            Toast.makeText(context, "Action: " + intent.getAction(), Toast.LENGTH_SHORT).show();
        }
    }*/

}