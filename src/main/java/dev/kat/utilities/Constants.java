package dev.kat.utilities;

public class Constants {

    public static class PlayerConstants{
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int GROUND = 4;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_JUMP_1 = 7;
        public static final int ATTACK_JUMP_2 = 8;

        public static int GetSpriteAmount(int player_action){

            switch(player_action){
                case RUNNING:
                    return 6;
                case IDLE:
                    return 5;
                case HIT:
                    return 4;
                case ATTACK_1, ATTACK_JUMP_2, ATTACK_JUMP_1:
                    return 3;
                case GROUND:
                    return 2;
                case FALLING:
                default:
                    return 1;


            }

        }
    }
}
