package com.example.helloworld;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SecondFragment extends Fragment {
    Button buttonBack, buttonEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        Intent intent = getActivity().getIntent();

        String name =  intent.getStringExtra("name");
        String rollNo = intent.getStringExtra("rollNo");
        String dept = intent.getStringExtra("dept");
        String email = intent.getStringExtra("email");
        int pos = intent.getIntExtra("pos", 0);

        TextView tname = (TextView) v.findViewById(R.id.fName);
        TextView trollNo = (TextView) v.findViewById(R.id.fRollNo);
        TextView tdept = (TextView) v.findViewById(R.id.fDept);
        TextView temail = (TextView) v.findViewById(R.id.fEmail);

        tname.setText(name);
        trollNo.setText(rollNo);
        tdept.setText(dept);
        temail.setText(email);


        //buttonBack = (Button) v.findViewById(R.id.buttonBack);
        buttonEdit = (Button) v.findViewById(R.id.buttonEdit);

        /*buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
                //super.onBackPressed();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.contant_main, new Home()).commit();
            }
        });*/


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edName = (EditText) v.findViewById(R.id.eName);
                EditText edEmail = (EditText) v.findViewById(R.id.eEmail);
                EditText edDept = (EditText) v.findViewById(R.id.eDept);

                StudentData details = StudentData.get();
                Stud stat = details.getStudnetData(rollNo);

                stat.dName = edName.getText().toString();
                stat.dEid = edEmail.getText().toString();
                stat.dDept = edDept.getText().toString();

                tname.setText(stat.dName);
                tdept.setText(stat.dDept);
                temail.setText(stat.dEid);

            }
        });



        return v;
    }


}