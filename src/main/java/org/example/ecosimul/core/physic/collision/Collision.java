package org.example.ecosimul.core.physic.collision;

public class Collision {
    public Collider A;
    public Collider B;
    float CollisionPoint;
    public Collision(Collider A, Collider B, float CollisionPoint) {
        this.A = A;
        this.B = B;
        this.CollisionPoint = CollisionPoint;
    }
}
