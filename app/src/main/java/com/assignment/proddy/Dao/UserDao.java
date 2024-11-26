package com.assignment.proddy.Dao;

import static java.nio.file.attribute.AclEntryPermission.DELETE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.user.User;

import java.util.List;
import java.util.UUID;

@Dao
public interface UserDao {
    @Insert
    public Long insert(User user);

    @Delete
    public Void delete(User user);

    @Update
    public void update(User user);

    @Query("SELECT * FROM user WHERE userEmail = :email AND userPassword = :password")
    public User getUserByEmailAndPassword(String email, String password);

    @Query("SELECT COUNT(*) FROM habit WHERE habit_UserId = :userId")
    public int getUserHabitCount(UUID userId);

    @Query("DELETE FROM user WHERE userId = :userId ")
    public Void deleteUserById(UUID userId);
}
