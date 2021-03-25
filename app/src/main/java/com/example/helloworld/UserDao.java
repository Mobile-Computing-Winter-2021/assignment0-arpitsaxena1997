package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_acc(DataAcc dataAcc);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_gps(DataGPS dataGPS);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_gyro(DataGyro dataGyro);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_light(DataLight dataLight);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_linAcc(DataLinAcc dataLinAcc);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_proximity(DataProximity dataProximity);

    @Query("Select * from table_acc")
    List<DataAcc> getAccData();

    @Query("Select * from table_light")
    List<DataLight> getLightData();

}
