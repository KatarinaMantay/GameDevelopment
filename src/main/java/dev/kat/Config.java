package dev.kat;

public final class Config {

    //used in game class
    public static final int TILES_DEFAULT_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILE_WIDTH = 26;
    public static final int TILE_HEIGHT = 14;
    public static final int TILE_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILE_SIZE * TILE_WIDTH;
    public static final int GAME_HEIGHT = TILE_SIZE * TILE_HEIGHT;

    private Config() {
    }
}