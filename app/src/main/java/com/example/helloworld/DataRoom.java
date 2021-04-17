package com.example.helloworld;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_room")
public class DataRoom {
    @PrimaryKey(autoGenerate = true)
    public int id_room;

    @ColumnInfo(name = "roomName")
    public String roomName;

    public DataRoom(String roomName){
        this.roomName = roomName;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
