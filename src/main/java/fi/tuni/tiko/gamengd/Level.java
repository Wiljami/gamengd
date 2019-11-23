package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;
import fi.tuni.tiko.gamengd.entity.Unit;
import fi.tuni.tiko.gamengd.entity.Wall;

import java.util.ArrayList;

public class Level {
    private Tile[][] map;
    private int width;
    private int height;
    private ArrayList<Unit> units = new ArrayList<>();

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile =  new Tile(x, y, new Floor());
                tile.setPassable(false);
                map[x][y] = tile;
            }
        }
    }

    /**
     * createRoom method creates a room with floors and walls surrounding it.
     *
     * Creates a room with walls and floors. Currently there is no check to see if
     * the room is within the level.
     * @param positionX x-coordinate of the top left corner
     * @param positionY y-coordinate of the top left corner
     * @param width width of the room
     * @param height height of the room
     */
    public void createRoom(int positionX, int positionY, int width, int height) {
        for (int x = positionX; x < width + positionX; x++) {
            for (int y = positionY; y < height + positionY; y++) {
                Tile tile =  new Tile(x, y, new Floor("floor"));
                map[x][y] = tile;
                if (x == positionX || y == positionY ||
                        x == width + positionX-1 || y == height + positionY-1) {
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

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }
}