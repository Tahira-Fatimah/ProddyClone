package com.assignment.proddy.Entity.reminder;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.assignment.proddy.Entity.habit.Habit;

import java.sql.Time;

@Entity(tableName = "reminder",
        foreignKeys = @ForeignKey(
                entity = Habit.class,
                parentColumns = "id",
                childColumns = "habitId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int habitId;

    private Time time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHabitId() {
        return habitId;
    }

    public void setHabitId(int habitId) {
        this.habitId = habitId;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Reminder(int habitId, Time time) {
        this.habitId = habitId;
        this.time = time;
    }
}
