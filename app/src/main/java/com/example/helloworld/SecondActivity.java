package com.example.helloworld;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SecondActivity extends android.app.Activity
{
 Button buttonDownloadSong, buttonPlayDownload, buttonStopDownload;
 
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_second);
  
  
  buttonDownloadSong = findViewById(R.id.buttonDownloadSong);
  buttonPlayDownload = findViewById(R.id.buttonPlayDownload);
  buttonStopDownload = findViewById(R.id.buttonStopDownload);
  
  buttonStopDownload.setOnClickListener(new View.OnClickListener()
  {
   @Override
   public void onClick(View view)
   {
    Toast.makeText(getApplicationContext(), "Stopped playing downloaded song", Toast.LENGTH_SHORT).show();
    stopService(new Intent(getApplicationContext(), DownloadMusicPlay.class));
   }
  });
  
  
  buttonPlayDownload.setOnClickListener(new View.OnClickListener()
  {
   @Override
   public void onClick(View view)
   {
    Toast.makeText(getApplicationContext(), "Started Playing Downloaded Song", Toast.LENGTH_SHORT).show();
    startService(new Intent(getApplicationContext(), DownloadMusicPlay.class));
   }
  });
  
  
  buttonDownloadSong.setOnClickListener(new View.OnClickListener()
  {

   @Override
   public void onClick(View view)
   {
    TextView stat = (TextView) findViewById(R.id.stat);

    stat.setText("Downloading Started");
    Toast.makeText(getApplicationContext(), "Downloading Started", Toast.LENGTH_SHORT).show();
    new DownloadSongTask().execute();

   }
  });

  boolean connected = connectionStatus();
  TextView stat = (TextView) findViewById(R.id.stat);
  
 }
 
 protected boolean connectionStatus()
 {
  TextView stat = (TextView) findViewById(R.id.network);
  ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
  
  //NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
  //NetworkInfo mob = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
  
  NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
  if(activeNetwork != null && activeNetwork.isConnectedOrConnecting())
  {
   Toast.makeText(this, "Netowk is connected", Toast.LENGTH_SHORT).show();
   stat.setText("Network is Connected");
   return true;
  }else
  {
   Toast.makeText(this, "Network is not Conneted", Toast.LENGTH_SHORT).show();
   stat.setText("Network is not Connected");
   return false;
  }
 }
 
 
 private class DownloadSongTask extends AsyncTask<String, Void, String>
 {
  
  private void checkDirectory(File f)
  {
   if(!f.exists())
   {
    f.mkdir();
   }
  }
  
  private void checkFile(File f) throws IOException
  {
   if(!f.exists())
   {
    f.createNewFile();
   }
  }
  
  @Override
  protected String doInBackground(String... urls)
  {
   try
   {
    String myURL = "https://faculty.iiitd.ac.in/~mukulika/s1.mp3";
    URL url = new URL(myURL);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.connect();
    
    //int response = conn.getResponseCode(); Log.d("Http", "The response is: " + response);
    InputStream is = conn.getInputStream();
    String tempFile = Environment.getExternalStorageDirectory() + "/arpit";
    File dir = new File(tempFile);
    checkDirectory(dir);
    
    File f = new File(dir, "MC.mp3");
    checkFile(f);
    
    //getDir("file", Context.MODE_PRIVATE).getAbsolutePath() + "/rm_song.mp3";
    FileOutputStream fileOutputStream = new FileOutputStream(f);
    byte[] buffer = new byte[1024];
    int len = 0;
    for(len = 0; (len = is.read(buffer)) != -1; fileOutputStream.write(buffer))
    {
    
    }
    TextView result = (TextView) findViewById(R.id.result);
    result.setText("Downloading Finished");
    Toast.makeText(getApplicationContext(), "Downloading Completed", Toast.LENGTH_SHORT).show();
   }catch(Exception e)
   {
    e.printStackTrace();
   }
   return null;
   
  }
  
 }


 
}