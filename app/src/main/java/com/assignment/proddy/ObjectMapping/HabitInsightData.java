package com.assignment.proddy.ObjectMapping;

import com.assignment.proddy.Entity.habitTracker.HabitTracker;

import java.util.List;

public class HabitInsightData {

    public HabitWithStreakAndTime habitWithStreakAndTime;
    public List<HabitTracker> habitTrackerList;

    public HabitInsightData(HabitWithStreakAndTime habitWithStreakAndTime, List<HabitTracker> habitTrackerList) {
        this.habitWithStreakAndTime = habitWithStreakAndTime;
        this.habitTrackerList = habitTrackerList;
    }

    public HabitWithStreakAndTime getHabitWithStreak() {
        return habitWithStreakAndTime;
    }

    public List<HabitTracker> getHabitTrackerList() {
        return habitTrackerList;
    }

    public void setHabitWithStreak(HabitWithStreakAndTime habitWithStreakAndTime) {
        this.habitWithStreakAndTime = habitWithStreakAndTime;
    }

    public void setHabitTrackerList(List<HabitTracker> habitTrackerList) {
        this.habitTrackerList = habitTrackerList;
    }

    public void addTrackerToList(HabitTracker habitTracker){
        habitTrackerList.add(habitTracker);
    }
}
