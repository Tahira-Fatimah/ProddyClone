package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitStack;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;

import java.util.Date;
import java.util.List;

@Dao
public interface HabitDao {
    @Insert
    public Long insert(Habit habit);

    @Delete
    public void delete(Habit habit);

    @Update
    public void update(Habit habit);

    @Query("SELECT * FROM habit h JOIN habit_step hs ON h.id = hs.habitId")
    public List<HabitStack> getHabitStack();

    @Query("SELECT * FROM habit h " +
            "JOIN habit_tracker ht ON h.id = ht.habitId " +
            "WHERE ht.status = 0 AND DATE(ht.date) = DATE(:today) AND h.userId = :userId")
    public List<HabitWithTrackers> getIncompleteHabits(int userId, Date today);

    @Query("SELECT * FROM habit h " +
            "JOIN habit_tracker ht ON h.id = ht.habitId " +
            "WHERE ht.status = 1 AND DATE(ht.date) = DATE(:today) AND h.userId = :userId")
    public List<HabitWithTrackers> getCompletedHabits(int userId, Date today);

    @Query("SELECT * FROM habit")
    public List<Habit> getHabits();
}
