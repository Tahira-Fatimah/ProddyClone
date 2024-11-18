package com.assignment.proddy.Entity.habit.asyncTasks;

import com.assignment.proddy.ObjectMapping.HabitWithTrackers;

import java.util.List;

public interface onCompletedHabitsRetrievedListener {
    void onCompletedHabitsRetrieved(List<HabitWithTrackers> habitsWithTrackers);
}
