package com.assignment.proddy.Entity.habit;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.assignment.proddy.Entity.user.User;


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

    @TypeConverters(HabitTypeConverter.class)
    private HabitType habitType;

    public Habit(String name, String reason, String schedule, HabitType habitType, int userId) {
        this.name = name;
        this.reason = reason;
        this.schedule = schedule;
        this.habitType = habitType;
        this.userId = userId;
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
