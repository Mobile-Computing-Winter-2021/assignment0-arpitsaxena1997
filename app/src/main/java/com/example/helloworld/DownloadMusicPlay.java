package com.example.helloworld;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
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
  
  Uri uri = Uri.fromFile(new File(tempFile));
  
  myPlayer = MediaPlayer.create(getApplicationContext(), uri);
 }
 
 @Override
 public void onStart(Intent intent, int flags)
 {
  myPlayer.start();
  Toast.makeText(getApplicationContext(), "Stop in music", Toast.LENGTH_SHORT).show();
 }
 
 @Override
 public void onDestroy()
 {
  myPlayer.stop();
 }
}


