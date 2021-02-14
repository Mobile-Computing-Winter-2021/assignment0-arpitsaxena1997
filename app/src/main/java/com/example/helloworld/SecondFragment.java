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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SecondFragment extends Fragment {
    Button buttonDownloadSong, buttonPlayDownload, buttonStopDownload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        /*Checking Connection Status*/
        //boolean connected = new SecondActivity().connectionStatus();
        //TextView stat = (TextView)v.findViewById(R.id.stat);

        TextView stat = (TextView) v.findViewById(R.id.network);
        TextView netType = (TextView) v.findViewById(R.id.networkType);
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mob = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean connected;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            //Toast.makeText(this, "Netowk is connected", Toast.LENGTH_SHORT).show();
            stat.setText("Network is Connected");
            connected = true;
            if (wifi != null) {
                netType.setText("Connected via Wifi");
                Toast.makeText(getActivity(), "Network is connected with Wi-fi", Toast.LENGTH_SHORT).show();
            } else {
                netType.setText("Connected via Wifi");
                Toast.makeText(getActivity(), "Network is connected with Mobile Data", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Toast.makeText(this, "Network is not Conneted", Toast.LENGTH_SHORT).show();
            stat.setText("Network is not Connected");
            connected = false;
        }

        buttonDownloadSong = (Button) v.findViewById(R.id.buttonDownloadSong);
        buttonPlayDownload = (Button) v.findViewById(R.id.buttonPlayDownload);
        buttonStopDownload = (Button) v.findViewById(R.id.buttonStopDownload);

        buttonStopDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Stopped playing downloaded song", Toast.LENGTH_SHORT).show();
                String tempFile = Environment.getExternalStorageDirectory() + "/arpit/MC.mp3";
                File file = new File(tempFile);

                TextView stat = (TextView) v.findViewById(R.id.songNotPresent);

                if (file.exists()){
                    getActivity().stopService(new Intent(getActivity(), DownloadMusicPlay.class));
                    stat.setText("Downloaded song Stopped");
                    Toast.makeText(getActivity(), "Stopped playing the downloaded song", Toast.LENGTH_SHORT).show();
                }

                else {
                    stat.setText("Song not Present, Download the song, then play it and then stop it");
                    Toast.makeText(getActivity(), "Song not Present, Download the song, then play it and then stop it", Toast.LENGTH_SHORT).show();
                }

            }
        });


        buttonPlayDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Started Playing Downloaded Song", Toast.LENGTH_SHORT).show();
                String tempFile = Environment.getExternalStorageDirectory() + "/arpit/MC.mp3";
                File file = new File(tempFile);

                TextView stat = (TextView) v.findViewById(R.id.songNotPresent);

                if (file.exists()){
                    getActivity().startService(new Intent(getActivity(), DownloadMusicPlay.class));

                    stat.setText("Playing Downloaded Song");
                    Toast.makeText(getActivity(), "Playing Downloaded Song", Toast.LENGTH_SHORT).show();
                }
                else{
                    stat.setText("Song not Present, Download the song, then play it");
                    Toast.makeText(getActivity(), "Song not Present, Download the song, then play it", Toast.LENGTH_SHORT).show();
                }
            }
        });


        buttonDownloadSong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TextView stat = (TextView) v.findViewById(R.id.stat);
                stat.setText("Downloading Started");

                TextView result = (TextView) getView().findViewById(R.id.result);
                result.setText("");
                //Toast.makeText(getApplicationContext(), "Downloading Started", Toast.LENGTH_SHORT).show();
                new SecondFragment.DownloadSongTask().execute();

            }
        });


        return v;
    }


    public class DownloadSongTask extends AsyncTask<String, Void, String> {

        private void checkDirectory(File f) {
            if (!f.exists()) {
                f.mkdir();
            }
        }

        private void checkFile(File f) throws IOException {
            if (!f.exists()) {
                f.createNewFile();
            }
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
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
                for (len = 0; (len = is.read(buffer)) != -1; fileOutputStream.write(buffer)) {

                }
                TextView result = (TextView) getView().findViewById(R.id.result);
                result.setText("Downloading Finished");
                //Toast.makeText(this, "Downloading Completed", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

    }
}