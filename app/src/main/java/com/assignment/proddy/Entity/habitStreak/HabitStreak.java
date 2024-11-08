package com.assignment.proddy.Entity.habitStreak;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.assignment.proddy.Entity.habit.Habit;

import java.util.Date;

@Entity(tableName = "habit_streak",
        foreignKeys = @ForeignKey(
                entity = Habit.class,
                parentColumns = "id",
                childColumns = "habitId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class HabitStreak {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int habitId;

    private int crrStreak;

    private int maxStreak;

    private Date lastCompletedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLastCompletedDate() {
        return lastCompletedDate;
    }

    public void setLastCompletedDate(Date lastCompletedDate) {
        this.lastCompletedDate = lastCompletedDate;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public int getMaxStreak() {
        return maxStreak;
    }

    public void setMaxStreak(int maxStreak) {
        this.maxStreak = maxStreak;
    }

    public int getCrrStreak() {
        return crrStreak;
    }

    public void setCrrStreak(int crrStreak) {
        this.crrStreak = crrStreak;
    }

    public HabitStreak(int habitId, int crrStreak, int maxStreak, Date lastCompletedDate) {
        this.habitId = habitId;
        this.crrStreak = crrStreak;
        this.maxStreak = maxStreak;
        this.lastCompletedDate = lastCompletedDate;
    }
}
