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

            // Create a slider to control gravity
            JSlider gravitySlider = new JSlider(0, 100, 10); // Min, Max, Initial
            gravitySlider.setMajorTickSpacing(25);
            gravitySlider.setMinorTickSpacing(5);
            gravitySlider.setPaintTicks(true);
            gravitySlider.setPaintLabels(true);

            // Add a listener to update gravity in the animation panel
            gravitySlider.addChangeListener(e -> {
                JSlider source = (JSlider) e.getSource();
                // Scale the slider value to a more appropriate gravity range
                animationPanel.setGravity(source.getValue() / 100.0);
            });

            // Add the slider to the bottom of the frame
            frame.getContentPane().add(gravitySlider, BorderLayout.SOUTH);

            // Display the window
            frame.pack(); // Adjust frame size to fit components
            frame.setVisible(true);
        });
    }
}
