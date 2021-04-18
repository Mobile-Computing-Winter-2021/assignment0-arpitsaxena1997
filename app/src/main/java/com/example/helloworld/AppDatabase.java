package com.example.helloworld;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DataRoom.class, DataWardrive.class, DataAcc.class, DataGyro.class, DataMagneto.class, DataStep.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();


}

