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

            // Create a label with the message
            JLabel label = new JLabel("Hello SymDev AI", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 24));

            // Add the label to the frame's content pane
            frame.getContentPane().add(label, BorderLayout.CENTER);

            // Display the window
            frame.setVisible(true);
        });
    }
}
