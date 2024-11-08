package dev.kat.levels;

import dev.kat.core.Game;
import dev.kat.enums.SpriteAtlas;
import dev.kat.utilities.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static dev.kat.Config.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] level;
    private Level levelOne;

    public LevelManager(Game game){
        this.game = game;
        //level = LoadSave.getSpriteAtlas(SpriteAtlas.LEVEL_ATLAS);
        importOutsideSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void importOutsideSprites() {
        BufferedImage image = LoadSave.getSpriteAtlas(SpriteAtlas.LEVEL_ATLAS);
        level = new BufferedImage[48];

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 12; j++){
                int index = i * 12 + j;
                level [index] = image.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public void drawLevel(Graphics g){

        for(int i = 0; i < TILE_HEIGHT; i++){
            for(int j = 0; j < TILE_WIDTH; j++){
                int index = levelOne.getSpriteIndex(j,i);
                g.drawImage(level[index], TILE_SIZE*j, TILE_SIZE*i, TILE_SIZE, TILE_SIZE, null);
            }

        }
    }

    public void updateLevel(){

    }
}
