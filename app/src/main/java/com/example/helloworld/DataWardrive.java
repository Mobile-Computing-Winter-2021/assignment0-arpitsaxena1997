package com.example.helloworld;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_wardrive")
public class DataWardrive {
    @PrimaryKey(autoGenerate = true)
    public int id_wardrive;

    @ColumnInfo(name = "rssiValue")
    public int data_rssi;

    @ColumnInfo(name = "ssidValue")
    public String data_ssid;

    @ColumnInfo(name = "roomLabel")
    public String data_roomLabel;

    public DataWardrive(int data_rssi, String data_ssid, String data_roomLabel) {
        this.data_rssi = data_rssi;
        this.data_ssid = data_ssid;
        this.data_roomLabel = data_roomLabel;
    }

    /*public int getId_wardrive() {
        return id_wardrive;
    }

    public void setId_wardrive(int id_wardrive) {
        this.id_wardrive = id_wardrive;
    }*/

    public int getData_rssi() {
        return data_rssi;
    }

    public void setData_rssi(int data_rssi) {
        this.data_rssi = data_rssi;
    }

    public String getData_roomLabel() {
        return data_roomLabel;
    }

    public void setData_roomLabel(String data_roomLabel) {
        this.data_roomLabel = data_roomLabel;
    }
}
