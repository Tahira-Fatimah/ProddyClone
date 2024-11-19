package com.assignment.proddy.Utils;

import android.graphics.Color;

import com.assignment.proddy.Entity.Lesson;
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


    public static List<Lesson> getLessons(){
        List<Lesson> lessons = new ArrayList<>(Arrays.asList(
                new Lesson(1, R.drawable.lesson1, "Tiny Habits", "Why easy is better than difficult", new Lesson.Theme(Color.parseColor("#1A5001"), Color.parseColor("#889D82"), Color.parseColor("#58754F")), R.raw.lesson1, R.drawable.lesson1_gradient),
                new Lesson(2, R.drawable.lesson2, "Mindset", "Building a growth mindset", new Lesson.Theme(Color.parseColor("#6C5179"), Color.parseColor("#B6A8BC"), Color.parseColor("#988581")), R.raw.lesson1, R.drawable.lesson2_gradient),
                new Lesson(3, R.drawable.lesson3, "Habit Hacks", "Saying Goodbye to Procrastination", new Lesson.Theme(Color.parseColor("#1C5B84"), Color.parseColor("#8EADC2"), Color.parseColor("#698CA9")),  R.raw.lesson1, R.drawable.lesson3_gradient),
                new Lesson(4, R.drawable.lesson41, "Your Identity", "How long term changes happen", new Lesson.Theme(Color.parseColor("#674E31"), Color.parseColor("#B5A99C"), Color.parseColor("#988581")),  R.raw.lesson1, R.drawable.lesson4_gradient),
                new Lesson(5, R.drawable.lesson5, "The Habit Loop", "Habits and your brain", new Lesson.Theme(Color.parseColor("#542924"), Color.parseColor("#AA9492"), Color.parseColor("#876965")), R.raw.lesson1, R.drawable.lesson5_gradient),
                new Lesson(6, R.drawable.lesson6, "Your Environment", "Going beyond willpower", new Lesson.Theme(Color.parseColor("#261227"), Color.parseColor("#938993"), Color.parseColor("#988581")), R.raw.lesson1, R.drawable.lesson6_gradient),
                new Lesson(7, R.drawable.lesson7, "Habit Waves", "Creating perfect days", new Lesson.Theme(Color.parseColor("#7793C3"), Color.parseColor("#BBC9E1"), Color.parseColor("#A093D5")), R.raw.lesson1, R.drawable.lesson7_gradient)


                ));
        return lessons;
    }

    public static List<String> getHourData(){
        List<String> hourData = new ArrayList<>();
        for (int i = 1; i <= 12; i++)
            hourData.add(String.format("%02d", i));
        return hourData;
    }

    public static List<String> getMinData(){
        List<String> minData = new ArrayList<>();
        for (int i = 0; i <= 60; i++)
            minData.add(String.format("%02d", i));

        return minData;
    }

    public static int getHabitCategoryIndex(HabitType habitType) {
        List<HabitCategory> habitCategories = StringUtils.getHabitCategories();
        for (int i = 0; i < habitCategories.size(); i++) {
            if (habitCategories.get(i).getHabitType() == habitType) {
                return i;
            }
        }
        return -1;
    }
}
