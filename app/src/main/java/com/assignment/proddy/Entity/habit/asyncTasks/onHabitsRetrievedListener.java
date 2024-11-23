package com.assignment.proddy.Entity.habit.asyncTasks;

import com.assignment.proddy.ObjectMapping.HabitWithTracker;

import java.util.List;

public interface onHabitsRetrievedListener {
    void onHabitsRetrieved(List<HabitWithTracker> habitWithTrackers);
}
