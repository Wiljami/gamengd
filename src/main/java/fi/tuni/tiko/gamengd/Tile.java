package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;
import fi.tuni.tiko.gamengd.entity.Furniture;
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
    //TODO: Does Tile need to know where it is?
    private int x;
    private int y;
    private Floor floor;
    private Wall wall;
    private List<Furniture> furnitures = new ArrayList<>();
    private boolean hasWall = false;
    private boolean passable = true;

    public Tile(int x, int y, Floor floor) {
        this.x = x;
        this.y = y;
        setFloor(floor);
    }

    public Tile() {
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
}