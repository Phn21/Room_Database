package com.example.ex7;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TaskDao {

    @Insert
    void insert(com.example.ex7.Task task);

    @Query("SELECT * from Task Where id = :id")
    Task get(int id);

}
