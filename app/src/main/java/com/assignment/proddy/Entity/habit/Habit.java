package com.assignment.proddy.Entity.habit;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.assignment.proddy.Entity.user.User;

import java.sql.Time;
import java.util.List;

@Entity(tableName = "habit",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class Habit {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;

    private String name;

    private String reason;

    private String  schedule;

    private List<String> habitDays;

    @TypeConverters(HabitTypeConverter.class)
    private HabitType habitType;

    private Time reminderTime;

    public Habit(String name, String reason, String schedule, HabitType habitType, int userId, Time reminderTime) {
        this.name = name;
        this.reason = reason;
        this.schedule = schedule;
        this.habitType = habitType;
        this.userId = userId;
        this.reminderTime=reminderTime;
    }

    public Time getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Time reminderTime) {
        this.reminderTime = reminderTime;
    }

    public List<String> getHabitDays() {
        return habitDays;
    }

    public void setHabitDays(List<String> habitDays) {
        this.habitDays = habitDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public HabitType getHabitType() {
        return habitType;
    }

    public void setHabitType(HabitType habitType) {
        this.habitType = habitType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    @Override
    @NonNull
    public String toString() {
        return "Habit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reason='" + reason + '\'' +
                ", schedule=" + schedule +
                ", habitType=" + habitType +
                '}';
    }

}
