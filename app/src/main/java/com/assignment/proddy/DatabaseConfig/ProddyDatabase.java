package com.assignment.proddy.DatabaseConfig;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.assignment.proddy.Converters.Converters;
import com.assignment.proddy.Converters.HabitDaysConverter;
import com.assignment.proddy.Converters.DateTypeConverter;
import com.assignment.proddy.Dao.HabitDao;
import com.assignment.proddy.Dao.HabitStepDao;
import com.assignment.proddy.Dao.HabitTrackerDao;
import com.assignment.proddy.Dao.UserDao;
import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habitStep.HabitStep;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;
import com.assignment.proddy.Entity.user.User;


@Database(entities = {User.class, Habit.class, HabitStep.class, HabitTracker.class}, version = 1)
@TypeConverters({Converters.class, DateTypeConverter.class, HabitDaysConverter.class})
public abstract class ProddyDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract HabitDao habitDao();
    public abstract HabitStepDao habitStepDao();
    public abstract HabitTrackerDao habitTrackerDao();
}
