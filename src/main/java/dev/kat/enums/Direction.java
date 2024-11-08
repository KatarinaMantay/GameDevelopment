package dev.kat.enums;

public enum Direction {

    NONE(-1),
    LEFT(0),
    UP(1),
    RIGHT(2),
    DOWN(3);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
