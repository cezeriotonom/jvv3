package com.symdev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;

public class AnimationPanel extends JPanel implements ActionListener {

    private final int TRAIL_LENGTH = 6; // Number of dots in the trail
    private final int PATH_RADIUS = 20;
    private final int DOT_SIZE = 6;
    private final double ANGLE_SPEED = 4.0; // Degrees per frame

    // A list to store the history of the lead dot's position
    private LinkedList<Point> trail;
    private double currentAngle = 0;

    public AnimationPanel() {
        setBackground(new Color(33, 37, 41)); // A dark grey, similar to dark blue grey
        trail = new LinkedList<>();
        Timer timer = new Timer(15, this); // ~66 FPS
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update the angle for the lead dot
        currentAngle += ANGLE_SPEED;
        if (currentAngle >= 360) {
            currentAngle -= 360;
        }

        // Calculate the new position for the lead dot
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = (int) (centerX + PATH_RADIUS * Math.cos(Math.toRadians(currentAngle)));
        int y = (int) (centerY + PATH_RADIUS * Math.sin(Math.toRadians(currentAngle)));

        // Add the new position to the front of the trail
        trail.addFirst(new Point(x, y));

        // Keep the trail at a fixed length
        if (trail.size() > TRAIL_LENGTH) {
            trail.removeLast();
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(new Color(137, 25, 28)); // Teknofest Red (#89191C)

        // Draw each dot in the trail with decreasing opacity
        for (int i = 0; i < trail.size(); i++) {
            Point p = trail.get(i);
            // Opacity decreases from front to back
            float opacity = 1.0f - (float) i / TRAIL_LENGTH;
            if (opacity < 0) opacity = 0;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            // Draw the dot centered on its point
            g2d.fill(new Ellipse2D.Double(p.x - DOT_SIZE / 2.0, p.y - DOT_SIZE / 2.0, DOT_SIZE, DOT_SIZE));
        }
        
        // Reset composite to default
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}
