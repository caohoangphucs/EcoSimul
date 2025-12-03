package org.example.ecosimul.core.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Tick {
    private float tickPerSecond = 20;
    private long lastTick = 0;
    private ScheduledFuture<?> gameLoopHandle;
    private final Action updateFunc;
    ScheduledExecutorService tickExecutor = Executors.newSingleThreadScheduledExecutor();

    public Tick(float tps, Action updateFunction) {
        this.tickPerSecond = tps;
        this.updateFunc = updateFunction;
        this.gameLoopHandle = createGameLoopHandle(tps, updateFunction);
    }
    public ScheduledFuture<?> createGameLoopHandle(float tps, Action callable) {
        long dt = (int) (1000f / tps);
        return tickExecutor.scheduleAtFixedRate(() -> callable.run(dt),
                0,
                dt,
                TimeUnit.MILLISECONDS);
    }

    public void setTick(int tps) {
        this.tickPerSecond = tps;

        if (gameLoopHandle != null && !gameLoopHandle.isCancelled()) {
            gameLoopHandle.cancel(false);
        }

        gameLoopHandle = createGameLoopHandle(tps, this.updateFunc);
    }
}
