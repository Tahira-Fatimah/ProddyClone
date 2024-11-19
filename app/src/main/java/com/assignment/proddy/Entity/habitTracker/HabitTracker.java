package com.assignment.proddy.Entity.habitTracker;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.assignment.proddy.Converters.DateTypeConverter;
import com.assignment.proddy.Entity.habit.Habit;

import java.util.Date;

@Entity(tableName = "habit_tracker",
        foreignKeys = @ForeignKey(
                entity = Habit.class,
                parentColumns = "id",
                childColumns = "habitId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        indices = @Index(value = "habitId")
)
public class HabitTracker {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int habitId;

    @TypeConverters({DateTypeConverter.class})
    private Date date;

    private Boolean status;

    public HabitTracker(int habitId, Date date, Boolean status) {
        this.habitId = habitId;
        this.date = date;
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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


}
