package com.example.helloworld;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_room(DataRoom dataRoom);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_war(DataWardrive dataWardrive);


    @Query("Select * from table_room")
    List<DataRoom> getAllRooms();

    @Query("Select * from table_wardrive")
    List<DataWardrive> getWarData();

    // IMU

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_acc(DataAcc dataAcc);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_gyro(DataGyro dataGyro);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_magnet(DataMagneto dataMagneto);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert_step(DataStep dataStep);

    @Query("Select * from table_acc")
    List<DataAcc> getAccData();

    @Query("Select * from table_gyro")
    List<DataGyro> getGyroData();

    @Query("Select * from table_mangnet")
    List<DataMagneto> getMagnetData();

    @Query("Select * from table_step")
    List<DataStep> getStepData();

}
