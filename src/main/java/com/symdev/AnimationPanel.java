package com.symdev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.awt.AlphaComposite; // Missing import added here

public class AnimationPanel extends JPanel implements ActionListener {

    private final int TRAIL_LENGTH = 6;
    private final int PATH_RADIUS = 20;
    private final int DOT_SIZE = 6;
    private final double ANGLE_SPEED = 4.0;

    private LinkedList<Point> trail;
    private double currentAngle = 0;

    public AnimationPanel() {
        setBackground(new Color(0, 114, 198)); // Windows 10 blue
        trail = new LinkedList<>();
        Timer timer = new Timer(15, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentAngle += ANGLE_SPEED;
        if (currentAngle >= 360) {
            currentAngle -= 360;
        }

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int x = (int) (centerX + PATH_RADIUS * Math.cos(Math.toRadians(currentAngle)));
        int y = (int) (centerY + PATH_RADIUS * Math.sin(Math.toRadians(currentAngle)));

        trail.addFirst(new Point(x, y));

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
        g2d.setColor(Color.WHITE);

        for (int i = 0; i < trail.size(); i++) {
            Point p = trail.get(i);
            
            float opacity = (float) Math.pow(0.75, i);
            double currentSize = DOT_SIZE * (1.0 - (double)i / (TRAIL_LENGTH + 2));
            if (currentSize < 1) currentSize = 1;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            g2d.fill(new Ellipse2D.Double(p.x - currentSize / 2.0, p.y - currentSize / 2.0, currentSize, currentSize));
        }
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}
