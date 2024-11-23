package com.assignment.proddy.Entity.habitTracker;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.assignment.proddy.Converters.DateTypeConverter;
import com.assignment.proddy.Entity.habit.Habit;

import java.util.Date;
import java.util.UUID;

@Entity(tableName = "habit_tracker",
        foreignKeys = @ForeignKey(
                entity = Habit.class,
                parentColumns = "habitId",
                childColumns = "habitTracker_HabitId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        indices = @Index(value = "habitTracker_HabitId")
)
public class HabitTracker {
    @PrimaryKey
    @NonNull private UUID habitTrackerId;

    private UUID habitTracker_HabitId;

    @TypeConverters({DateTypeConverter.class})
    private Date habitTrackerDate;

    private Boolean habitTrackerStatus;

    public HabitTracker(@NonNull UUID habitTrackerId, UUID habitTracker_HabitId, Date habitTrackerDate, Boolean habitTrackerStatus) {
        Log.d("HabitTracker CONSTRUCTOR",String.valueOf(habitTrackerId)+String.valueOf(habitTrackerDate));
        this.habitTrackerId = habitTrackerId;
        this.habitTracker_HabitId = habitTracker_HabitId;
        this.habitTrackerDate = habitTrackerDate;
        this.habitTrackerStatus = habitTrackerStatus;
    }

    public Date getHabitTrackerDate() {
        return habitTrackerDate;
    }

    public void setHabitTrackerDate(Date habitTrackerDate) {
        this.habitTrackerDate = habitTrackerDate;
    }

    public Boolean getHabitTrackerStatus() {
        return habitTrackerStatus;
    }

    public void setHabitTrackerStatus(Boolean habitTrackerStatus) {
        this.habitTrackerStatus = habitTrackerStatus;
    }

    @NonNull
    public UUID getHabitTrackerId() {
        return habitTrackerId;
    }

    public void setHabitTrackerId(@NonNull UUID habitTrackerId) {
        this.habitTrackerId = habitTrackerId;
    }

    public UUID getHabitTracker_HabitId() {
        return habitTracker_HabitId;
    }

    public void setHabitTracker_HabitId(UUID habitTracker_HabitId) {
        this.habitTracker_HabitId = habitTracker_HabitId;
    }

    @Override
    public String toString() {
        return "HabitTracker{" +
                "habitTrackerId=" + habitTrackerId +
                ", habitTracker_HabitId=" + habitTracker_HabitId +
                ", habitTrackerDate=" + (habitTrackerDate != null ? habitTrackerDate.toString() : "No date") +
                ", habitTrackerStatus=" + (habitTrackerStatus != null ? habitTrackerStatus : "No status") +
                '}';
    }

}
