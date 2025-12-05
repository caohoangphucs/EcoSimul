package org.example.ecosimul.core.server;

import org.example.ecosimul.core.physic.collision.*;
import org.example.ecosimul.core.physic.collision.resolve.Resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class World {
    public CollisionSystem collisionSystem;
    public SpatialGrid spatialGrid;
    public Resolver resolver;
    public Physic physic;
    public int screenWidth;
    public int screenHeight;

    public ConcurrentLinkedQueue<Entity> spawnQueue = new ConcurrentLinkedQueue<>();
    public void safeAddEntity(Entity e) {
        spawnQueue.add(e);
    }
    public List<Entity> entities = new ArrayList<>();
    public World() {
        this.spatialGrid = new SpatialGrid(1707,1067, 100);
        this.collisionSystem = new CollisionSystem();
        this.resolver = new Resolver();
        this.physic = new Physic();
    }
    public void addEntity(Entity entity){
        entities.add(entity);
    }
    public void removeEntity(Entity entity){
        entities.remove(entity);
    }
    public void update(long dt) {
        spatialGrid.clear();
        //Update spacialGrid for faster collision checking
        for (Entity e : entities) {
            spatialGrid.add(e.collider);
        }
        for (Entity entity : entities) {
            physic.update(entity, dt);

            //Collision border
            BorderTouchType borderTouchType = collisionSystem.isBorderCollision(entity.collider, screenWidth, screenHeight);
            if (borderTouchType != BorderTouchType.NONE) {
                resolver.resolveBorder(entity.collider, borderTouchType, screenWidth, screenHeight);
            }

            //Collision with other ball
            for (Collider entity2 : spatialGrid.getNeighbors(entity.collider)) {
                Collider col = (Collider) entity2;
                if (col != entity.collider) {
                    if (collisionSystem.isCollision(entity.collider, col))
                        resolver.resolve(new Collision(entity.collider, col, 1.1f));
                }
            }
        }

        Entity e;
        while ((e = spawnQueue.poll()) != null) {
            addEntity(e);
        }
    }


    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
