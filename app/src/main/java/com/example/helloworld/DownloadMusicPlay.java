package com.example.helloworld;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;

public class DownloadMusicPlay extends Service
{
 @Nullable
 @Override
 public IBinder onBind(Intent intent)
 {
  return null;
 }

 
 public DownloadMusicPlay()
 {
 
 }
 
 MediaPlayer myPlayer;
 
 @Override
 public void onCreate()
 {
   String tempFile = Environment.getExternalStorageDirectory() + "/arpit/MC.mp3";
   File file = new File(tempFile);

  Uri uri = Uri.fromFile(file);

  myPlayer = MediaPlayer.create(getApplicationContext(), uri);
 }

 @Override
 public void onStart(Intent intent, int flags)
 {
  myPlayer.start();
 }
 
 @Override
 public void onDestroy()
 {
  myPlayer.stop();
  //Toast.makeText(getApplicationContext(), "Stopped Playing Downloaded Song", Toast.LENGTH_SHORT).show();
 }

}




