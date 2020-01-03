package de.ralfhergert.gw2.model;

import java.util.Random;

public class Range {

    private final int min;
    private final int max;

    public Range(int min, int max) {
        this.min = Math.min(min, max);
        this.max = Math.max(min, max);
    }

    public boolean isInRange(int value) {
        return min <= value && value <= max;
    }

    public int random() {
        return min + new Random().nextInt(max - min);
    }
}
