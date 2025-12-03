package org.example.ecosimul.core.server;

import org.example.ecosimul.core.physic.collision.shape.Circle;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BallGenerator {
    public int W;
    public int H;
    public BallGenerator(int W, int H) {
        this.W = W;
        this.H = H;
    }
    public void generateAtPoint(World world, float posX, float posY, int ballNums, float ballRadius, float vx, float vy, long interval) {
        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count >= ballNums) {
                    executor.shutdown();
                    return;
                }

                world.safeAddEntity(new Entity(
                        new Circle(
                                posX,
                                posY,
                                ballRadius,
                                vx,vy
                        )
                ));


                count++;
            }

        }, 0, interval, TimeUnit.MILLISECONDS);
    }
    public void generateLineAsync(World world, int ballNums, int ballRadius, float posY, long interval) {

        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();

        // Tính spacing như generateLine cũ
        float spacing = (float) (W - ballNums * ballRadius) / (ballNums + 1);
        float startX = ballRadius;

        executor.scheduleAtFixedRate(new Runnable() {
            int count = 0;   // đếm trong phạm vi hàm
            float curX = startX;

            @Override
            public void run() {
                if (count >= ballNums) {
                    executor.shutdown();
                    return;
                }

                world.safeAddEntity(new Entity(
                        new Circle(
                                curX,
                                posY,
                                ballRadius,
                                0, 0
                        )
                ));

                // tính vị trí bóng kế tiếp
                curX += spacing;

                count++;
            }

        }, 0, interval, TimeUnit.MILLISECONDS);
    }


}
