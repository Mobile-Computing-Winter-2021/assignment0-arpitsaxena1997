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

}
