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
 public final String TAG="Fragment Second Activity";

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

}
