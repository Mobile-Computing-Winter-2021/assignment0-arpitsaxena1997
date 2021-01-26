package com.example.helloworld;

import android.util.Log;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class SecondActivity extends android.app.Activity{

    private boolean one, two, three, four, five;
    private String name_s;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String name_s = intent.getStringExtra("name");
        boolean one = intent.getBooleanExtra("pre1", false);
        boolean two = intent.getBooleanExtra("pre2", false);
        boolean three = intent.getBooleanExtra("pre3", false);
        boolean four = intent.getBooleanExtra("pre4", false);
        boolean five = intent.getBooleanExtra("pre5", false);

        TextView tv_name = (TextView) findViewById(R.id.textView);
        TextView tv_pre1 = (TextView) findViewById(R.id.pre1);
        TextView tv_pre2 = (TextView) findViewById(R.id.pre2);
        TextView tv_pre3 = (TextView) findViewById(R.id.pre3);
        TextView tv_pre4 = (TextView) findViewById(R.id.pre4);
        TextView tv_pre5 = (TextView) findViewById(R.id.pre5);
        TextView tv_res = (TextView) findViewById(R.id.result);

        String str = "Hii, " + name_s;
        tv_name.setText(str);

        //int count = 0;
        if (one == true) {
            str = "You are Wearing a mask when outside";
            tv_pre1.setText(str);
            count++;
        }
        else {
            str = "You are not Wearing a mask when outside";
            tv_pre1.setText(str);
        }

        if (two == true) {
            str = "You are Washing hands frequently";
            tv_pre2.setText(str);
            count++;
        }
        else {
            str = "You are not Washing hands frequently";
            tv_pre2.setText(str);
        }

        if (three == true) {
            str = "You are Maintaining 2 feet distance";
            tv_pre3.setText(str);
            count++;
        }
        else {
            str = "You are not Maintaining 2 feet distance";
            tv_pre3.setText(str);
        }

        if (four == true) {
            str = "You are Covering nose and mouth while sneezing and coughing";
            tv_pre4.setText(str);
            count++;
        }
        else {
            str = "You are not Covering nose and mouth while sneezing and coughing";
            tv_pre4.setText(str);
        }

        if (five == true) {
            str = "You are Taking healthy diets";
            tv_pre5.setText(str);
            count++;
        }
        else {
            str = "You are not Taking healthy diets";
            tv_pre5.setText(str);
        }

        if (count == 5) {
            str = "You are SAFE!!!";
            tv_res.setText(str);
        }
        else {
            str = "You are NOT SAFE!!!";
            tv_res.setText(str);
        }

    }

    public void back(View v) {
        if (count == 5) {
            setAnswerShownResult(true);
            Toast.makeText(this, "You are SAFE!!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
        else {
            setAnswerShownResult(false);
            Toast.makeText(this, "You are NOT SAFE!!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra("COVID_Safe_Status",isAnswerShown);
        setResult(Activity.RESULT_OK,data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onCreate to onStart");
        Toast.makeText(this, "State of Second activity changed from onCreate to onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onStart to onResume");
        Toast.makeText(this, "State of Second activity changed from onStart to onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onResume to onPause");
        Toast.makeText(this, "State of Second activity changed from onResume to onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("State of the Activity", this.getClass().getSimpleName()+ "status changed from onPause to onStop");
        Toast.makeText(this, "State of Second activity changed from onPause to onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onStop to onDestroy");
        Toast.makeText(this, "State of Second activity changed from onStop to onDestroy", Toast.LENGTH_SHORT).show();
    }

}

