package kat.dev.EventListeners;

import kat.dev.MainClasses.Panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
                panel.changeYDelta(-5);
                break;
            case KeyEvent.VK_A:
                panel.changeXDelta(-5);
                break;
            case KeyEvent.VK_S:
                panel.changeYDelta(5);
                break;
            case KeyEvent.VK_D:
                panel.changeXDelta(5);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
