package com.assignment.proddy.Utils;

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
}
