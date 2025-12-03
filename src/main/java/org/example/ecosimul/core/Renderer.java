package org.example.ecosimul.core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.ecosimul.core.physic.collision.Collider;
import org.example.ecosimul.core.physic.collision.ColliderType;
import org.example.ecosimul.core.physic.collision.shape.Circle;
import org.example.ecosimul.core.server.Entity;
import org.example.ecosimul.core.server.World;

public class Renderer {
    private long lastPrint = 0;
    private long frames = 0;
    public World world;
    public Renderer(World world) {
        this.world = world;
    }
    public void update(long now) {
        frames++;

        if (now - lastPrint >= 1_000_000_000L) {
            show();
            frames = 0;
            lastPrint = now;
        }
    }
    public void render(GraphicsContext gc, double width, double height) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        for (Entity entity: world.entities) {
            if (entity.collider.getType() == ColliderType.CIRCLE) {
                gc.setFill(Color.WHITE);
                gc.fillText("Ball's Vy: " + entity.collider.getvy(),20, 20 );
                gc.fillText("Ball's Y:"+ entity.collider.getY(), 20, 40);
                gc.fillText("Ball's X:"+ entity.collider.getX(), 20, 60);
                Circle c = (Circle) entity.collider ;
                gc.setFill(Color.RED);
                gc.fillOval(c.getX() - c.getRadius(), c.getY() - c.getRadius(), c.getRadius() * 2, c.getRadius() * 2);
            }
        }
    }
    public void show() {
        System.out.println("FPS: " + frames);
    }
}
