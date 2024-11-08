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


}
