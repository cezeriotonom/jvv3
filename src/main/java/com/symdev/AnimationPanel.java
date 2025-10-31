package com.symdev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationPanel extends JPanel implements ActionListener {

    private int x = 50; // Initial x position
    private int y = 50; // Initial y position
    private int dx = 2; // x velocity
    private int dy = 2; // y velocity
    private final int BALL_SIZE = 40;
    private final int DELAY = 10; // Milliseconds between updates

    public AnimationPanel() {
        // Timer to drive the animation
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update ball position
        x += dx;
        y += dy;

        // Check for collisions with the panel boundaries
        if (x < 0 || x > getWidth() - BALL_SIZE) {
            dx = -dx;
        }
        if (y < 0 || y > getHeight() - BALL_SIZE) {
            dy = -dy;
        }

        // Trigger a repaint
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing for smooth edges
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the ball
        g2d.setColor(Color.RED);
        g2d.fillOval(x, y, BALL_SIZE, BALL_SIZE);
    }
}
