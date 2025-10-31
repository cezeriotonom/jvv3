package com.symdev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AnimationPanel extends JPanel implements ActionListener {

    private final int NUM_BALLS = 6;
    private final int PATH_RADIUS = 50;
    private final int BALL_SIZE = 10;
    private final double ANGLE_SPEED = 1.0; // Degrees per frame

    private List<Ball> balls;

    public AnimationPanel() {
        setBackground(new Color(0, 114, 198)); // Windows 10 blue
        balls = new ArrayList<>();

        for (int i = 0; i < NUM_BALLS; i++) {
            double startAngle = i * (360.0 / NUM_BALLS);
            // Stagger the opacity phases for a wave-like effect
            double initialOpacityAngle = i * (180.0 / NUM_BALLS);
            balls.add(new Ball(startAngle, BALL_SIZE, Color.WHITE, 2.0, initialOpacityAngle));
        }

        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        for (Ball ball : balls) {
            ball.update(ANGLE_SPEED, centerX, centerY, PATH_RADIUS);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Ball ball : balls) {
            ball.draw(g2d);
        }
        
        // Reset composite to default after drawing all balls
        g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, 1.0f));
    }
}
