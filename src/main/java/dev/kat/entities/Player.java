package dev.kat.entities;

import dev.kat.enums.Direction;
import dev.kat.enums.PlayerAction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private PlayerAction playerAction = PlayerAction.IDLE;
    private Direction playerDirection = Direction.NONE;
    private boolean moving = false;


    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(){
        setAnimation();
        updatePosition();
        updateAnimationTick();

    }

    public void render(Graphics g){
        g.drawImage(
                animations[playerAction.getValue()][animationIndex],
                (int)x,
                (int)y,
                120,
                80,
                null
        );

    }

    public void setDirection(Direction direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;

            if (animationIndex >= playerAction.getSpriteAmount()){
                animationIndex = 0;
            }
        }

    }

    private void setAnimation() {
        if (moving && playerAction == PlayerAction.IDLE) {
            animationIndex = 0;
            playerAction = PlayerAction.RUNNING;
        }
        else if (playerAction == PlayerAction.RUNNING) {
            animationIndex = 0;
            playerAction = PlayerAction.IDLE;
        }
    }

    private void updatePosition() {
        if (moving) {

            switch(playerDirection){
                case LEFT -> x -= 5;
                case UP -> y -= 5;
                case RIGHT -> x += 5;
                case DOWN -> y += 5;
            }
        }
    }

    private void loadAnimations() {

        InputStream stream = getClass().getResourceAsStream("/player_sprites.png");

        try {
            BufferedImage image = ImageIO.read(stream);

            animations = new BufferedImage[9][6];
            for (int j = 0; j < animations.length; j++){
                for(int i = 0; i < animations[j].length; i++){
                    animations[j][i] = image.getSubimage(i*64, j*40, 64, 40);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
