package kat.dev.MainClasses;

import kat.dev.EventListeners.KeyboardEvents;
import kat.dev.EventListeners.MouseEvents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Panel extends JPanel {

    private MouseEvents mouseEvents;
    private float xDelta = 100;
    private float yDelta = 100;
    private BufferedImage img;
    private BufferedImage[] idle;
    private int animationTick, animationIndex, animationSpeed = 30;

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
        idle = new BufferedImage[5];

        for(int i = 0; i < idle.length; i++){
            idle[i] = img.getSubimage(i*64, 0, 64, 40);
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
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
        this.xDelta = x;
        this.yDelta = y;
    }

    public void changeXDelta(int num){
        this.xDelta += num;
    }

    public void changeYDelta(int num){
        this.yDelta += num;
    }

    private void updateAnimationTick() {
        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= idle.length){
                animationIndex = 0;
            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);

        updateAnimationTick();
        g.drawImage(idle[animationIndex], (int)xDelta, (int)yDelta, 120,80,null);
        

    }


}
