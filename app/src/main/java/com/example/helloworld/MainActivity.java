package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.app.Activity;
import android.widget.CheckBox;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clear(View v){
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName4);
        editText.getText().clear();

        CheckBox box1 = (CheckBox) findViewById(R.id.checkBox1);
        box1.setChecked(false);
        CheckBox box2 = (CheckBox) findViewById(R.id.checkBox2);
        box2.setChecked(false);
        CheckBox box3 = (CheckBox) findViewById(R.id.checkBox3);
        box3.setChecked(false);
        CheckBox box4 = (CheckBox) findViewById(R.id.checkBox4);
        box4.setChecked(false);
        CheckBox box5 = (CheckBox) findViewById(R.id.checkBox5);
        box5.setChecked(false);
    }

    public void submit(View v){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextTextPersonName4);
        intent.putExtra("name", editText.getText().toString());

        CheckBox box1 = (CheckBox) findViewById(R.id.checkBox1);
        intent.putExtra("pre1", box1.isChecked());
        CheckBox box2 = (CheckBox) findViewById(R.id.checkBox2);
        intent.putExtra("pre2", box2.isChecked());
        CheckBox box3 = (CheckBox) findViewById(R.id.checkBox3);
        intent.putExtra("pre3", box3.isChecked());
        CheckBox box4 = (CheckBox) findViewById(R.id.checkBox4);
        intent.putExtra("pre4", box4.isChecked());
        CheckBox box5 = (CheckBox) findViewById(R.id.checkBox5);
        intent.putExtra("pre5", box5.isChecked());

        startActivityForResult(intent, 0);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode!= Activity.RESULT_OK)
            return;
        if(requestCode==requestQode){
            if(data==null){
                return;
            }
            wasAnswerShown=data.getBooleanExtra("COVID_Safe_Status",false);
            //Toast.makeText(this, "Your COVID status")
            //checkAnswer(wasAnswerShown);
        }
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onCreate to onStart");
        Toast.makeText(this, "State of Main activity changed from onCreate to onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onStart to onResume");
        Toast.makeText(this, "State of Main activity changed from onStart to onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onResume to onPause");
        Toast.makeText(this, "State of Main activity changed from onResume to onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("State of the Activity", this.getClass().getSimpleName()+ "status changed from onPause to onStop");
        Toast.makeText(this, "State of Main activity changed from onPause to onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("State of the Activity", this.getClass().getSimpleName()+"status changed from onStop to onDestroy");
        Toast.makeText(this, "State of Main activity changed from onStop to onDestroy", Toast.LENGTH_SHORT).show();
    }


}