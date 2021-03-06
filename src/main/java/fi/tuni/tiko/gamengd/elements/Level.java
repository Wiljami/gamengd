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
 * @version 2019.1220
 */
public class Level implements CrisisSource {
    /**
     * 2D-array of the game map in Tiles.
     */
    private Tile[][] map;
    /**
     * name of the TiledMap map for this level.
     */
    private String mapName;
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

    private ArrayList<Furniture> furnitures = new ArrayList<>();
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
     * Identifier String of this level.
     */
    private String id;

    /**
     * StairData data package of this level.
     */
    private ArrayList<JacksonLevel.StairData> stairData;

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
     * @param levelData object with the levelData
     * @param core reference to the gameCore
     */
    public Level(JacksonLevel levelData, GameCore core) {
        JacksonMap mapData = JacksonLoader.loadMap(levelData.getMap());
        setMapName(levelData.getMap());
        this.id = levelData.getId();
        this.turnController = core.getTurnController();
        int width = mapData.getWidth();
        int height = mapData.getHeight();
        ImageLoader.readTileSet(mapData.getTileSets());
        generateEmptyMap(width, height);
        fillMap(mapData);
        addStairs(levelData);
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
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {

                Tile tile = null;
                if (tileData[i] != 0) {
                    tile = new Tile(this, x, y, new Floor(tileSet[tileData[i] - 1]));
                    map[x][y] = tile;
                }
                if (wallData[i] != 0) {
                    if (tile == null) tile = new Tile(this, x, y, new Floor());
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
        for (JacksonLevel.MonsterSpawn s : levelData.getMonsterSpawns()) {
            Monster m = Monster.spawn(s.getType(), s.getSpawnPointX(), s.getSpawnPointY(), this);
            addUnit(m);
        }
    }

    /**
     * addStairs adds stairs furniture to the level
     *
     * addStairs uses the JacksonLevel data to place the stairs either up or
     * down and adds to them the data where they are connected.
     * @param levelData JacksonLevel data.
     */
    private void addStairs(JacksonLevel levelData) {
        setStairData(levelData.getStairs());
        if (levelData.getStairs() != null) {
            for (JacksonLevel.StairData s : levelData.getStairs()) {
                Stair stair = new Stair(s);
                getTileAt(s.getX(), s.getY()).addFurniture(stair);
            }
        }
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
     * getter for mapName.
     * @return mapName
     */
    public String getMapName() {
        return mapName;
    }

    /**
     * setter for mapName.
     * @param mapName new mapName.
     */
    public void setMapName(String mapName) {
        this.mapName = mapName;
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
     * @param id id of this spawn
     */
    public void randomSpawn(String id) {
        Tile tile = new Tile(this);
        boolean okSpawn = false;
        while (!okSpawn) {
            int randomLocation = (int)(Math.random() * openTiles.length);
            tile = openTiles[randomLocation];
            if (tile.isPassable() && tile.isEnterable()) {
                okSpawn = true;
            }
        }

        addUnit(Monster.spawn(id, tile, this));
    }

    /**
     * registerCrisis registers the Crisis.
     *
     * registerCrisis registers the Crisis within this object to the
     * crisisController.
     * @param crisisController crisisController.
     */
    private void registerCrisis(CrisisController crisisController) {
        crisisController.addCrisis(new Crisis(0.25, 0, "spawn01", this));
    }

    /**
     * runCrisis is the Level's method for dealing with crisis.
     *
     * runCrisis is called by the Crisis when it is triggered.
     */
    @Override
    public void runCrisis(Crisis crisis) {
        if (crisis.getId().equals("spawn01")) randomSpawn("monster01");
    }

    /**
     * getter for id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * getter for stairData.
     * @return stairData
     */
    public ArrayList<JacksonLevel.StairData> getStairData() {
        return stairData;
    }

    /**
     * setter for stairData.
     * @param stairData new stairData.
     */
    public void setStairData(ArrayList<JacksonLevel.StairData> stairData) {
        this.stairData = stairData;
    }

    /**
     * createJacksonLevel method creates a new data package out of the Level.
     *
     * createJacksonLevel creates a new JacksonLevel object for game saving
     * purposes. It is used to create a json file.
     * @return JacksonLevel of this Level.
     */
    public JacksonLevel createJacksonLevel() {
        JacksonLevel level = new JacksonLevel();
        level.setId(getId());
        level.setMap(getMapName());
        ArrayList<JacksonLevel.MonsterSpawn> monsterSpawns = new ArrayList<>();
        for (Unit unit : units) {
            if (unit != player) {
                System.out.println(unit);
                JacksonLevel.MonsterSpawn spawn = new JacksonLevel.MonsterSpawn();
                spawn.setSpawnPointX(unit.getX());
                spawn.setSpawnPointY(unit.getY());
                spawn.setType(unit.getId());
                monsterSpawns.add(spawn);
            }
        }
        level.setMonsterSpawns(monsterSpawns);
        ArrayList<JacksonLevel.StairData> stairData = new ArrayList<>();
        level.setStairs(getStairData());

        return level;
    }
}
