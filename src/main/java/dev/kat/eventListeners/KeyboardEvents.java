package dev.kat.eventListeners;

import dev.kat.core.Panel;
import dev.kat.enums.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvents implements KeyListener {

    private final Panel panel;

    public KeyboardEvents(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> panel.setDirection(Direction.UP);
            case KeyEvent.VK_A -> panel.setDirection(Direction.LEFT);
            case KeyEvent.VK_S -> panel.setDirection(Direction.DOWN);
            case KeyEvent.VK_D -> panel.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W,
                 KeyEvent.VK_A,
                 KeyEvent.VK_S,
                 KeyEvent.VK_D
                    -> panel.setMoving(false);
        }

    }
}
