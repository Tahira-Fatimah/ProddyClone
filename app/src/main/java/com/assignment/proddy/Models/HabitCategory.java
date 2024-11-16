package com.assignment.proddy.Models;

import android.graphics.drawable.Drawable;

public class HabitCategory {
    int drawable;
    String categoryName;

    public HabitCategory(int drawable, String categoryName) {
        this.drawable = drawable;
        this.categoryName = categoryName;
    }

    public int getdrawable() {
        return drawable;
    }

    public void setdrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
