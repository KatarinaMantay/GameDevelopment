package dev.kat.enums;

public enum PlayerAction {

    IDLE(0),
    RUNNING(1),
    JUMP(2),
    FALLING(3),
    GROUND(4),
    HIT(5),
    ATTACK_1(6),
    ATTACK_JUMP_1(7),
    ATTACK_JUMP_2(8);

    private final int value;

    PlayerAction(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public int getSpriteAmount(){
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
