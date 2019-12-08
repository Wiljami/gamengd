package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;
import fi.tuni.tiko.gamengd.entity.Furniture;
import fi.tuni.tiko.gamengd.entity.Unit;
import fi.tuni.tiko.gamengd.entity.Wall;

import java.util.ArrayList;
import java.util.List;

/**
 * Tile class holds the information for each gametile.
 *
 * Tile class holds the information what is on this one specific Tile.
 *
 * @author Viljami Pietarila
 * @version 2019.1118
 */
public class Tile {
    private int x;
    private int y;
    private Floor floor;
    private Wall wall;
    private List<Furniture> furnitures = new ArrayList<>();
    private Level level;
    private boolean hasWall = false;
    private boolean passable = true;
    private boolean hasUnit = false;
    private Unit unit;

    public Tile(Level level, int x, int y, Floor floor) {
        this.x = x;
        this.y = y;
        this.level = level;
        setFloor(floor);
    }

    public Tile(Level level) {
        this.level = level;
        setFloor(new Floor());
        passable = false;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public void addWall(Wall wall) {
        this.wall = wall;
        hasWall = true;
        passable = false;
    }

    public void removeWall() {
        this.wall = null;
        hasWall = false;
        passable = true;
    }

    public void addFurniture(Furniture furniture) {
        furnitures.add(furniture);
    }

    public List<Furniture> getFurnitures() {
        return furnitures;
    }

    public Wall getWall() {
        return wall;
    }

    public boolean hasWall() {
        return hasWall;
    }

    public boolean isPassable() {
        return passable;
    }

    public boolean isEnterable() {
        return passable && !hasUnit;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }

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

    public void unitEnters(Unit unit) {
        this.unit = unit;
        hasUnit = true;
    }

    //In case there is some event or something that wants to know the unit!
    public void unitLeaves(Unit unit) {
        this.unit = null;
        hasUnit = false;
    }

    public boolean hasUnit() {
        return hasUnit;
    }

    public Unit getUnit() {
        return unit;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}