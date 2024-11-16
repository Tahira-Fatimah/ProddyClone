package com.assignment.proddy.Converters;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class HabitDaysConverter {

    @TypeConverter
    public static String fromList(List<String> habitDays) {
        if (habitDays == null) return null;
        return String.join(",", habitDays); // Join the list elements into a comma-separated string
    }

    @TypeConverter
    public static List<String> fromString(String habitDays) {
        if (habitDays == null || habitDays.isEmpty()) return new ArrayList<>();
        String[] array = habitDays.split(","); // Split the comma-separated string into a list
        List<String> list = new ArrayList<>();
        for (String day : array) {
            list.add(day);
        }
        return list;
    }
}
