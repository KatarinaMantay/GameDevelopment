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
    private BufferedImage img, subImg;

    public Panel(){
        mouseEvents = new MouseEvents(this);
        importImg();
        setPanelSize();
        setFocusable(true);
        addKeyListener(new KeyboardEvents(this));
        addMouseListener(mouseEvents);
        addMouseMotionListener(mouseEvents);

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

    public void paint(Graphics g){
        super.paint(g);

        subImg = img.getSubimage(1*64,8*40,64,40);
        g.drawImage(subImg,(int)xDelta,(int)yDelta,128,80,null);

    }

}
