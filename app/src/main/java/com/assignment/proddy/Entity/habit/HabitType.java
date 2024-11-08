package com.assignment.proddy.Entity.habit;

public enum HabitType {
    HEALTH("Health"),
    MINDFULNESS("Mindfulness"),
    PRODUCTIVITY("Productivity"),
    FUN("Fun"),
    RELATIONSHIPS("Relationships"),
    FINANCES("Finances"),
    LEARNING("Learning");

    private final String displayName;

    HabitType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static HabitType fromString(String displayName) {
        for (HabitType habitType : HabitType.values()) {
            if (habitType.displayName.equalsIgnoreCase(displayName)) {
                return habitType;
            }
        }
        return null; // or throw an exception if needed
    }
}
