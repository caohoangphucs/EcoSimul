package org.example.ecosimul.core.server;

import org.example.ecosimul.core.physic.collision.Collider;

public class Physic {
    public final float g = 0.3f;
    public Physic() {}
    public void update(Entity e, long dt) {
        Collider c = e.collider;

        // add velocity
        c.setX(c.getX() + c.getvx() * (double) dt / 1000);

        c.setY(c.getY() + c.getvy() * (double) dt / 1000);

        // apply gravity
        c.setVy(c.getvy() + g * dt);
    }

}
