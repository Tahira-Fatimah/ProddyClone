package com.assignment.proddy.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.reflection.Reflection;
import com.assignment.proddy.ObjectMapping.ReflectionDateAndRate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Dao
public interface ReflectionDao {

    @Insert
    public Long insert(Reflection reflection);

    @Delete
    public void delete(Reflection reflection);

    @Update
    public int update(Reflection reflection);

    @Query("SELECT * FROM reflection WHERE reflectionId =:uuid")
    public Reflection findReflectionById(UUID uuid);

    @Query("SELECT * FROM reflection r WHERE date(r.reflectionCreationDate / 1000, 'unixepoch') = date(:reflectionCreationDate / 1000, 'unixepoch')")
    public Reflection findReflectionByDate(Date reflectionCreationDate);

    @Query("SELECT AVG(reflectionFeelingRate) AS avgFeelingRate\n" +
            "FROM reflection\n" +
            "WHERE reflectionCreationDate >= strftime('%s', 'now', '-30 days') * 1000\n" +
            "  AND reflectionCreationDate <= strftime('%s', 'now') * 1000;")
    public float getAverageReflectionMoodForAMonth();

    @Query("SELECT r.reflectionFeelingRate as reflectionFeelingRate, r.reflectionCreationDate as reflectionCreationDate \n" +
            "FROM reflection r\n" +
            "WHERE r.reflectionCreationDate >= strftime('%s', 'now', '-30 days') * 1000\n" +
            "  AND r.reflectionCreationDate <= strftime('%s', 'now') * 1000 ORDER BY reflectionCreationDate ASC")
    public List<ReflectionDateAndRate> getReflectionFeelingRatesForLastMonth();

    @Query("SELECT r.reflectionFeelingRate as reflectionFeelingRate, r.reflectionCreationDate as reflectionCreationDate \n" +
            "FROM reflection r\n" +
            "WHERE r.reflectionCreationDate >= strftime('%s', 'now', '-6 days') * 1000\n" +
            "  AND r.reflectionCreationDate <= strftime('%s', 'now') * 1000 ORDER BY reflectionCreationDate ASC")
    public List<ReflectionDateAndRate> getReflectionFeelingRatesForLastWeek();

    @Query("SELECT r.reflectionFeelingRate as reflectionFeelingRate, r.reflectionCreationDate as reflectionCreationDate \n" +
            "FROM reflection r\n" +
            "WHERE R.reflection_UserId = :userId " +
            "AND(date(r.reflectionCreationDate/1000, 'unixepoch') >= date(:startDate/1000, 'unixepoch') " +
            "AND date(r.reflectionCreationDate/1000, 'unixepoch') <= date(:endDate/1000, 'unixepoch'))" +
            "ORDER BY reflectionCreationDate ASC")
    public List<ReflectionDateAndRate> getReflectionFeelingRatesForDateBound(UUID userId, Date startDate, Date endDate);

}
