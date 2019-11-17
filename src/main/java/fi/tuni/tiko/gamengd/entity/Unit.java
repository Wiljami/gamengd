package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;

public class Unit extends Entity {
    public Unit(Sprite sprite) {
        super(sprite);
    }

    void move(int x, int y) {
        setXY(getX() + x, getY() + y);
    }
}
