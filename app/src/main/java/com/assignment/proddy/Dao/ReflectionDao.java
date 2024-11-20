package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.reflection.Reflection;

@Dao
public interface ReflectionDao {

    @Insert
    public Long insert(Reflection reflection);

    @Delete
    public void delete(Reflection reflection);

    @Update
    public void update(Reflection reflection);
}
