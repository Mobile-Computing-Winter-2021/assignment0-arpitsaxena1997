package com.example.helloworld;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapter {
    /*Context context;
    LayoutInflater inflater;
    List<ScanResult> wifiList;

    public ListAdapter(Context context, List<ScanResult> wifiList) {
        this.context  = context;
        this.wifiList = wifiList;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return wifiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListHolder listHolder;
        if(view == null) {
            view = inflater.inflate(R.layout.item_list, null);
            listHolder = new ListHolder();

            listHolder.wifiOutput = (TextView)view.findViewById(R.id.wifiName);
            view.setTag(listHolder);
        }
        else {
            listHolder = (ListHolder)view.getTag();
        }

        listHolder.wifiOutput.setText(wifiList.get(i).SSID);
        TextView t1 = (TextView) view.findViewById(R.id.textView);
        t1.setText(wifiList.get(0).SSID);
        return view;
    }

    class ListHolder{
        TextView wifiOutput;
    }*/
}
