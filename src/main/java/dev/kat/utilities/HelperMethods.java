package dev.kat.utilities;

import dev.kat.Config;

public class HelperMethods {

    public static boolean canMoveHere(int x, int y, int width, int height, int[][] levelData){

        return  !isSolid(x, y, levelData) &&
                !isSolid(x + width, y, levelData) &&
                !isSolid(x, y + height, levelData) &&
                !isSolid(x + width, y + height, levelData);

    }

    public static boolean isSolid(int x, int y, int[][] levelData){
        if ( x < 0 || x >= Config.GAME_WIDTH){
            return true;
        }
        if (y < 0 || y >= Config.GAME_HEIGHT){
            return true;
        }

        float xIndex = (float) x / Config.TILE_SIZE;
        float yIndex = (float) y / Config.TILE_SIZE;

        int value = levelData[(int) yIndex][(int) xIndex];
        if (value >= 48 || value < 0 || value != 11) {
            return true;
        }

        return false;
    }
}
