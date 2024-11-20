package com.assignment.proddy.ObjectMapping;

import android.util.Log;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;

import java.util.List;

public class HabitWithTrackers {
    @Embedded
    private Habit habit;

    @Relation(
            parentColumn = "habitId",
            entityColumn = "habitTracker_HabitId"
    )
    private List<HabitTracker> habitTrackers;

    public HabitWithTrackers(Habit habit, List<HabitTracker> habitTrackers) {
        Log.d("HabitWithTrackers CONSTRUCTOR",String.valueOf(habit.getHabitName()));
        this.habit = habit;
        this.habitTrackers = habitTrackers;
    }

    public Habit getHabit() {
        return habit;
    }

    public Boolean getHabitWithTracker(){
        Log.d("getHabitWithTrackers",String.valueOf(habitTrackers.size()));
        if(habitTrackers.isEmpty() || habitTrackers.contains(null)){
            return false;
        }
        return true;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public List<HabitTracker> getHabitTrackers() {
        return habitTrackers;
    }

    public void setHabitTrackers(List<HabitTracker> habitTrackers) {
        this.habitTrackers = habitTrackers;
    }
}
