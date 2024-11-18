package com.assignment.proddy.Utils;

import com.assignment.proddy.Entity.habit.HabitType;
import com.assignment.proddy.Models.HabitCategory;
import com.assignment.proddy.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {
    public static List<String> getAllDays(){
        return new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
    }

    public static List<String> getTimeData(){
        List<String> timeData = new ArrayList<>();
        timeData.add("AM");
        timeData.add("PM");
        return timeData;
    }

    public static List<HabitCategory> getHabitCategories(){
        List<HabitCategory> habitCategories = new ArrayList<>(Arrays.asList(
                new HabitCategory(R.drawable.health, HabitType.HEALTH),
                new HabitCategory(R.drawable.mindfulness, HabitType.MINDFULNESS),
                new HabitCategory(R.drawable.productivity, HabitType.PRODUCTIVITY),
                new HabitCategory(R.drawable.fun, HabitType.FUN),
                new HabitCategory(R.drawable.relationships, HabitType.RELATIONSHIPS),
                new HabitCategory(R.drawable.finances, HabitType.FINANCES),
                new HabitCategory(R.drawable.learning, HabitType.LEARNING)
        ));

        return habitCategories;
    }
}
