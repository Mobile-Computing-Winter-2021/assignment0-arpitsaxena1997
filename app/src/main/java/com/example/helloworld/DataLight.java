package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_light")
public class DataLight {
    @PrimaryKey(autoGenerate = true)
    public int id_light;

    @ColumnInfo(name = "light_timestamp")
    public Long timestamp_light;

    @ColumnInfo(name = "light_data_0")
    public float data_light_0;

    /*@ColumnInfo(name = "light_data_1")
    public float data_light_1;

    @ColumnInfo(name = "light_data_2")
    public float data_light_2;*/

    public DataLight(Long timestamp_light, float data_light_0) {
        this.timestamp_light = timestamp_light;
        this.data_light_0 = data_light_0;
    }

    public int getId_light() {
        return id_light;
    }

    public void setId_light(int id_light) {
        this.id_light = id_light;
    }

    public Long getTimestamp_light() {
        return timestamp_light;
    }

    public void setTimestamp_light(Long timestamp_light) {
        this.timestamp_light = timestamp_light;
    }

    public float getData_light_0() {
        return data_light_0;
    }

    public void setData_light_0(float data_light_0) {
        this.data_light_0 = data_light_0;
    }
}
