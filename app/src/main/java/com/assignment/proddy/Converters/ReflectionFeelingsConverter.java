package com.assignment.proddy.Converters;

import androidx.room.TypeConverter;

import com.assignment.proddy.Entity.reflection.ReflectionFeelings;

import java.util.ArrayList;
import java.util.List;

public class ReflectionFeelingsConverter {

    // Converts a List<ReflectionFeelings> to a String (comma-separated)
    @TypeConverter
    public static String fromReflectionFeelingsList(List<ReflectionFeelings> reflectionFeelingsList) {
        if (reflectionFeelingsList == null) {
            return null; // Handle null case
        }
        // Convert each item in the list to a displayName and join them with commas
        StringBuilder sb = new StringBuilder();
        for (ReflectionFeelings feeling : reflectionFeelingsList) {
            if (sb.length() > 0) sb.append(","); // Add comma between values
            sb.append(feeling.getDisplayName());
        }
        return sb.toString(); // Return as a single string
    }

    // Converts a String (comma-separated) back to a List<ReflectionFeelings>
    @TypeConverter
    public static List<ReflectionFeelings> toReflectionFeelingsList(String displayNames) {
        if (displayNames == null || displayNames.isEmpty()) {
            return new ArrayList<>(); // Return empty list if input is null or empty
        }
        List<ReflectionFeelings> feelingsList = new ArrayList<>();
        String[] splitNames = displayNames.split(",");
        for (String name : splitNames) {
            ReflectionFeelings feeling = ReflectionFeelings.fromString(name.trim()); // Trim and convert to enum
            if (feeling != null) {
                feelingsList.add(feeling);
            }
        }
        return feelingsList;
    }
}
