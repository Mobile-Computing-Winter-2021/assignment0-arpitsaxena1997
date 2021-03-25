package com.example.helloworld;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_gps")
public class DataGPS {
    @PrimaryKey(autoGenerate = true)
    public int id_gps;

    @ColumnInfo(name = "gps_timestamp")
    public Long timestamp_gps;

    // Latitude
    @ColumnInfo(name = "gps_data_0")
    public double data_gps_0;

    // Longitude
    @ColumnInfo(name = "gps_data_1")
    public double data_gps_1;

    /*@ColumnInfo(name = "gps_data_2")
    public float data_gps_2;*/

    public DataGPS(Long timestamp_gps, double data_gps_0, double data_gps_1) {
        this.data_gps_0 = data_gps_0;
        this.data_gps_1 = data_gps_1;
        this.timestamp_gps = timestamp_gps;
    }

    public int getId_gps() {
        return id_gps;
    }

    public void setId_gps(int id_gps) {
        this.id_gps = id_gps;
    }

    public Long getTimestamp_gps() {
        return timestamp_gps;
    }

    public void setTimestamp_gps(Long timestamp_gps) {
        this.timestamp_gps = timestamp_gps;
    }

    public double getData_gps_0() {
        return data_gps_0;
    }

    public void setData_gps_0(double data_gps_0) {
        this.data_gps_0 = data_gps_0;
    }

    public double getData_gps_1() {
        return data_gps_1;
    }

    public void setData_gps_1(double data_gps_1) {
        this.data_gps_1 = data_gps_1;
    }
}
