package com.example.helloworld;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ChargerReceiver extends BroadcastReceiver
{
 public ChargerReceiver()
 {
 
 }
 
 @Override
 public void onReceive(Context context, Intent intent)
 {
  if(intent.getAction() == Intent.ACTION_POWER_DISCONNECTED)
  {
   Toast.makeText(context, "BroadcastReceiver: Power Disconnected", Toast.LENGTH_SHORT).show();
  }
  if(intent.getAction() == Intent.ACTION_POWER_CONNECTED)
  {
   Toast.makeText(context, "BroadcastReceiver: Power Connected", Toast.LENGTH_SHORT).show();
  }
  if(intent.getAction() == Intent.ACTION_BATTERY_LOW)
  {
   Toast.makeText(context, "BroadcastReceiver: Battery Low", Toast.LENGTH_SHORT).show();
  }
  if(intent.getAction() == Intent.ACTION_BATTERY_OKAY)
  {
   Toast.makeText(context, "BroadcastReceiver: Battery Okay", Toast.LENGTH_SHORT).show();
  }
 }
}