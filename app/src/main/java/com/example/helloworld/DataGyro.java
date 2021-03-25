package com.example.helloworld;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_gyro")
public class DataGyro {
    @PrimaryKey(autoGenerate = true)
    public int id_gyro;

    @ColumnInfo(name = "gyro_timestamp")
    public Long timestamp_gyro;

    @ColumnInfo(name = "gyro_data_0")
    public float data_gyro_0;

    @ColumnInfo(name = "gyro_data_1")
    public float data_gyro_1;

    @ColumnInfo(name = "gyro_data_2")
    public float data_gyro_2;

    public DataGyro(Long timestamp_gyro, float data_gyro_0, float data_gyro_1, float data_gyro_2) {
        this.timestamp_gyro = timestamp_gyro;
        this.data_gyro_1 = data_gyro_1;
        this.data_gyro_0 = data_gyro_0;
        this.data_gyro_2 = data_gyro_2;
    }

    public int getId_gyro() {
        return id_gyro;
    }

    public void setId_gyro(int id_gyro) {
        this.id_gyro = id_gyro;
    }

    public Long getTimestamp_gyro() {
        return timestamp_gyro;
    }

    public void setTimestamp_gyro(Long timestamp_gyro) {
        this.timestamp_gyro = timestamp_gyro;
    }

    public float getData_gyro_0() {
        return data_gyro_0;
    }

    public void setData_gyro_0(float data_gyro_0) {
        this.data_gyro_0 = data_gyro_0;
    }

    public float getData_gyro_1() {
        return data_gyro_1;
    }

    public void setData_gyro_1(float data_gyro_1) {
        this.data_gyro_1 = data_gyro_1;
    }

    public float getData_gyro_2() {
        return data_gyro_2;
    }

    public void setData_gyro_2(float data_gyro_2) {
        this.data_gyro_2 = data_gyro_2;
    }
}
