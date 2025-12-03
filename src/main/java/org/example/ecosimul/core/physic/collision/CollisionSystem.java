package org.example.ecosimul.core.physic.collision;

import org.example.ecosimul.core.physic.collision.shape.Circle;
import org.example.ecosimul.core.physic.collision.shape.Rectangle;

public class CollisionSystem {
    public int screenWidth;
    public int screenHeight;
    public CollisionSystem(){

    }
    public BorderTouchType isBorderCollision(Collider A, int screenWidth, int screenHeight) {
        if (A.getType() == ColliderType.CIRCLE) {
            Circle circle = (Circle) A;
            double x = circle.getX();
            double y = circle.getY();
            double r = circle.getRadius();

            if (y - r <= 0) return BorderTouchType.TOP;
            if (y + r >= screenHeight) return  BorderTouchType.BOTTOM;
            if (x - r <= 0) return BorderTouchType.LEFT;
            if (x + r >= screenWidth) return  BorderTouchType.RIGHT;

        }

        return BorderTouchType.NONE;
    }
    public boolean isCollision(Collider A, Collider B) {
        if (A.getType() == ColliderType.CIRCLE && B.getType() == ColliderType.CIRCLE)
            return circleCircle((Circle) A, (Circle) B);

        if (A.getType() == ColliderType.RECTANGLE && B.getType() == ColliderType.RECTANGLE)
            return rectangleRectangle((Rectangle) A, (Rectangle) B);

        if (A.getType() == ColliderType.CIRCLE && B.getType() == ColliderType.RECTANGLE)
            return circleRectangle((Circle) A, (Rectangle) B);

        if (A.getType() == ColliderType.RECTANGLE && B.getType() == ColliderType.CIRCLE)
            return circleRectangle((Circle) B, (Rectangle) A);

        // polygon, capsule... thêm sau
        return false;
    }

    public BorderTouchType isBorderTouch(Collider A) {
        if (A.getType() == ColliderType.CIRCLE) return circleBorder((Circle) A);
        if (A.getType() == ColliderType.RECTANGLE) return rectangleBorder((Rectangle) A);

        return BorderTouchType.NONE;
    }

    private boolean circleCircle(Circle a, Circle b) {
        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        double r = a.getRadius() + b.getRadius();
        return dx*dx + dy*dy < r*r;
    }

    private boolean rectangleRectangle(Rectangle a, Rectangle b) {
        return a.getX() < b.getX() + b.getW() &&
                a.getX() + a.getW() > b.getH() &&
                a.getY() < b.getY() + b.getH() &&
                a.getY() + a.getY() > b.getH();
    }

    private boolean circleRectangle(Circle c, Rectangle box) {
        double closestX = clamp(c.getY(), box.getX(), box.getX() + box.getW());
        double closestY = clamp(c.getY(), box.getY(), box.getY() + box.getH());

        double dx = closestX - c.getX();
        double dy = closestY - c.getY();

        return dx*dx + dy*dy < c.getRadius() * c.getRadius();
    }

    private BorderTouchType circleBorder(Circle A) {
        if (A.getY() - A.getRadius() < 0) return BorderTouchType.TOP;
        if (A.getY() + A.getRadius() > this.screenHeight) return BorderTouchType.BOTTOM;
        if (A.getX() - A.getRadius() < 0) return BorderTouchType.LEFT;
        if (A.getX() + A.getRadius() > this.screenWidth) return BorderTouchType.RIGHT;
        return BorderTouchType.NONE;
    }
    private BorderTouchType rectangleBorder(Rectangle A) {
        if (A.getX() < 0) return BorderTouchType.LEFT;
        if (A.getX() + A.getW() > this.screenWidth) return BorderTouchType.RIGHT;
        if (A.getY() < 0) return BorderTouchType.TOP;
        if (A.getY() + A.getH() > this.screenHeight) return BorderTouchType.BOTTOM;
        return  BorderTouchType.NONE;
    }
    private double clamp(double v, double min, double max) {
        return Math.max(min, Math.min(max, v));
    }
}
