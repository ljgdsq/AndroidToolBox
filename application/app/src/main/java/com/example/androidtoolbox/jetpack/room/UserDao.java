package com.example.androidtoolbox.jetpack.room;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User ...users);

    @Update
    int updateUser(User ...users);

    @Delete
    int deleteUser(User...users);

    @Query("Delete From User")
    int deleteAll();

    @Query("SELECT * FROM User")
    List<User> getAll();
}
