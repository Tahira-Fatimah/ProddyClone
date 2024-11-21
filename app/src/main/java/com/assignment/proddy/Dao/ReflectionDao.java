package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.reflection.Reflection;

import java.util.UUID;

@Dao
public interface ReflectionDao {

    @Insert
    public Long insert(Reflection reflection);

    @Delete
    public void delete(Reflection reflection);

    @Update
    public void update(Reflection reflection);

    @Query("SELECT * FROM reflection WHERE reflectionId =:uuid")
    public Reflection findReflectionById(UUID uuid);
}
