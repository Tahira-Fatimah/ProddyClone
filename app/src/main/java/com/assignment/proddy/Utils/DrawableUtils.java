package com.assignment.proddy.Utils;

import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.R;

import java.util.Objects;

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

    public static int getReflectionEmojiDrawable(int feelingRate){
        switch (feelingRate){
            case 1:
                return R.drawable.mood1;
            case 2:
                return R.drawable.mood2;
            case 3:
                return R.drawable.mood3;
            case 4:
                return R.drawable.mood4;
            case 5:
                return R.drawable.mood5;
            default:
                return R.drawable.mood5;
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
        if(value == 2){
            return R.color.daily_mood_scale_2;
        } else if (value == 3) {
            return R.color.daily_mood_scale_3;
        } else if (value == 4) {
            return R.color.daily_mood_scale_4;
        } else if (value ==5) {
            return R.color.daily_mood_scale_5;
        }
        return R.color.daily_mood_scale_1;
    }

    public static int getCalendarItemColor(String status){
        int color = R.color.grid_item_calendar;
        if(Objects.equals(status, "NONE") || Objects.equals(status, "NOT COMPLETED")){
            color = R.color.grid_item_calendar;
        } else if (Objects.equals(status, "SOME") || Objects.equals(status, "TODAY")) {
            color = R.color.today_item_calendar;
        } else if (Objects.equals(status, "ALL") || Objects.equals(status, "COMPLETED")){
            color = R.color.completed_habits_scale_1;
        } else if (Objects.equals(status, "NOT TRACKED")){
            color = R.color.checkmark_purple;
        }
        return color;
    }
}
