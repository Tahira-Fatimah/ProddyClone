package com.assignment.proddy.ObjectMapping;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habitTracker.HabitTracker;

import java.util.List;

public class HabitWithTrackers {
    @Embedded
    private Habit habit;

    @Relation(
            parentColumn = "id",
            entityColumn = "habitId"
    )
    private List<HabitTracker> habitTrackers;

    public HabitWithTrackers(Habit habit, List<HabitTracker> habitTrackers) {
        this.habit = habit;
        this.habitTrackers = habitTrackers;
    }

    public Habit getHabit() {
        return habit;
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
