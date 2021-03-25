package com.example.helloworld;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_linAcc")
public class DataLinAcc {
    @PrimaryKey(autoGenerate = true)
    public int id_linAcc;

    @ColumnInfo(name = "linAcc_timestamp")
    public Long timestamp_linAcc;

    @ColumnInfo(name = "linAcc_data_0")
    public float data_linAcc_0;

    @ColumnInfo(name = "linAcc_data_1")
    public float data_linAcc_1;

    @ColumnInfo(name = "linAcc_data_2")
    public float data_linAcc_2;

    public DataLinAcc(Long timestamp_linAcc, float data_linAcc_0, float data_linAcc_1, float data_linAcc_2) {
        this.timestamp_linAcc = timestamp_linAcc;
        this.data_linAcc_0 = data_linAcc_0;
        this.data_linAcc_1 = data_linAcc_1;
        this.data_linAcc_2 = data_linAcc_2;
    }

    public int getId_linAcc() {
        return id_linAcc;
    }

    public void setId_linAcc(int id_linAcc) {
        this.id_linAcc = id_linAcc;
    }

    public Long getTimestamp_linAcc() {
        return timestamp_linAcc;
    }

    public void setTimestamp_linAcc(Long timestamp_linAcc) {
        this.timestamp_linAcc = timestamp_linAcc;
    }

    public float getData_linAcc_0() {
        return data_linAcc_0;
    }

    public void setData_linAcc_0(float data_linAcc_0) {
        this.data_linAcc_0 = data_linAcc_0;
    }

    public float getData_linAcc_1() {
        return data_linAcc_1;
    }

    public void setData_linAcc_1(float data_linAcc_1) {
        this.data_linAcc_1 = data_linAcc_1;
    }

    public float getData_linAcc_2() {
        return data_linAcc_2;
    }

    public void setData_linAcc_2(float data_linAcc_2) {
        this.data_linAcc_2 = data_linAcc_2;
    }
}
