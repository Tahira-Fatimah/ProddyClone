package com.assignment.proddy.Entity.habit.asyncTasks;

import com.assignment.proddy.Entity.habit.Habit;

import java.util.List;

public interface onHabitsRetrievedListener {
    void onHabitsRetrieved(List<Habit> habits);
}
