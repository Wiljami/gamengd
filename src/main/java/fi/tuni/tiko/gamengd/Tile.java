package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Floor;

public class Tile {
    //TODO: Does Tile need to know where it is?
    private int x;
    private int y;
    private Floor floor;
    public Tile(int x, int y, Floor floor) {
        this.x = x;
        this.y = y;
        setFloor(floor);
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }
}
