package com.example.helloworld;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;


public class PlayerService extends Service {
    MediaPlayer myPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        myPlayer = MediaPlayer.create(this, R.raw.sawre);
        myPlayer.setLooping(false);
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        myPlayer.start();
        Toast.makeText(this, "Service Started, Music started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        myPlayer.stop();
        Toast.makeText(this, "Service Stopped, Music Stopped", Toast.LENGTH_SHORT).show();
    }


}
