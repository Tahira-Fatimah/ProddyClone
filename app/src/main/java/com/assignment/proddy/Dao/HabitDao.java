package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.ObjectMapping.HabitStack;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.ObjectMapping.HabitWithTracker;
import com.assignment.proddy.ObjectMapping.HabitWithTrackers;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Dao
public interface HabitDao {
    @Insert
    public Long insert(Habit habit);

    @Delete

    public void delete(Habit habit);

    @Update
    public void update(Habit habit);

    @Query("SELECT * FROM habit WHERE habitId =:habitId")
    public HabitStack getHabitStack(UUID habitId);

//    @Query("SELECT * FROM habit h " +
//            "JOIN habit_tracker ht ON h.id = ht.habitId " +
//            "WHERE ht.status = 0 AND date(ht.date/1000, 'unixepoch') = date(:today/1000, 'unixepoch') AND h.userId = :userId")
//    public List<HabitWithTrackers> getIncompleteHabits(int userId, Date today);

    @Query("SELECT * FROM habit h WHERE h.habitName = :userId ")
    public Habit getCompletedHabits(String userId);

    @Query(
            "SELECT " +
                    "h.habitId AS habitId, " +
                    "h.habit_UserId AS habit_UserId, " +
                    "h.habitName AS habitName, " +
                    "h.habitReason AS habitReason, " +
                    "h.habitDays AS habitDays, " +
                    "h.habitType AS habitType, " +
                    "h.habitReminderTime AS habitReminderTime, " +
                    "ht.habitTrackerId AS habitTrackerId, " +
                    "ht.habitTracker_HabitId AS habitTracker_HabitId, " +
                    "ht.habitTrackerDate AS habitTrackerDate, " +
                    "ht.habitTrackerStatus AS habitTrackerStatus " +
                    "FROM habit h " +
                    "LEFT JOIN " +
                    "(SELECT * FROM habit_tracker " +
                    "WHERE date(habitTrackerDate / 1000, 'unixepoch') = date(:today / 1000, 'unixepoch')) ht " + // added space here
                    "ON h.habitId = ht.habitTracker_HabitId " +
                    "WHERE h.habit_UserId = :userId "+
                    "AND h.habitDays LIKE '%' || " +
                    "CASE strftime('%w', date(:today / 1000, 'unixepoch')) " +
                    "WHEN '0' THEN 'Sunday' " +
                    "WHEN '1' THEN 'Monday' " +
                    "WHEN '2' THEN 'Tuesday' " +
                    "WHEN '3' THEN 'Wednesday' " +
                    "WHEN '4' THEN 'Thursday' " +
                    "WHEN '5' THEN 'Friday' " +
                    "WHEN '6' THEN 'Saturday' END || '%'")
    public List<HabitWithTracker> getHabits(UUID userId, Date today);

    @Query("SELECT * FROM habit WHERE habit_UserId = :userId")
    public List<Habit> getHabitsForUser(UUID userId);
}