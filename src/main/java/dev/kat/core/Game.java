package dev.kat.core;

import dev.kat.entities.Player;

import java.awt.*;

public class Game {
    private FrameWindow window;
    private Panel panel;
    private Thread thread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    public Game() {
        initalizeClasses();
        panel = new Panel(this);
        window = new FrameWindow(panel);

        panel.requestFocus();
    }

    private void initalizeClasses() {
        player = new Player(200,200);
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g) {
        player.render(g);
    }

    public void start() {
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;

        long previousTime = System.nanoTime();

        int updates = 0;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                panel.repaint();
                frames++;
                deltaF--;
            }


            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }
    }

    public Player getPlayer() {
        return player;
    }


    public void windowFocusLost() {
        player.resetDir();
    }
}
