package com.assignment.proddy.ObjectMapping;


import androidx.annotation.NonNull;
import androidx.room.TypeConverters;

import com.assignment.proddy.Converters.HabitTypeConverter;

import java.util.List;
import java.util.UUID;

public class HabitWithStreakAndTime {

    // Attributes from Habit
    @NonNull
    private UUID habitId;
    private String habitName;
    private List<String> habitDays;
    @TypeConverters(HabitTypeConverter.class)

    @NonNull
    private UUID habitStreak_HabitId;
    private int habitStreakCrrStreak;
    private int habitStreakMaxStreak;

    private int timeSpent;


    public HabitWithStreakAndTime(@NonNull UUID habitId, String habitName, List<String> habitDays, @NonNull UUID habitStreak_HabitId, int habitStreakCrrStreak, int habitStreakMaxStreak, int timeSpent) {
        this.habitId = habitId;
        this.habitName = habitName;
        this.habitDays = habitDays;
        this.habitStreak_HabitId = habitStreak_HabitId;
        this.habitStreakCrrStreak = habitStreakCrrStreak;
        this.habitStreakMaxStreak = habitStreakMaxStreak;
        this.timeSpent = timeSpent;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    @NonNull
    public UUID getHabitId() {
        return habitId;
    }

    public String getHabitName() {
        return habitName;
    }

    public List<String> getHabitDays() {
        return habitDays;
    }

    @NonNull
    public UUID getHabitStreak_HabitId() {
        return habitStreak_HabitId;
    }

    public int getHabitStreakCrrStreak() {
        return habitStreakCrrStreak;
    }

    public int getHabitStreakMaxStreak() {
        return habitStreakMaxStreak;
    }

    public void setHabitId(@NonNull UUID habitId) {
        this.habitId = habitId;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public void setHabitDays(List<String> habitDays) {
        this.habitDays = habitDays;
    }

    public void setHabitStreak_HabitId(@NonNull UUID habitStreak_HabitId) {
        this.habitStreak_HabitId = habitStreak_HabitId;
    }

    public void setHabitStreakCrrStreak(int habitStreakCrrStreak) {
        this.habitStreakCrrStreak = habitStreakCrrStreak;
    }

    public void setHabitStreakMaxStreak(int habitStreakMaxStreak) {
        this.habitStreakMaxStreak = habitStreakMaxStreak;
    }

    @Override
    public String toString() {
        return "HabitInsightData{" +
                "habitId=" + habitId +
                ", habitName='" + habitName + '\'' +
                ", habitDays=" + habitDays +
                ", habitStreak_HabitId=" + habitStreak_HabitId +
                ", habitStreakCrrStreak=" + habitStreakCrrStreak +
                ", habitStreakMaxStreak=" + habitStreakMaxStreak +
                '}';
    }

}
