package com.symdev;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        // Ensure GUI updates are on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame frame = new JFrame("jvv3");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(800, 600));
            frame.setLocationRelativeTo(null); // Center the window

            // Create and add the animation panel
            AnimationPanel animationPanel = new AnimationPanel();
            frame.getContentPane().add(animationPanel, BorderLayout.CENTER);

            // Display the window
            frame.setVisible(true);
        });
    }
}
