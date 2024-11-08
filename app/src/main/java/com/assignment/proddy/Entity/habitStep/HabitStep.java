package com.assignment.proddy.Entity.habitStep;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "habit_step",
        foreignKeys = @ForeignKey(
                entity = HabitStep.class,
                parentColumns = "id",
                childColumns = "habitId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class HabitStep {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int habitId;

    private int stepNum;

    private String description;

    private int time;

    private String emoji;

    public HabitStep(int habitId, int stepNum, String description, int time, String emoji) {
        this.habitId = habitId;
        this.stepNum = stepNum;
        this.description = description;
        this.time = time;
        this.emoji = emoji;
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

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }


    @NonNull
    public String toString() {
        return "HabitStep{" +
                "id=" + id +
                ", habitId=" + habitId +
                ", stepNum=" + stepNum +
                ", description='" + description + '\'' +
                ", time=" + time +
                ", emoji='" + emoji + '\'' +
                '}';
    }

}
