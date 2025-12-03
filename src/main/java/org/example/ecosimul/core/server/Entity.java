package org.example.ecosimul.core.server;

import org.example.ecosimul.core.physic.collision.Collider;

public class Entity implements Object{
    public Collider collider;

    public Entity(Collider collider) {
        this.collider = collider;
    }

    public void onCollide(Entity other) {
        System.out.println("Entity collided!");
    }
}
