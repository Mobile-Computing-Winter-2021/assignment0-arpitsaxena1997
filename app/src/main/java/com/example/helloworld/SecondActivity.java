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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SecondActivity extends FragmentActivity
{
 public final String TAG="Fragment";

 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_second);

  FragmentManager fm = getSupportFragmentManager();
  Fragment frag = fm.findFragmentById(R.id.fragment_activity2);
  if(frag==null){
   Log.i(TAG,"fragment is  null");
   frag = new SecondFragment();
   fm.beginTransaction()
           .add(R.id.fragment_activity2,frag)
           .commit();
  }
  Log.i(TAG,"after inflating");

 }

/* protected class DownloadSongTask extends AsyncTask<String, Void, String>
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

 }*/


}
