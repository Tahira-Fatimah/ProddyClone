package com.assignment.proddy.Utils;

import android.content.Context;

import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.R;

public class DrawableUtils {
    public static int getHabitDrawable(HabitType habitType) {
        switch (habitType) {
            case HEALTH:
                return R.drawable.health;
            case MINDFULNESS:
                return R.drawable.mindfulness;
            case PRODUCTIVITY:
                return R.drawable.productivity;
            case FUN:
                return R.drawable.fun;
            case RELATIONSHIPS:
                return R.drawable.relationships;
            case FINANCES:
                return R.drawable.finances;
            case LEARNING:
                return R.drawable.learning;
            default:
                return R.drawable.finances;
        }
    }

    public static int getCompletedHabitScaleColor(int value, int habitCount) {
        double ratio = (value /(habitCount * 1.0));
        System.out.println("meriratiohaiiii"+String.valueOf(habitCount));
        if(ratio >= 0.1 && ratio <= 0.2){
            return R.color.completed_habits_scale_2;
        } else if (ratio >= 0.2 && ratio <= 0.4) {
            return R.color.completed_habits_scale_3;
        } else if (ratio >= 0.4 && ratio <= 0.7) {
            return R.color.completed_habits_scale_4;
        } else if (ratio >= 0.7 && ratio <= 1) {
            return R.color.completed_habits_scale_5;
        }
        return R.color.completed_habits_scale_1;
    }

    public static int getMoodScaleColor(int value) {
        if(value == 1){
            return R.color.daily_mood_scale_2;
        } else if (value == 2) {
            return R.color.daily_mood_scale_3;
        } else if (value >= 3 && value <= 5) {
            return R.color.daily_mood_scale_4;
        } else if (value >=6) {
            return R.color.daily_mood_scale_5;
        }
        return R.color.daily_mood_scale_1;
    }
}
