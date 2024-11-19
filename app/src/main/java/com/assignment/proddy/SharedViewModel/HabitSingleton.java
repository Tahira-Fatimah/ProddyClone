package com.assignment.proddy.SharedViewModel;

import com.assignment.proddy.Entity.habit.HabitType;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

public class HabitSingleton {

    private static HabitSingleton instance;

    private UUID id;
    private int userId;
    private String name;
    private String reason;
    private List<String> habitDays;
    private HabitType habitType;
    private Time reminderTime;

    private HabitSingleton() {
        id = null;
        userId = -1;
        name = "";
        reason = "";
        habitDays = null;
        habitType = null;
        reminderTime = null;
    }

    public static synchronized HabitSingleton getInstance() {
        if (instance == null) {
            instance = new HabitSingleton();
        }
        return instance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public List<String> getHabitDays() {
        return habitDays;
    }

    public void setHabitDays(List<String> habitDays) {
        this.habitDays = habitDays;
    }

    public HabitType getHabitType() {
        return habitType;
    }

    public void setHabitType(HabitType habitType) {
        this.habitType = habitType;
    }

    public Time getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Time reminderTime) {
        this.reminderTime = reminderTime;
    }

    public void clearHabit() {
        this.id = null;
        this.userId = -1;
        this.name = "";
        this.reason = "";
        this.habitDays = null;
        this.habitType = null;
        this.reminderTime = null;
    }

    public void setHabitDetails(UUID id, int userId, String name, String reason, List<String> habitDays, HabitType habitType, Time reminderTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.reason = reason;
        this.habitDays = habitDays;
        this.habitType = habitType;
        this.reminderTime = reminderTime;
    }

    @Override
    public String toString() {
        return "HabitSingleton{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", reason='" + reason + '\'' +
                ", habitDays=" + habitDays +
                ", habitType=" + habitType +
                ", reminderTime=" + reminderTime +
                '}';
    }
}
