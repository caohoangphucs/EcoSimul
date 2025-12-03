package org.example.ecosimul.core.physic.collision.shape;

import org.example.ecosimul.core.physic.collision.Collider;
import org.example.ecosimul.core.physic.collision.ColliderType;

public class Rectangle implements Collider {
    public double x;
    public double y;
    public double w;
    public double h;
    public double vx, vy;
    public Rectangle(double x, double y, double w, double h, double vx, double vy) {
        this.x = x;
        this.w = w;
        this.h = h;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }
    public Rectangle(double x, double y, double w, double h) {
        this(x, y,w,h,0,0);
    }
    @Override
    public ColliderType getType() {
        return ColliderType.RECTANGLE;
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
        return this.getvx();
    }

    @Override
    public double getvy() {
        return this.getvy();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setVx(double vx) {
        this.vx = vx;
    }

    @Override
    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

}
