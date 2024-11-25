package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habitStep.HabitStep;

import java.util.List;
import java.util.UUID;

@Dao
public interface HabitStepDao {
    @Insert
    public Long insert(HabitStep habitStep);

    @Delete
    public void delete(HabitStep habitStep);

    @Update
    public Void update(HabitStep habitStep);

    @Insert
    public Long[] insertAll(List<HabitStep> habitStepList);

    @Query("SELECT SUM(hs.habitStepTime) FROM habit_step hs " +
            "JOIN (SELECT h.habitId AS habitIds " +
            "FROM habit_tracker ht " +
            "JOIN habit h ON ht.habitTracker_HabitId = h.habitId " +
            "JOIN user u ON h.habit_UserId = u.userId " +
            "WHERE u.userId = :userId " +
            "GROUP BY h.habitId) AS subquery " +
            "ON hs.habitStep_HabitId = subquery.habitIds")
    public int getTotalTimeSpent(UUID userId);

}
