package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habitStreak.HabitStreak;

import java.util.UUID;

@Dao
public interface HabitStreakDao {

    @Insert
    public Long insert(HabitStreak habitStreak);

    @Update
    public Void update(HabitStreak habitStreak);

    @Query("SELECT * FROM habit_streak WHERE habitStreak_HabitId = :habitId")
    public HabitStreak getHabitStreak(UUID habitId);

}
