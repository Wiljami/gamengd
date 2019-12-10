package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Tile;
import fi.tuni.tiko.gamengd.controller.turn.TurnActor;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;

public class Unit extends Entity implements TurnActor {
    Level level;

    public Unit(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void setXY(int x, int y) {
        super.setXY(x, y);
        level.getTileAt(x, y).unitEnters(this);
    }

    void move(Tile tile) {
        level.getTileAt(getX(), getY()).unitLeaves(this);
        setXY(tile.getX(), tile.getY());
    }

    void move(int x, int y) {
        move(level.getTileAt(getX() + x, getY() + y));
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        turnInfo.getTurnController().finishedTurn();
    }

    public Tile getTile() {
        return level.getTileAt(getX(), getY());
    }
}