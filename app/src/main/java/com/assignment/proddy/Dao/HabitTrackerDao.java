package com.assignment.proddy.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.ObjectMapping.HabitWithTracker;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Query("SELECT * FROM habit h LEFT JOIN habit_tracker ht ON h.habitId = ht.habitTracker_HabitId WHERE habit_UserId = :userId " +
            "AND (date(ht.habitTrackerDate/1000, 'unixepoch') >= date(:startDate/1000, 'unixepoch') " +
            "AND date(ht.habitTrackerDate/1000, 'unixepoch') <= date(:endDate/1000, 'unixepoch'))")
    public List<HabitTracker> getTrackersWithDateBound(UUID userId, Date startDate, Date endDate);

    @Query("SELECT * FROM habit h JOIN habit_tracker ht ON h.habitId = ht.habitTracker_HabitId " +
            "WHERE habit_UserId = :userId AND h.habitId = :habitId " +
            "AND (date(ht.habitTrackerDate/1000, 'unixepoch') >= date(:startDate/1000, 'unixepoch') " +
            "AND date(ht.habitTrackerDate/1000, 'unixepoch') <= date(:endDate/1000, 'unixepoch'))")
    public List<HabitTracker> getTrackersForHabitWithDateBound(UUID userId, UUID habitId, Date startDate, Date endDate);
}