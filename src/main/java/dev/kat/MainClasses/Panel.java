package dev.kat.MainClasses;

import dev.kat.EventListeners.KeyboardEvents;
import dev.kat.EventListeners.MouseEvents;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static dev.kat.utilities.Constants.PlayerConstants.*;

public class Panel extends JPanel {

    private MouseEvents mouseEvents;
    private float xChanges = 100;
    private float yChanges = 100;
    private BufferedImage image;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = IDLE;

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

        for(int j = 0; j < animations.length; j++){
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

    private void setPanelSize() {
        Dimension size = new Dimension(1024, 640);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setRectPosition(int x, int y){
        this.xChanges = x;
        this.yChanges = y;
    }

    public void changeXPos(int num){
        this.xChanges += num;
    }

    public void changeYPos(int num){
        this.yChanges += num;
    }

    private void updateAnimationTick() {
        animationTick++;

        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;

            if(animationIndex >= GetSpriteAmount(playerAction)){
                animationIndex = 0;
            }
        }

    }

    public void paint(Graphics g){
        super.paint(g);

        updateAnimationTick();
        g.drawImage(animations[playerAction][animationIndex], (int)xChanges, (int) yChanges, 120,80,null);
        

    }


}
