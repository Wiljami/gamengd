package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;
import fi.tuni.tiko.gamengd.entity.Wall;

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
                Tile tile =  new Tile(x, y, new Floor("floor"));
                map[x][y] = tile;
                if (x == 0 || y == 0 || x == width-1 || y == height-1) {
                    tile.addWall(new Wall());
                }
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