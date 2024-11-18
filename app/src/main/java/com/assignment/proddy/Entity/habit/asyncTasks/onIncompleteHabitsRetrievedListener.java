package com.assignment.proddy.Entity.habit.asyncTasks;

import com.assignment.proddy.ObjectMapping.HabitWithTrackers;

import java.util.List;

public interface onIncompleteHabitsRetrievedListener {
    void onIncompleteHabitsRetrieved(List<HabitWithTrackers> habitsWithTrackers);
}
