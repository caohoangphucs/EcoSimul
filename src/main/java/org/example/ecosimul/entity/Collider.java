package org.example.ecosimul.entity;

import javafx.scene.paint.Color;

public class Collider {
    public double x, y;
    public double vx;
    public double vy;
    public double radius;
    public Color color;
    public Collider(double x, double y, double vx, double vy, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.color = color;
        System.out.println("Creating Ball at " + x + ", " + y);
    }

}