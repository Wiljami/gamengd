package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;

public class Level {
    private Tile[][] map;
    private int width;
    private int height;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = new Tile(x, y, new Floor("floor"));
            }
        }
    }

    public Tile getTileAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return new Tile();
        }
        return map[x][y];
    }
}