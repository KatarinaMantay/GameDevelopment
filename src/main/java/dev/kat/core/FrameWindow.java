package dev.kat.core;

import javax.swing.*;

public class FrameWindow {
    private JFrame frame;

    public FrameWindow(Panel panel) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
