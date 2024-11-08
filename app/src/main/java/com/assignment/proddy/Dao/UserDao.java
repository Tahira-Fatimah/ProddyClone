package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.user.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    public Long insert(User user);

    @Delete
    public void delete(User user);

    @Update
    public void update(User user);

    @Query("SELECT * FROM user")
    public List<User> getAllUsers();
}
