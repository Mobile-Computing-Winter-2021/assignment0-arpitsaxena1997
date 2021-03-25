package com.example.helloworld;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DataAcc.class, DataGPS.class, DataGyro.class, DataLight.class, DataLinAcc.class, DataProximity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();


}

