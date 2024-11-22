package com.assignment.proddy.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habitTracker.HabitTracker;

@Dao
public interface HabitTrackerDao {
    @Insert
    public Long insert(HabitTracker habitTracker);

    @Delete
    public Void delete(HabitTracker habitTracker);

    @Update
    public Void update(HabitTracker habitTracker);

    @Query("UPDATE habit_tracker SET habitTrackerStatus=1 WHERE habitTrackerId=:id")
    public Void markCompleted(int id);
}