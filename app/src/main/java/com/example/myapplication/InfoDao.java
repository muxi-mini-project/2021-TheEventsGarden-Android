package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InfoDao {
    @Insert
    void InsertInfo(Info... info);

    @Delete
    void DeleteInfo(Info... infos);

    @Query("SELECT * FROM Info ORDER BY ID")
    List<Info> getAllInfo();
}
