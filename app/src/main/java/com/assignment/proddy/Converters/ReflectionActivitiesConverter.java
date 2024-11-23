package com.assignment.proddy.Converters;

import androidx.room.TypeConverter;

import com.assignment.proddy.Entity.reflection.ReflectionActivities;

import java.util.ArrayList;
import java.util.List;

public class ReflectionActivitiesConverter {

    // Converts a List<ReflectionActivities> to a String (comma-separated)
    @TypeConverter
    public static String fromReflectionActivitiesList(List<ReflectionActivities> reflectionActivitiesList) {
        if (reflectionActivitiesList == null) {
            return null; // Handle null case
        }
        // Convert each item in the list to a displayName and join them with commas
        StringBuilder sb = new StringBuilder();
        for (ReflectionActivities activity : reflectionActivitiesList) {
            if (sb.length() > 0) sb.append(","); // Add comma between values
            sb.append(activity.getDisplayName());
        }
        return sb.toString(); // Return as a single string
    }

    // Converts a String (comma-separated) back to a List<ReflectionActivities>
    @TypeConverter
    public static List<ReflectionActivities> toReflectionActivitiesList(String displayNames) {
        if (displayNames == null || displayNames.isEmpty()) {
            return new ArrayList<>(); // Return empty list if input is null or empty
        }
        List<ReflectionActivities> activitiesList = new ArrayList<>();
        String[] splitNames = displayNames.split(",");
        for (String name : splitNames) {
            ReflectionActivities activity = ReflectionActivities.fromString(name.trim()); // Trim and convert to enum
            if (activity != null) {
                activitiesList.add(activity);
            }
        }
        return activitiesList;
    }
}
