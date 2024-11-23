package com.assignment.proddy.Entity.reflection;

public enum ReflectionFeelingsColourEnum {
    PROUD(1),
    FOGGY(0),
    PEACEFUL(1),
    EUPHORIC(1),
    SAD(0),
    HAPPY(1),
    MOODY(0),
    HELPLESS(0),
    ANXIOUS(0),
    GRATEFUL(1),
    LOVED(1),
    ANGRY(0),
    CALM(1),
    CREATIVE(1),
    DISTRACTED(0),
    MOTIVATED(1),
    INSECURE(0),
    NOSTALGIC(1);

    private final int value;

    ReflectionFeelingsColourEnum(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public static ReflectionFeelingsColourEnum fromString(String displayName) {
        for (ReflectionFeelingsColourEnum reflectionFeelings : ReflectionFeelingsColourEnum.values()) {
            if (reflectionFeelings.name().equalsIgnoreCase(displayName)) {
                return reflectionFeelings;
            }
        }
        return null; // or throw an exception if needed
    }

    public static ReflectionFeelingsColourEnum fromValue(int value) {
        for (ReflectionFeelingsColourEnum reflectionFeelings : ReflectionFeelingsColourEnum.values()) {
            if (reflectionFeelings.value == value) {
                return reflectionFeelings;
            }
        }
        return null; // or throw an exception if needed
    }
}

