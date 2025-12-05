package org.example.ecosimul.core;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.ecosimul.core.physic.collision.Collider;
import org.example.ecosimul.core.physic.collision.ColliderType;
import org.example.ecosimul.core.physic.collision.shape.Circle;
import org.example.ecosimul.core.server.Entity;
import org.example.ecosimul.core.server.World;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private long frames = 0;
    public World world;
    public Renderer(World world) {
        this.world = world;
    }
    private long acc;
    private long framePerSecond;
    private long updateTimeMiliSeconds = 100;
    public void update(long dt) {
        frames++;
        acc += dt;
        if (acc >= updateTimeMiliSeconds) {
            acc = 0;
            framePerSecond = frames * 1000 / updateTimeMiliSeconds;
            frames = 0;
        }
    }
    public void render(GraphicsContext gc, double width, double height) {

        gc.setFill(Color.BLACK);
        gc.clearRect(0, 0, width, height);

        gc.setFill(Color.RED);
        gc.fillText("Ball number: " + world.entities.size(), 20, 20);
        gc.fillText("TPS: " + framePerSecond, 20, 40);

        gc.setFill(Color.RED);

        for (Entity entity : world.entities) {
            Circle c = (Circle) entity.collider;

            double x = c.getX();
            double y = c.getY();
            double r = c.getRadius();
            double d = r * 2;

            // skip offscreen
            if (x + r < 0 || x - r > width || y + r < 0 || y - r > height)
                continue;

            gc.fillOval(x - r, y - r, d, d);
        }
    }


    public void show() {
        System.out.println("FPS: " + frames);
    }
}
