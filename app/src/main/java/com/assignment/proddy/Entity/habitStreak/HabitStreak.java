package com.assignment.proddy.Entity.habitStreak;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.assignment.proddy.Entity.habit.Habit;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "habit_streak",
        foreignKeys = @ForeignKey(
                entity = Habit.class,
                parentColumns = "habitId",
                childColumns = "habitStreak_HabitId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class HabitStreak {
    @PrimaryKey
    @NonNull private UUID habitStreakId;

    private UUID habitStreak_HabitId;

    private int habitStreakCrrStreak;

    private int habitStreakMaxStreak;

    private Date habitStreakLastCompletedDate;

    @NonNull
    public UUID getHabitStreakId() {
        return habitStreakId;
    }

    public void setHabitStreakId(@NonNull UUID habitStreakId) {
        this.habitStreakId = habitStreakId;
    }

    public Date getHabitStreakLastCompletedDate() {
        return habitStreakLastCompletedDate;
    }

    public void setHabitStreakLastCompletedDate(Date habitStreakLastCompletedDate) {
        this.habitStreakLastCompletedDate = habitStreakLastCompletedDate;
    }

    public UUID getHabitStreak_HabitId() {
        return habitStreak_HabitId;
    }

    public void setHabitStreak_HabitId(UUID habitStreak_HabitId) {
        this.habitStreak_HabitId = habitStreak_HabitId;
    }

    public int getHabitStreakMaxStreak() {
        return habitStreakMaxStreak;
    }

    public void setHabitStreakMaxStreak(int habitStreakMaxStreak) {
        this.habitStreakMaxStreak = habitStreakMaxStreak;
    }

    public int getHabitStreakCrrStreak() {
        return habitStreakCrrStreak;
    }

    public void setHabitStreakCrrStreak(int habitStreakCrrStreak) {
        this.habitStreakCrrStreak = habitStreakCrrStreak;
    }

    public HabitStreak(UUID habitStreakId, UUID habitStreak_HabitId, int habitStreakCrrStreak, int habitStreakMaxStreak, Date habitStreakLastCompletedDate) {
        this.habitStreakId = habitStreakId;
        this.habitStreak_HabitId = habitStreak_HabitId;
        this.habitStreakCrrStreak = habitStreakCrrStreak;
        this.habitStreakMaxStreak = habitStreakMaxStreak;
        this.habitStreakLastCompletedDate = habitStreakLastCompletedDate;
    }
}
