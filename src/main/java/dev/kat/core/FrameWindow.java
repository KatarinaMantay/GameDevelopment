package dev.kat.core;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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
        frame.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowGainedFocus(WindowEvent e) {
                panel.getGame().windowFocusLost();
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
    }
}
