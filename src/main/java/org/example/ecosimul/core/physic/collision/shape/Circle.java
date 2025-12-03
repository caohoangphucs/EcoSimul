package org.example.ecosimul.core.physic.collision.shape;

import org.example.ecosimul.core.physic.collision.Collider;
import org.example.ecosimul.core.physic.collision.ColliderType;
import org.example.ecosimul.core.server.Entity;

public class Circle implements Collider {
    public double x, y, radius;
    public double vx, vy;
    public Circle(double x, double y, double radius, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.vx = vx;
        this.vy = vy;

    }
    public Circle(double x, double y, double radius) {
        this(x,y,radius,0,0);
    }
    @Override
    public ColliderType getType() {
        return ColliderType.CIRCLE;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getvx() {
        return this.vx;
    }

    @Override
    public double getvy() {
        return this.vy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }
}
