package com.example.vuvansan;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);
    @Query("SELECT COUNT(name) FROM user ")
    int count();
}
