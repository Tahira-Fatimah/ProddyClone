package com.assignment.proddy.Converters;

import androidx.room.TypeConverter;

import com.assignment.proddy.Entity.habit.HabitType;

public class HabitTypeConverter {

    @TypeConverter
    public static String fromHabitType(HabitType habitType) {
        if (habitType == null) {
            return null; // Handle null case
        }
        return habitType.getDisplayName(); // Convert enum to string
    }

    @TypeConverter
    public static HabitType toHabitType(String displayName) {
        if (displayName == null) {
            return null; // Handle null case
        }
        return HabitType.fromString(displayName); // Convert string back to enum
    }
}
