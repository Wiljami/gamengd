package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;
import fi.tuni.tiko.gamengd.entity.Player;
import fi.tuni.tiko.gamengd.entity.Unit;
import fi.tuni.tiko.gamengd.entity.Wall;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;
import fi.tuni.tiko.gamengd.util.json.JacksonMap;
import fi.tuni.tiko.gamengd.util.Util;
import fi.tuni.tiko.gamengd.scripts.pathfinding.AStarGraph;
import java.util.ArrayList;

public class Level {
    private Tile[][] map;
    private int width;
    private int height;
    private ArrayList<Unit> units = new ArrayList<>();
    private AStarGraph aStarGraph;
    private Player player;

    public Level(int width, int height) {
        generateEmptyMap(width, height);
    }

    public Level(String file) {
        JacksonLevel levelData = Util.loadLevel(file);
        JacksonMap mapData = Util.loadMap(levelData.getMap());
        int width = mapData.getWidth();
        int height = mapData.getHeight();
        generateEmptyMap(width, height);
        fillMap(mapData.getLayers().get(0).getData());
        spawnPlayer(levelData);
    }

    private void generateEmptyMap(int width, int height) {
        setWidth(width);
        setHeight(height);
        map = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Tile tile =  new Tile(this, x, y, new Floor());
                tile.setPassable(false);
                map[x][y] = tile;
            }
        }
    }

    private void fillMap(int[] tileData) {
        int i = 0;
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Tile tile = null;
                if (tileData[i] != 0) {
                    tile = new Tile(this, x, y, new Floor("floor"));
                    map[x][y] = tile;
                }
                if (tileData[i] == 1) {
                       tile.addWall(new Wall());
                }
                i++;
            }
        }
    }

    private void spawnPlayer(JacksonLevel levelData) {
        Sprite dude = new Sprite("dude.png");
        Player player = new Player(dude);
        player.setXY(levelData.getPlayerSpawnX(), levelData.getPlayerSpawnY());
        setPlayer(player);
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
                Tile tile =  new Tile(this, x, y, new Floor("floor"));
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
            return new Tile(this);
        }
        return map[x][y];
    }

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public AStarGraph getaStarGraph() {
        return aStarGraph;
    }

    public void setaStarGraph(AStarGraph aStarGraph) {
        this.aStarGraph = aStarGraph;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        addUnit(player);
    }
}