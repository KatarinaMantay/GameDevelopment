package dev.kat.events;

import dev.kat.core.Panel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardEvents implements KeyListener {

    private final Panel panel;

    public KeyboardEvents(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> panel.getGame().getPlayer().setUp(true);
            case KeyEvent.VK_A -> panel.getGame().getPlayer().setLeft(true);
            case KeyEvent.VK_S -> panel.getGame().getPlayer().setDown(true);
            case KeyEvent.VK_D -> panel.getGame().getPlayer().setRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> panel.getGame().getPlayer().setUp(false);
            case KeyEvent.VK_A -> panel.getGame().getPlayer().setLeft(false);
            case KeyEvent.VK_S -> panel.getGame().getPlayer().setDown(false);
            case KeyEvent.VK_D -> panel.getGame().getPlayer().setRight(false);
        }
    }
}