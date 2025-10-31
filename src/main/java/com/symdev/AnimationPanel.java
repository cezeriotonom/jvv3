package com.symdev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AnimationPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

    private int x = 50; // Initial x position
    private int y = 50; // Initial y position
    private int dx = 2; // x velocity
    private double dy = 0; // y velocity (using double for precision)
    private final int BALL_SIZE = 40;
    private final int DELAY = 10; // Milliseconds between updates
    private double gravity = 0.1;
    private final double BOUNCE_FACTOR = 0.7; // Energy loss on bounce
    private boolean isDragging = false;

    public AnimationPanel() {
        // Timer to drive the animation
        Timer timer = new Timer(DELAY, this);
        timer.start();

        // Add mouse listeners to the panel
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isDragging) {
            // Don't apply physics while dragging
            return;
        }

        // Apply gravity to vertical velocity
        dy += gravity;

        // Update ball position
        x += dx;
        y += (int)dy;

        // Check for collisions with horizontal boundaries
        if (x < 0) {
            x = 0;
            dx = -dx;
        } else if (x > getWidth() - BALL_SIZE) {
            x = getWidth() - BALL_SIZE;
            dx = -dx;
        }

        // Check for collision with the floor (and bounce)
        if (y > getHeight() - BALL_SIZE) {
            y = getHeight() - BALL_SIZE;
            dy *= -BOUNCE_FACTOR; // Reverse and reduce vertical velocity
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

    @Override
    public void mousePressed(MouseEvent e) {
        // Check if the mouse press is inside the ball
        if (e.getX() >= x && e.getX() <= x + BALL_SIZE && e.getY() >= y && e.getY() <= y + BALL_SIZE) {
            isDragging = true;
            // Reset velocity when picked up
            dx = 0;
            dy = 0;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isDragging) {
            // Center the ball on the mouse cursor
            x = e.getX() - BALL_SIZE / 2;
            y = e.getY() - BALL_SIZE / 2;
            repaint();
        }
    }

    // Unused mouse listener methods
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
