package com.assignment.proddy.Entity.habit;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.assignment.proddy.Converters.HabitTypeConverter;
import com.assignment.proddy.Entity.user.User;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Entity(tableName = "habit",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "userId",
                childColumns = "habit_UserId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class Habit implements Serializable {
    @PrimaryKey
    @NonNull private UUID habitId;

    private UUID habit_UserId;

    private String habitName;

    private String habitReason;

//    private String schedule;

    private List<String> habitDays;

    @TypeConverters(HabitTypeConverter.class)
    private HabitType habitType;

    private Time habitReminderTime;

    public Habit(@NonNull UUID habitId, String habitName, String habitReason, HabitType habitType, UUID habit_UserId, Time habitReminderTime, List<String> habitDays) {
        this.habitName = habitName;
        this.habitReason = habitReason;
//        this.schedule = schedule;
        this.habitId = habitId;
        this.habitType = habitType;
        this.habit_UserId = habit_UserId;
        this.habitReminderTime = habitReminderTime;
        this.habitDays = habitDays;
    }

    public Time getHabitReminderTime() {
        return habitReminderTime;
    }

    public void setHabitReminderTime(Time habitReminderTime) {
        this.habitReminderTime = habitReminderTime;
    }

    public List<String> getHabitDays() {
        return habitDays;
    }

    public void setHabitDays(List<String> habitDays) {
        this.habitDays = habitDays;
    }

    @NonNull
    public UUID getHabitId() {
        return habitId;
    }

    public void setHabitId(@NonNull UUID habitId) {
        this.habitId = habitId;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getHabitReason() {
        return habitReason;
    }

    public void setHabitReason(String habitReason) {
        this.habitReason = habitReason;
    }

//    public String getSchedule() {
//        return schedule;
//    }
//
//    public void setSchedule(String schedule) {
//        this.schedule = schedule;
//    }

    public HabitType getHabitType() {
        return habitType;
    }

    public void setHabitType(HabitType habitType) {
        this.habitType = habitType;
    }

    public UUID getHabit_UserId() {
        return habit_UserId;
    }

    public void setHabit_UserId(UUID habit_UserId) {
        this.habit_UserId = habit_UserId;
    }

//    @Override
    @NonNull
    public String toString() {
        return "Habit{" +
                "id=" + habitId +
                ", name='" + habitName + '\'' +
                ", reason='" + habitReason + '\'' +
                ", habitType=" + habitType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the object references are the same
        if (this == obj) return true;

        // Check if the object is null or not an instance of Habit
        if (obj == null || getClass() != obj.getClass()) return false;

        // Cast the object to Habit
        Habit habit = (Habit) obj;

        // Check if habitId is equal
        return habitId.equals(habit.habitId);
    }

    @Override
    public int hashCode() {
        return habitId.hashCode();
    }


}
