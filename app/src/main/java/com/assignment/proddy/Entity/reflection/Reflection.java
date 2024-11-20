package com.assignment.proddy.Entity.reflection;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.assignment.proddy.Converters.ReflectionActivitiesConverter;
import com.assignment.proddy.Converters.ReflectionFeelingsConverter;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.user.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(
        tableName = "reflection",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "userId",
                childColumns = "reflection_UserId",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
)
public class Reflection {
    @PrimaryKey
    @NonNull private UUID reflectionId;

    private UUID reflection_UserId;

    @TypeConverters({ReflectionFeelingsConverter.class})
    private List<ReflectionFeelings> reflectionFeelingsList;

    private int reflectionFeelingRate;

    @TypeConverters({ReflectionActivitiesConverter.class})
    private List<ReflectionActivities> reflectionActivitiesList;

    private String reflectionThoughts;

    private Date reflectionCreationDate;

    public Reflection(@NonNull UUID reflectionId, UUID reflection_UserId, List<ReflectionFeelings> reflectionFeelingsList, int reflectionFeelingRate, List<ReflectionActivities> reflectionActivitiesList, String reflectionThoughts, Date reflectionCreationDate) {
        this.reflectionId = reflectionId;
        this.reflection_UserId = reflection_UserId;
        this.reflectionFeelingsList = reflectionFeelingsList;
        this.reflectionFeelingRate = reflectionFeelingRate;
        this.reflectionActivitiesList = reflectionActivitiesList;
        this.reflectionThoughts = reflectionThoughts;
        this.reflectionCreationDate = reflectionCreationDate;
    }


    public Date getReflectionCreationDate() {
        return reflectionCreationDate;
    }

    public void setReflectionCreationDate(Date reflectionCreationDate) {
        this.reflectionCreationDate = reflectionCreationDate;
    }

    @NonNull
    public UUID getReflectionId() {
        return reflectionId;
    }

    public void setReflectionId(@NonNull UUID reflectionId) {
        this.reflectionId = reflectionId;
    }

    public UUID getReflection_UserId() {
        return reflection_UserId;
    }

    public void setReflection_UserId(UUID reflection_UserId) {
        this.reflection_UserId = reflection_UserId;
    }

    public List<ReflectionFeelings> getReflectionFeelingsList() {
        return reflectionFeelingsList;
    }

    public void setReflectionFeelingsList(List<ReflectionFeelings> reflectionFeelingsList) {
        this.reflectionFeelingsList = reflectionFeelingsList;
    }

    public int getReflectionFeelingRate() {
        return reflectionFeelingRate;
    }

    public void setReflectionFeelingRate(int reflectionFeelingRate) {
        this.reflectionFeelingRate = reflectionFeelingRate;
    }

    public List<ReflectionActivities> getReflectionActivitiesList() {
        return reflectionActivitiesList;
    }

    public void setReflectionActivitiesList(List<ReflectionActivities> reflectionActivitiesList) {
        this.reflectionActivitiesList = reflectionActivitiesList;
    }

    public String getReflectionThoughts() {
        return reflectionThoughts;
    }

    public void setReflectionThoughts(String reflectionThoughts) {
        this.reflectionThoughts = reflectionThoughts;
    }

    @Override
    public String toString() {
        return "Reflection{" +
                "reflectionId=" + reflectionId +
                ", reflection_UserId=" + reflection_UserId +
                ", reflectionFeelingsList=" + reflectionFeelingsList +
                ", reflectionFeelingRate=" + reflectionFeelingRate +
                ", reflectionActivitiesList=" + reflectionActivitiesList +
                ", reflectionThoughts='" + reflectionThoughts + '\'' +
                '}';
    }
}
