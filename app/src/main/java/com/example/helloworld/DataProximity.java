package com.example.helloworld;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_proximity")
public class DataProximity {
    @PrimaryKey(autoGenerate = true)
    public int id_proximity;

    @ColumnInfo(name = "proximity_proximity")
    public Long timestamp_proximity;

    @ColumnInfo(name = "proximity_data_0")
    public float data_proximity_0;

    /*@ColumnInfo(name = "proximity_data_1")
    public float data_proximity_1;

    @ColumnInfo(name = "proximity_data_2")
    public float data_proximity_2;*/

    public DataProximity(Long timestamp_proximity, float data_proximity_0){
        this.timestamp_proximity = timestamp_proximity;
        this.data_proximity_0 = data_proximity_0;
    }

    public int getId_proximity() {
        return id_proximity;
    }

    public void setId_proximity(int id_proximity) {
        this.id_proximity = id_proximity;
    }

    public Long getTimestamp_proximity() {
        return timestamp_proximity;
    }

    public void setTimestamp_proximity(Long timestamp_proximity) {
        this.timestamp_proximity = timestamp_proximity;
    }

    public float getData_proximity_0() {
        return data_proximity_0;
    }

    public void setData_proximity_0(float data_proximity_0) {
        this.data_proximity_0 = data_proximity_0;
    }
}
