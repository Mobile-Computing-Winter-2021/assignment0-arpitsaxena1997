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

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new ItemFragment();
        fm.beginTransaction().replace(R.id.fragment_container_list, fragment).commit();


/*

        Fragment frag = fm.findFragmentById(R.id.fragment_container_list);
        if(frag==null){
            Log.i(TAG,"fragment is  null");
            frag = new ItemFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container_list,frag)
                    .commit();
*/
        Log.i(TAG,"after inflating");
        }

    }

   /* @Override
    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }*/

