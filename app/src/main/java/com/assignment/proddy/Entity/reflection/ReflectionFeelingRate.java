package com.assignment.proddy.Entity.reflection;

public enum ReflectionFeelingRate {
    AWESOME(5),
    GOOD(4),
    NORMAL(3),
    MEH(2),
    TERRIBLE(1);

    private final int rate;

    // Constructor
    ReflectionFeelingRate(int rate) {
        this.rate = rate;
    }

    // Getter for rate
    public int getRate() {
        return rate;
    }
    // Optional: Static method to get enum by rate value
    public static ReflectionFeelingRate getByRate(int rate) {
        for (ReflectionFeelingRate feelingRate : ReflectionFeelingRate.values()) {
            if (feelingRate.getRate() == rate) {
                return feelingRate;
            }
        }
        throw new IllegalArgumentException("No enum constant with rate: " + rate);
    }
}

