package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;
import fi.tuni.tiko.gamengd.entity.Wall;

public class Tile {
    //TODO: Does Tile need to know where it is?
    private int x;
    private int y;
    private Floor floor;
    private Wall wall;
    private boolean hasWall = false;

    public Tile(int x, int y, Floor floor) {
        this.x = x;
        this.y = y;
        setFloor(floor);
    }

    public Tile() {
        setFloor(new Floor());
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
    }

    public Wall getWall() {
        return wall;
    }

    public boolean hasWall() {
        return hasWall;
    }
}