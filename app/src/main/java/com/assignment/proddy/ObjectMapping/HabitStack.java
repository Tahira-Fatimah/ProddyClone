package com.assignment.proddy.ObjectMapping;

import android.util.Log;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.assignment.proddy.Entity.habit.Habit;
import com.assignment.proddy.Entity.habitStep.HabitStep;

import java.io.Serializable;
import java.util.List;

public class HabitStack implements Serializable {
    @Embedded
    private Habit habit;

    @Relation(
            parentColumn = "habitId",
            entityColumn = "habitStep_HabitId"
    )

    private List<HabitStep> habitSteps;

    public List<HabitStep> getHabitSteps() {
        return habitSteps;
    }

    public void setHabitSteps(List<HabitStep> habitSteps) {
        this.habitSteps = habitSteps;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public void displayHabitStack(){
        System.out.println(habit.toString());
        for(HabitStep habitStep : habitSteps){
            Log.d("habitSteps", habitStep.toString());
        }
    }
}
