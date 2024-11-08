package dev.kat.core;

import dev.kat.enums.Direction;
import dev.kat.enums.PlayerAction;
import dev.kat.eventListeners.KeyboardEvents;
import dev.kat.eventListeners.MouseEvents;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Panel extends JPanel {

    private MouseEvents mouseEvents;
    private float xChanges = 100;
    private float yChanges = 100;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private PlayerAction playerAction = PlayerAction.IDLE;
    private Direction playerDirection = Direction.NONE;
    private boolean moving = false;

    public Panel(){
        mouseEvents = new MouseEvents(this);

        importImg();
        loadAnimations();

        setPanelSize();
        setFocusable(true);

        addKeyListener(new KeyboardEvents(this));
        addMouseListener(mouseEvents);
        addMouseMotionListener(mouseEvents);

    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++){
            for(int i = 0; i < animations[j].length; i++){
                animations[j][i] = image.getSubimage(i*64, j*40, 64, 40);
            }

        }
    }

    private void importImg() {
        InputStream stream = getClass().getResourceAsStream("/player_sprites.png");

        try {
            image = ImageIO.read(stream);
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

    public void updateGame(){
        setAnimation();
        updatePosition();
        updateAnimationTick();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(800, 500);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
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
                case LEFT -> xChanges -= 5;
                case UP -> yChanges -= 5;
                case RIGHT -> xChanges += 5;
                case DOWN -> yChanges += 5;
            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(
                animations[playerAction.getValue()][animationIndex],
                (int)xChanges,
                (int) yChanges,
                120,
                80,
                null
        );
    }



}
