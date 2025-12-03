package org.example.ecosimul.core.physic.collision.resolve;

import org.example.ecosimul.core.physic.collision.BorderTouchType;
import org.example.ecosimul.core.physic.collision.Collider;
import org.example.ecosimul.core.physic.collision.ColliderType;
import org.example.ecosimul.core.physic.collision.Collision;
import org.example.ecosimul.core.physic.collision.shape.Circle;

public class Resolver {
    public Resolver() {

    }

    public void resolve(Collision col) {
        if (col.A.getType() == ColliderType.CIRCLE && col.B.getType() == ColliderType.CIRCLE) {
            resolveCircleCircle((Circle) col.A, (Circle) col.B);
        }
    }
    public void resolveCircleCircle(Circle A, Circle B) {

        double dx = B.x - A.x;
        double dy = B.y - A.y;
        double dist = Math.sqrt(dx*dx + dy*dy);
        if (dist == 0) { dist = 0.001; dx = 0.001; }

        double sumR = A.radius + B.radius;
        double penetration = sumR - dist;
        if (penetration <= 0) return;

        double nx = dx / dist;
        double ny = dy / dist;

        // 1. IMPULSE RESPONSE (phản lực)
        double rvx = B.vx - A.vx;
        double rvy = B.vy - A.vy;

        double velAlongNormal = rvx * nx + rvy * ny;
        if (velAlongNormal > 0) return;

        double restitution = 0.4;
        double j = -(1 + restitution) * velAlongNormal;

        double ix = j * nx;
        double iy = j * ny;

        A.vx -= ix * 0.5;
        A.vy -= iy * 0.5;

        B.vx += ix * 0.5;
        B.vy += iy * 0.5;

        // 2. PUSH OUT (đưa ra khỏi overlap)
        double push = penetration / 2.0;

        A.x -= nx * push;
        A.y -= ny * push;

        B.x += nx * push;
        B.y += ny * push;
    }

    public void resolveBorder(Collider A, BorderTouchType type, int screenWidth, int screenHeight) {
        if (A.getType() == ColliderType.CIRCLE) {
            Circle circle = (Circle) A;
            double r = circle.getRadius();
            double restitution = 0.8;

            if (type == BorderTouchType.BOTTOM) {
                circle.setY(screenHeight - r);
                circle.setVy(-circle.getvy() * restitution);
            }

            if (type == BorderTouchType.TOP) {
                circle.setY(r);
                circle.setVy(-circle.getvy() * restitution);
            }

            if (type == BorderTouchType.LEFT) {
                circle.setX(r);
                circle.setVx(-circle.getvx() * restitution);
            }

            if (type == BorderTouchType.RIGHT) {
                circle.setX(screenWidth - r);
                circle.setVx(-circle.getvx() * restitution);
            }

        }
    }
}
