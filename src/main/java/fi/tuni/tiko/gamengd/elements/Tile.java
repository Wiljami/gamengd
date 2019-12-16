package fi.tuni.tiko.gamengd.elements;

import fi.tuni.tiko.gamengd.entity.*;

import java.util.ArrayList;

/**
 * Tile class holds the information for each gametile.
 *
 * Tile class holds the information what is on this one specific Tile.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class Tile {
    /**
     * x coordinate of the Tile.
     */
    private int x;
    /**
     * y coordinate of the Tile.
     */
    private int y;
    /**
     * Floor of the Tile.
     */
    private Floor floor;
    /**
     * Wall of the Tile.
     */
    private Wall wall;
    /**
     * List of furnitures on the Tile.
     */
    private ArrayList<Furniture> furnitures = new ArrayList<>();
    /**
     * Reference to the Tile's level.
     */
    private Level level;
    /**
     * Boolean wether the Tile has Wall.
     */
    private boolean hasWall = false;
    /**
     * Boolean wether the Tile is passable.
     */
    private boolean passable = true;
    /**
     * Boolean wether the Tile has an Unit.
     */
    private boolean hasUnit = false;
    /**
     * Unit on the Tile.
     */
    private Unit unit;

    /**
     * Tile constructor.
     * @param level Tile's level
     * @param x x-coordinate
     * @param y y-coordinate
     * @param floor floor of the Tile
     */
    public Tile(Level level, int x, int y, Floor floor) {
        this.x = x;
        this.y = y;
        this.level = level;
        setFloor(floor);
    }

    /**
     * Constructor for dummy Tile.
     *
     * Dummy Tile is a 'non-tile' the CameraController creates to fill the
     * screen. These should never be accessed within the game.
     * @param level Level of the Tile
     */
    public Tile(Level level) {
        this.level = level;
        setFloor(new Floor());
        passable = false;
    }

    /**
     * floor setter.
     * @param floor Floor
     */
    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    /**
     * floor getter.
     * @return floor
     */
    public Floor getFloor() {
        return floor;
    }

    /**
     * addWall adds a wall on the Tile and sets the Tile impassable.
     * @param wall Wall to be added
     */
    public void addWall(Wall wall) {
        this.wall = wall;
        hasWall = true;
        passable = false;
    }

    /**
     * removeWall removes the wall on the Tile and sets the Tile passable.
     */
    public void removeWall() {
        this.wall = null;
        hasWall = false;
        passable = true;
    }

    /**
     * addFurniture adds a furniture to the furniture list.
     * @param furniture Furniture
     */
    public void addFurniture(Furniture furniture) {
        furnitures.add(furniture);
    }

    /**
     * getter for furnitures list.
     * @return furnitures
     */
    public ArrayList<Furniture> getFurnitures() {
        return furnitures;
    }

    /**
     * getter for the Wall. Does not care if it's null or not.
     * @return wall
     */
    public Wall getWall() {
        return wall;
    }

    /**
     * returns boolean depending wether there is a wall or not.
     * @return hasWall boolean.
     */
    public boolean hasWall() {
        return hasWall;
    }

    /**
     * returns boolean wether the Tile is passable or not.
     * @return passable
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * isEnterable returns boolean wether a new Unit can enter the Tile or not.
     *
     * Checks if the Tile is passable and if there is some Unit on the tile.
     * @return boolean of passable and !hasUnit
     */
    public boolean isEnterable() {
        return passable && !hasUnit;
    }

    /**
     * setter for passable.
     * @param passable boolean
     */
    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    /**
     * gets an array of Tiles that are next to this Tile.
     * @return Array of Tiles.
     */
    public Tile[] getNeighbours() {
        Tile[] tiles = new Tile[8];
        tiles[0] = level.getTileAt(x-1, y-1);
        tiles[1] = level.getTileAt(x, y-1);
        tiles[2] = level.getTileAt(x+1, y-1);
        tiles[3] = level.getTileAt(x+1, y);
        tiles[4] = level.getTileAt(x+1, y+1);
        tiles[5] = level.getTileAt(x, y+1);
        tiles[6] = level.getTileAt(x-1, y+1);
        tiles[7] = level.getTileAt(x-1, y);
        return tiles;
    }

    /**
     * unitEnters is called when an Unit enters the Tile.
     *
     * Sets the tile to hold an Unit.
     * @param unit Unit entering
     */
    public void unitEnters(Unit unit) {
        this.unit = unit;
        for (Furniture furniture : furnitures) {
            furniture.unitEntered(unit, this);
        }
        hasUnit = true;
    }

    /**
     * unitLeaves is called when an Unit leaves the Tile.
     *
     * Sets the Tile free also.
     * @param unit Unit leaving the Tile.
     */
    public void unitLeaves(Unit unit) {
        this.unit = null;
        for (Furniture furniture : furnitures) {
            furniture.unitLeft(unit, this);
        }
        hasUnit = false;
    }

    public void removeUnit() {
        this.unit = null;
        hasUnit = false;
    }

    /**
     * return wether the Tile has an unit on it.
     * @return boolean hasUnit.
     */
    public boolean hasUnit() {
        if (getUnit() == null) {
            hasUnit = false;
            return false;
        }
        return hasUnit;
    }

    /**
     * get the Unit on the Tile. Does not care if it's null.
     * @return Unit on the tile
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * getter for the x-coordinate.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * getter for the y-coordinate.
     * @return y
     */
    public int getY() {
        return y;
    }

    public String getToolTip() {
        String toolTip = "";
        toolTip += "x: " + getX() + " y: " + getY();
        return toolTip;
    }
}