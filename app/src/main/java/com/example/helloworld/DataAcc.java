package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_acc")
public class DataAcc {
    @PrimaryKey(autoGenerate = true)
    public int id_acc;

    @ColumnInfo(name = "acc_timestamp")
    public Long timestamp_acc;

    @ColumnInfo(name = "acc_data_0")
    public float data_acc_0;

    @ColumnInfo(name = "acc_data_1")
    public float data_acc_1;

    @ColumnInfo(name = "acc_data_2")
    public float data_acc_2;

    public DataAcc(long timestamp_acc, float data_acc_0, float data_acc_1, float data_acc_2){
        this.data_acc_0 = data_acc_0;
        this.data_acc_1 = data_acc_1;
        this.data_acc_2 = data_acc_2;
        this.timestamp_acc = timestamp_acc;
    }

    public int getId_acc() {
        return id_acc;
    }

    public void setId_acc(int id_acc) {
        this.id_acc = id_acc;
    }

    public Long getTimestamp_acc() {
        return timestamp_acc;
    }

    public void setTimestamp_acc(Long timestamp_acc) {
        this.timestamp_acc = timestamp_acc;
    }

    public float getData_acc_0() {
        return data_acc_0;
    }

    public void setData_acc_0(float data_acc_0) {
        this.data_acc_0 = data_acc_0;
    }

    public float getData_acc_1() {
        return data_acc_1;
    }

    public void setData_acc_1(float data_acc_1) {
        this.data_acc_1 = data_acc_1;
    }

    public float getData_acc_2() {
        return data_acc_2;
    }

    public void setData_acc_2(float data_acc_2) {
        this.data_acc_2 = data_acc_2;
    }
}