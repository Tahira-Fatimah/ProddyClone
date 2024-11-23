package com.assignment.proddy.Entity.habitStep;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.assignment.proddy.Entity.habit.Habit;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "habit_step",
        foreignKeys = @ForeignKey(
                entity = Habit.class,
                parentColumns = "habitId",
                childColumns = "habitStep_HabitId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        indices = @Index(value = "habitStep_HabitId")
)
public class HabitStep implements Serializable {
    @PrimaryKey
    @NonNull private UUID habitStepId;

    private UUID habitStep_HabitId;

    private int habitStepNum;

    private String habitStepDescription;

    private int habitStepTime;

    private String habitStepEmoji;

    public HabitStep() {

    }

    public HabitStep(@NonNull UUID habitStepId, UUID habitStep_HabitId, int habitStepNum, String habitStepDescription, int habitStepTime, String habitStepEmoji) {
        this.habitStepId = habitStepId;
        this.habitStep_HabitId = habitStep_HabitId;
        this.habitStepNum = habitStepNum;
        this.habitStepDescription = habitStepDescription;
        this.habitStepTime = habitStepTime;
        this.habitStepEmoji = habitStepEmoji;
    }

    @NonNull
    public UUID getHabitStepId() {
        return habitStepId;
    }

    public void setHabitStepId(@NonNull UUID habitStepId) {
        this.habitStepId = habitStepId;
    }

    public UUID getHabitStep_HabitId() {
        return habitStep_HabitId;
    }

    public void setHabitStep_HabitId(UUID habitStep_HabitId) {
        this.habitStep_HabitId = habitStep_HabitId;
    }

    public int getHabitStepNum() {
        return habitStepNum;
    }

    public void setHabitStepNum(int habitStepNum) {
        this.habitStepNum = habitStepNum;
    }

    public String getHabitStepDescription() {
        return habitStepDescription;
    }

    public void setHabitStepDescription(String habitStepDescription) {
        this.habitStepDescription = habitStepDescription;
    }

    public int getHabitStepTime() {
        return habitStepTime;
    }

    public void setHabitStepTime(int habitStepTime) {
        this.habitStepTime = habitStepTime;
    }

    public String getHabitStepEmoji() {
        return habitStepEmoji;
    }

    public void setHabitStepEmoji(String habitStepEmoji) {
        this.habitStepEmoji = habitStepEmoji;
    }


    @NonNull
    public String toString() {
        return "HabitStep{" +
                "id=" + habitStepId +
                ", habitId=" + habitStep_HabitId +
                ", stepNum=" + habitStepNum +
                ", description='" + habitStepDescription + '\'' +
                ", time=" + habitStepTime +
                ", emoji='" + habitStepEmoji + '\'' +
                '}';
    }

}
