package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;

public class Level {
    private Floor[][] map;
    private int width;
    private int height;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Floor[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = new Floor(x, y);
            }
        }
    }

    public Sprite getFloorAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Floor.voidTile();
        }
        return map[x][y].getSprite();
    }
}