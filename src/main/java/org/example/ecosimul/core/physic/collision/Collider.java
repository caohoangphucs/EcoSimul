package org.example.ecosimul.core.physic.collision;

public interface Collider {
    ColliderType getType();
    double getX();
    double getY();

    double getvx();
    double getvy();

    void setX(double x);
    void setY(double y);

    void setVx(double vx);
    void setVy(double vy);
}
