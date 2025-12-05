package org.example.ecosimul.core.physic.collision;

import java.util.ArrayList;
import java.util.List;

public class SpatialGrid {
    int cellSize;
    int cols, rows;
    List<Collider>[][] grid;

    @SuppressWarnings("unchecked")
    public SpatialGrid(double width, double height, int cellSize) {
        this.cellSize = cellSize;
        this.cols = (int)(width / cellSize) + 1;
        this.rows = (int)(height / cellSize) + 1;

        grid = new List[cols][rows];
        clear();
    }

    public void clear() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

    public void add(Collider b) {
        int cx = (int)(b.getX() / cellSize);
        int cy = (int)(b.getY() / cellSize);

        if (cx >= 0 && cx < cols && cy >= 0 && cy < rows) {
            grid[cx][cy].add(b);
        }
    }

    public List<Collider> getNeighbors(Collider b) {
        int cx = (int)(b.getX() / cellSize);
        int cy = (int)(b.getY() / cellSize);

        List<Collider> neighbors = new ArrayList<>();

        for (int i = cx - 1; i <= cx + 1; i++) {
            for (int j = cy - 1; j <= cy + 1; j++) {
                if (i >= 0 && i < cols && j >= 0 && j < rows) {
                    neighbors.addAll(grid[i][j]);
                }
            }
        }

        return neighbors;
    }
}
