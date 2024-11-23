package com.assignment.proddy.Entity.reflection;

public enum ReflectionActivities {

    SPORTS("Sports"),
    LOVE("Love"),
    FRIENDS("Friends"),
    WORK("Work"),
    STUDYING("Studying"),
    SELF_CARE("Self_Care"),
    CHORES("Chores"),
    NATURE("Nature"),
    RELAXING("Relaxing");



    private final String displayName;

    ReflectionActivities(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ReflectionActivities fromString(String displayName) {
        for (ReflectionActivities reflectionActivities : ReflectionActivities.values()) {
            if (reflectionActivities.displayName.equalsIgnoreCase(displayName)) {
                return reflectionActivities;
            }
        }
        return null; // or throw an exception if needed
    }
}
