package fi.tuni.tiko.gamengd.elements;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.controller.crisis.*;
import fi.tuni.tiko.gamengd.controller.turn.TurnController;
import fi.tuni.tiko.gamengd.entity.*;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import fi.tuni.tiko.gamengd.util.json.*;
import fi.tuni.tiko.gamengd.scripts.pathfinding.AStarGraph;
import javafx.scene.image.WritableImage;

import java.util.ArrayList;
import java.util.Set;

/**
 * Level class holds information of a game level.
 *
 * Level holds information of a single level within the game. It consists of
 * Unit data, Crisis data, Map data, Pathfinding data, etc. It implements
 * CrisisSource interface as it is a source of Crisis, for example new monster
 * spawns.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class Level implements CrisisSource {
    /**
     * 2D-array of the game map in Tiles.
     */
    private Tile[][] map;
    /**
     * width of the map.
     */
    private int width;
    /**
     * height of the map.
     */
    private int height;
    /**
     * ArrayList of Units on the level.
     */
    private ArrayList<Unit> units = new ArrayList<>();
    /**
     * Pathfinding data of the level.
     */
    private AStarGraph aStarGraph;
    /**
     * Reference to the player Unit on the level.
     */
    private Player player;
    /**
     * Reference to the TurnController.
     */
    private TurnController turnController;

    /**
     * Array of openTiles within the Level.
     */
    private Tile[] openTiles;

    /**
     * Constructor for creating empty level.
     *
     * Creates an empty level.
     * @param width width of the level
     * @param height height of the level
     */
    public Level(int width, int height) {
        generateEmptyMap(width, height);
    }

    /**
     * Constructor for creating a new level using json file.
     *
     * This constructor uses the file given to read a JacksonLevel object.
     * The JacksonLevel object holds the data the level needs to construct
     * itself.
     * @param file file name of the json file
     * @param core reference to the gameCore
     */
    public Level(JacksonLevel levelData, GameCore core) {
        JacksonMap mapData = JSONLoader.loadMap(levelData.getMap());
        this.turnController = core.getTurnController();
        int width = mapData.getWidth();
        int height = mapData.getHeight();
        ImageLoader.readTileSet(mapData.getTileSets());
        generateEmptyMap(width, height);
        fillMap(mapData);
        spawnPlayer(levelData);
        spawnMonsters(levelData);
        registerCrisis(core.getCrisisController());
        setaStarGraph(new AStarGraph(this));
    }

    /**
     * generateEmptyMap crates a level with an empty map.
     * @param width width of the level
     * @param height height of the level
     */
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

    /**
     * fillMap fills the map with the given data.
     *
     * fillMap reads the tileData array and begins to fill the level's map
     * with Tiles depending on the data.
     * @param mapData JacksonMap data
     */
    private void fillMap(JacksonMap mapData) {
        int[] tileData = {};
        int[] wallData = {};
        String tileSetKey = mapData.getTileSets().get(0).getName();
        WritableImage[] tileSet = ImageLoader.getTileSet(tileSetKey);
        for (MapLayer l : mapData.getLayers()) {
            if (l.getName().equals("Floor")) tileData = l.getData();
            if (l.getName().equals("Wall")) wallData = l.getData();
        }
        int i = 0;
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Tile tile = null;
                if (tileData[i] != 0) {
                    tile = new Tile(this, x, y, new Floor(tileSet[tileData[i] - 1]));
                    map[x][y] = tile;
                }
                if (wallData[i] != 0) {
                   if (tile == null)  tile = new Tile(this, x, y, new Floor());
                   tile.addWall(new Wall(tileSet[wallData[i] - 1]));
                }
                i++;
            }
        }
    }

    /**
     * spawnMonsters spawns the monsters defined in the JacksonLevel data.
     *
     * spawnMonsters reads through the JacksonLevel data and spawns the
     * monsters on the level by the given data.
     * @param levelData JacksonLevel data
     */
    private void spawnMonsters(JacksonLevel levelData) {
        for (MonsterSpawn s : levelData.getMonsterSpawns()) {
            Monster m = Monster.spawn(s.getType(), s.getSpawnPointX(), s.getSpawnPointY(), this);
            addUnit(m);
        }
    }

    /**
     * spawnMonsters spawns the player defined in the JacksonLevel data.
     * @param levelData JacksonLevel data
     */
    private void spawnPlayer(JacksonLevel levelData) {
        Player player = new Player(levelData.getPlayer(), this);
        setPlayer(player);
        addUnit(player);
    }

    /**
     * get Tile at x and y coordinate.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Tile at that coordinate
     */
    public Tile getTileAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return new Tile(this);
        }
        return map[x][y];
    }

    /**
     * addUnit adds an Unit to the level.
     *
     * addUnit adds an Unit to the level and adds it also to the
     * turnController.
     * @param unit Unit to be added.
     */
    public void addUnit(Unit unit) {
        units.add(unit);
        turnController.addTurn(unit);
    }

    /**
     * removeUnit removes an Unit from the level.
     *
     * Removes an Unit from the level and also removes it from the
     * turnContoller.
     * @param unit Unit to be removed
     */
    public void removeUnit(Unit unit) {
        units.remove(unit);
        turnController.removeTurn(unit);
    }

    /**
     * getter for the Units ArrayList.
     * @return units
     */
    public ArrayList<Unit> getUnits() {
        return units;
    }

    /**
     * setter for width.
     * @param width width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * setter for height
     * @param height height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * getter for width
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * getter for height
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter for the pathfinding data
     * @return aStarGraph
     */
    public AStarGraph getaStarGraph() {
        return aStarGraph;
    }

    /**
     * setter for aStarGraph.
     *
     * When aStarGraph is set it also parses through it to find the empty
     * Tiles within the level
     * @param aStarGraph aStarGraph
     */
    public void setaStarGraph(AStarGraph aStarGraph) {
        this.aStarGraph = aStarGraph;
        parseOpenTiles(getaStarGraph().getNodes().keySet());
    }

    /**
     * getter for player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * setter for player
     * @param player player
     */
    public void setPlayer(Player player) {
        this.player = player;
        addUnit(player);
    }

    /**
     * parseOpenTiles creates an Array of openTiles.
     *
     * parseOpenTiles parses through the set of Tiles received from the
     * pathfinding data. It then creates an array of Tiles with all the open
     * Tiles.
     * @param data pathfinding data
     */
    private void parseOpenTiles(Set<Tile> data) {
        openTiles = new Tile[data.size()];
        int i = 0;
        for (Tile t : data) {
            openTiles[i] = t;
            i++;
        }
    }

    /**
     * randomSpawn randomly spawns a monster to an open Tile.
     */
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

    /**
     * registerCrisis registers the Crisis.
     *
     * registerCrisis registers the Crisis within this object to the
     * crisisController.
     * @param crisisController crisisController.
     */
    private void registerCrisis(CrisisController crisisController) {
        crisisController.addCrisis(new Crisis(1, 0, "spawn01", this));
    }

    /**
     * runCrisis is the Level's method for dealing with crisis.
     *
     * runCrisis is called by the Crisis when it is triggered.
     */
    @Override
    public void runCrisis(Crisis crisis) {
        if (crisis.getId().equals("spawn01")) randomSpawn();
    }
}