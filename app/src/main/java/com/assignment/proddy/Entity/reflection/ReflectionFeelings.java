package com.assignment.proddy.Entity.reflection;

public enum ReflectionFeelings {
    PROUD("Proud"),
    FOGGY("Foggy"),
    PEACEFUL("Peaceful"),
    EUPHORIC("Euphoric"),
    SAD("Sad"),
    HAPPY("Happy"),
    MOODY("Moody"),
    HELPLESS("Helpless"),
    ANXIOUS("Anxious"),
    GRATEFUL("Grateful"),
    LOVED("Loved"),
    ANGRY("Angry"),
    CALM("Calm"),
    CREATIVE("Creative"),
    DISTRACTED("Distracted"),
    MOTIVATED("Motivated"),
    INSECURE("Insecure"),
    NOSTALGIC("Nostalgic");


    private final String displayName;

    ReflectionFeelings(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ReflectionFeelings fromString(String displayName) {
        for (ReflectionFeelings reflectionFeelings : ReflectionFeelings.values()) {
            if (reflectionFeelings.displayName.equalsIgnoreCase(displayName)) {
                return reflectionFeelings;
            }
        }
        return null; // or throw an exception if needed
    }
}
