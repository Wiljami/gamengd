package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.controller.Crisis;
import fi.tuni.tiko.gamengd.controller.CrisisController;
import fi.tuni.tiko.gamengd.controller.CrisisSource;
import fi.tuni.tiko.gamengd.entity.*;
import fi.tuni.tiko.gamengd.util.json.JSONLoader;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;
import fi.tuni.tiko.gamengd.util.json.JacksonMap;
import fi.tuni.tiko.gamengd.scripts.pathfinding.AStarGraph;
import fi.tuni.tiko.gamengd.util.json.MonsterSpawn;

import java.util.ArrayList;
import java.util.Set;

public class Level implements CrisisSource {
    private Tile[][] map;
    private int width;
    private int height;
    private ArrayList<Unit> units = new ArrayList<>();
    private AStarGraph aStarGraph;
    private Player player;

    public Level(int width, int height) {
        generateEmptyMap(width, height);
    }

    public Level(String file, GameCore core) {
        JacksonLevel levelData = JSONLoader.loadLevel(file);
        JacksonMap mapData = JSONLoader.loadMap(levelData.getMap());
        int width = mapData.getWidth();
        int height = mapData.getHeight();
        generateEmptyMap(width, height);
        fillMap(mapData.getLayers().get(0).getData());
        spawnPlayer(levelData);
        spawnMonsters(levelData);
        registerCrisis(core.getCrisisController());
        setaStarGraph(new AStarGraph(this));
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

    private void spawnMonsters(JacksonLevel levelData) {
        for (MonsterSpawn s : levelData.getMonsterSpawns()) {
            addUnit(Monster.spawn(s.getType(), s.getSpawnPointX(), s.getSpawnPointY(), this));
        }
    }

    private void spawnPlayer(JacksonLevel levelData) {
        Sprite dude = new Sprite("dude.png");
        Player player = new Player(dude);
        player.setLevel(this);
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
        parseOpenTiles(getaStarGraph().getNodes().keySet());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        addUnit(player);
    }

    private Tile[] openTiles;

    private void parseOpenTiles(Set<Tile> data) {
        openTiles = new Tile[data.size()];
        int i = 0;
        for (Tile t : data) {
            openTiles[i] = t;
            i++;
        }
    }

    public void randomSpawn() {
        Tile tile = new Tile(this);
        boolean okSpawn = false;
        while (!okSpawn) {
            int randomLocation = (int)(Math.random() * openTiles.length);
            tile = openTiles[randomLocation];
            if (tile.isPassable() && tile.isEnterable()) {
                okSpawn = true;
            }
        }

        addUnit(Monster.spawn("monster01", tile, this));
    }

    private void registerCrisis(CrisisController crisisController) {
        crisisController.addCrisis(new Crisis(0.1, 0, "Hellurei", this));
    }

    @Override
    public void runCrisis(Crisis crisis) {
        randomSpawn();
    }
}