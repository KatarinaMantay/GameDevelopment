package dev.kat.core;

import dev.kat.enums.Direction;
import dev.kat.enums.PlayerAction;
import dev.kat.events.KeyboardEvents;
import dev.kat.events.MouseEvents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static dev.kat.Config.GAME_HEIGHT;
import static dev.kat.Config.GAME_WIDTH;

public class Panel extends JPanel {

    private MouseEvents mouseEvents;
    private Game game;

    public Panel(Game game){
        this.game = game;
        mouseEvents = new MouseEvents(this);

        setPanelSize();
        setFocusable(true);

        addKeyListener(new KeyboardEvents(this));
        addMouseListener(mouseEvents);
        addMouseMotionListener(mouseEvents);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paint(Graphics g){
        super.paint(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }
}
