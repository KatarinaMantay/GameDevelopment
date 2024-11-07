package dev.kat.eventListeners;

import dev.kat.core.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static dev.kat.utilities.Constants.Directions.*;

public class KeyboardEvents implements KeyListener {

    private Panel panel;

    public KeyboardEvents(Panel panel) {
        this.panel = panel;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()){

            case KeyEvent.VK_W:
                panel.setDirection(UP);
                break;
            case KeyEvent.VK_A:
                panel.setDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                panel.setDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                panel.setDirection(RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch(e.getKeyCode()){

            case KeyEvent.VK_W:
            case KeyEvent.VK_A:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
                panel.setMoving(false);
                break;
        }

    }
}
