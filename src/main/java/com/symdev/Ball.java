package com.symdev;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Ball {
    // Position and movement properties
    public double angle; // Current angle on the circular path
    public double x, y; // Cartesian coordinates

    // Appearance properties
    private int size;
    private Color color;
    public float opacity; // 0.0f (transparent) to 1.0f (opaque)

    // Animation properties
    private double opacityAngle; // Used to calculate opacity oscillation
    private double opacitySpeed;

    public Ball(double startAngle, int size, Color color, double opacitySpeed, double initialOpacityAngle) {
        this.angle = startAngle;
        this.size = size;
        this.color = color;
        this.opacitySpeed = opacitySpeed;
        this.opacityAngle = initialOpacityAngle;
        this.opacity = 0.0f;
    }

    public void update(double angleSpeed, int centerX, int centerY, int pathRadius) {
        // Update the angle for circular motion
        this.angle += angleSpeed;
        if (this.angle > 360) {
            this.angle -= 360;
        }

        // Update the opacity to create a fading effect
        this.opacityAngle += this.opacitySpeed;
        // Use Math.sin to get a smooth oscillation between -1 and 1, then map it to 0-1 for opacity
        this.opacity = (float) (Math.sin(Math.toRadians(this.opacityAngle)) + 1) / 2.0f;

        // Calculate the new x, y position based on the angle
        this.x = centerX + pathRadius * Math.cos(Math.toRadians(this.angle));
        this.y = centerY + pathRadius * Math.sin(Math.toRadians(this.angle));
    }

    public void draw(Graphics2D g2d) {
        // Set the color and opacity
        g2d.setColor(this.color);
        g2d.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, this.opacity));

        // Draw the ball centered on its calculated x, y coordinates
        Ellipse2D.Double circle = new Ellipse2D.Double(this.x - size / 2.0, this.y - size / 2.0, size, size);
        g2d.fill(circle);
    }
}
