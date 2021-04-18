package com.example.helloworld;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_step")
public class DataStep {
    @PrimaryKey(autoGenerate = true)
    public int id_gyro;

    @ColumnInfo(name = "gyro_timestamp")
    public Long timestamp_gyro;

    @ColumnInfo(name = "step_data")
    public float data_step;

    public DataStep(Long timestamp_gyro, float data_step) {
        this.timestamp_gyro = timestamp_gyro;
        this.data_step = data_step;
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

    public float getData_step() {
        return data_step;
    }

    public void setData_step(float data_step) {
        this.data_step = data_step;
    }
}