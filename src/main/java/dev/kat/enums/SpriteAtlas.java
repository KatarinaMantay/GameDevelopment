package dev.kat.enums;

public enum SpriteAtlas {
    PLAYER_ATLAS("player_sprites.png"),
    LEVEL_ATLAS("outside_sprites.png"),
    LEVEL_ONE_DATA("level_one_data.png");

    private final String filePath;

    SpriteAtlas(String filePath) {
        this.filePath = filePath;
    }

    public String getFile() {
        return filePath;
    }
}