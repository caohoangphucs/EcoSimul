package org.example.ecosimul;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.ecosimul.core.Renderer;
import org.example.ecosimul.core.physic.collision.shape.Circle;
import org.example.ecosimul.core.server.*;
import org.example.ecosimul.core.physic.collision.SpatialGrid;
import org.example.ecosimul.core.physic.collision.Collider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBall extends Application {
    Rectangle2D screen = Screen.getPrimary().getBounds();
    int W = (int) screen.getWidth();
    int H = (int) screen.getHeight();
    List<Collider> balls = new ArrayList<>();
    Random random = new Random();
    SpatialGrid grid = new SpatialGrid(W, H, 50);
    World world = new World();
    Renderer renderer = new Renderer(world);
    BallGenerator ballGenerator = new BallGenerator((int) W, (int) H);
    @Override
    public void start(Stage stage) {
        Tick tickHandler = new Tick(120, new Action() {
            @Override
            public void run(long dt) {
                world.update(dt);
                renderer.update(dt);
            }
        });

        world.setScreenHeight((int) H);
        world.setScreenWidth((int) W);
        Canvas canvas = new Canvas(W, H);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Scene scene = new Scene(new javafx.scene.layout.StackPane(canvas), W, H);

        stage.setScene(scene);
        stage.setTitle("Multi-Bounce + Collision");
        stage.show();

        ballGenerator.generateAtPoint(world, 50, 50, 10, 50, 30, -100, 500);

        new AnimationTimer() {
            @Override

            public void handle(long now) {
                renderer.render(gc, canvas.getWidth(), canvas.getHeight());
            }
        }.start();
    }

    public static void main(String[] args) {
        launch();
    }
}
