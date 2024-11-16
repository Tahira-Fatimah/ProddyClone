package com.assignment.proddy.Models;

import com.assignment.proddy.Entity.habit.HabitType;

public class HabitCategory {
    int drawable;
    HabitType habitType;

    public HabitCategory(int drawable, HabitType categoryName) {
        this.drawable = drawable;
        this.habitType = categoryName;
    }

    public int getdrawable() {
        return drawable;
    }

    public void setdrawable(int drawable) {
        this.drawable = drawable;
    }

    public HabitType getHabitType() {
        return habitType;
    }

    public void setHabitType(HabitType habitType) {
        this.habitType = habitType;
    }
}
