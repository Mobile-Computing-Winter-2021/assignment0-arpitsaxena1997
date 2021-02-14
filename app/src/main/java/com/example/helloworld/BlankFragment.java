package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class BlankFragment extends Fragment
{
 Button btnStart;
 Button btnStop;
 Button btnNext;


 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
 {
  View v =  inflater.inflate(R.layout.fragment_blank, container, false);

  btnStart = (Button)v.findViewById(R.id.btnPlay);
  btnStop = (Button)v.findViewById(R.id.btnStop);
  btnNext = (Button)v.findViewById((R.id.btnSecActivity));

  btnStart.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    getActivity().startService(new Intent(getActivity(), PlayerService.class));
   }
  });

  btnStop.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    getActivity().stopService(new Intent(getActivity(), PlayerService.class));
   }
  });

  btnNext.setOnClickListener(new View.OnClickListener() {
   @Override
   public void onClick(View view) {
    Intent intent = new Intent(getActivity(), SecondActivity.class);

    startActivityForResult(intent, 0);
   }
  });



  return v;
 }
}
