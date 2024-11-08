package dev.kat.utilities;

import dev.kat.enums.SpriteAtlas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static dev.kat.Config.TILE_HEIGHT;
import static dev.kat.Config.TILE_WIDTH;

public class LoadSave {

    public static BufferedImage getSpriteAtlas(SpriteAtlas atlas) {
        BufferedImage image = null;
        InputStream stream = LoadSave.class.getResourceAsStream("/" + atlas.getFile());
        try {
            assert stream != null;
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return image;
    }

    public static int[][] getLevelData(){
        int[][] levelData = new int[TILE_HEIGHT][TILE_WIDTH];
        BufferedImage image = LoadSave.getSpriteAtlas(SpriteAtlas.LEVEL_ONE_DATA);

        for(int i = 0; i < image.getHeight(); i++){
            for(int j = 0; j < image.getWidth(); j++){
                Color color = new Color(image.getRGB(j, i));
                int value = color.getRed();

                if(value >= 48){
                    value = 0;
                }

                levelData[i][j] = value;
            }
        }
        return levelData;
    }
}