package dev.kat.enums;

public enum PlayerAction {

    IDLE(0, 30),
    RUNNING(1, 20),
    JUMP(2, 15),
    FALLING(3, 20),
    GROUND(4, 25),
    HIT(5, 35),
    ATTACK_1(6, 20),
    ATTACK_JUMP_1(7, 12),
    ATTACK_JUMP_2(8, 12);

    private final int value;
    private final int animationSpeed;

    PlayerAction(int value, int animationSpeed) {
        this.value = value;
        this.animationSpeed = animationSpeed;
    }

    public int getValue() {
        return value;
    }

    public int getAnimationSpeed() {
        return animationSpeed;
    }

    public int getSpriteAmount() {
        return switch (this) {
            case RUNNING -> 6;
            case IDLE -> 5;
            case HIT -> 4;
            case ATTACK_1, ATTACK_JUMP_2, ATTACK_JUMP_1 -> 3;
            case GROUND -> 2;
            default -> 1;
        };
    }
}