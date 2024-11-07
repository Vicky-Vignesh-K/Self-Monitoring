package com.self.monitoring.SelfMonitoring.configuration;

public enum Ratings {
    GOOD,
    AVERAGE,
    BAD,
    EXCELLENT,
    VERYGOOD,
    NOTBAD;

    public static Ratings finder(String rating) {
        for (Ratings ratingEnum : Ratings.values()) {
            if (ratingEnum.name().equalsIgnoreCase(rating)) {
                return ratingEnum;
            }
        }
        throw new IllegalArgumentException("Unknown rating: " + rating);
    }
}
