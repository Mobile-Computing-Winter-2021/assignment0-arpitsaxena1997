package com.example.helloworld;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_mangnet")
public class DataMagneto {
    @PrimaryKey(autoGenerate = true)
    public int id_magnet;

    @ColumnInfo(name = "magnet_timestamp")
    public Long timestamp_magnet;

    @ColumnInfo(name = "magnet_data_0")
    public float data_magnet_0;

    @ColumnInfo(name = "gyro_data_1")
    public float data_magnet_1;

    @ColumnInfo(name = "gyro_data_2")
    public float data_magnet_2;

    public DataMagneto(Long timestamp_magnet, float data_magnet_0, float data_magnet_1, float data_magnet_2) {
        this.timestamp_magnet = timestamp_magnet;
        this.data_magnet_0 = data_magnet_0;
        this.data_magnet_1 = data_magnet_1;
        this.data_magnet_2 = data_magnet_2;
    }

    public int getId_magnet() {
        return id_magnet;
    }

    public void setId_magnet(int id_magnet) {
        this.id_magnet = id_magnet;
    }

    public Long getTimestamp_magnet() {
        return timestamp_magnet;
    }

    public void setTimestamp_magnet(Long timestamp_magnet) {
        this.timestamp_magnet = timestamp_magnet;
    }

    public float getData_magnet_0() {
        return data_magnet_0;
    }

    public void setData_magnet_0(float data_magnet_0) {
        this.data_magnet_0 = data_magnet_0;
    }

    public float getData_magnet_1() {
        return data_magnet_1;
    }

    public void setData_magnet_1(float data_magnet_1) {
        this.data_magnet_1 = data_magnet_1;
    }

    public float getData_magnet_2() {
        return data_magnet_2;
    }

    public void setData_magnet_2(float data_magnet_2) {
        this.data_magnet_2 = data_magnet_2;
    }
}