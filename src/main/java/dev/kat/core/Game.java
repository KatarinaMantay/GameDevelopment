package dev.kat.core;

public class Game implements Runnable {
    private FrameWindow window;
    private Panel panel;
    private Thread thread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public Game(){
        panel = new Panel();
        window = new FrameWindow(panel);

        panel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;

        long previousTime = System.nanoTime();

        long lastFrame = System.nanoTime();
        long now = System.nanoTime();
        int updates = 0;
        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;

        while(true){
            now = System.nanoTime();
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;

            if(deltaU >= 1){
                updates++;
                deltaU--;
            }

            if(now - lastFrame  >= timePerFrame){
                panel.repaint();
                lastFrame = now;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }
}
