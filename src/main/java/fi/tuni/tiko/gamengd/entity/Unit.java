package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Tile;
import fi.tuni.tiko.gamengd.controller.TurnActor;
import fi.tuni.tiko.gamengd.controller.TurnInfo;

public class Unit extends Entity implements TurnActor {
    //TODO: Is there a better way to do this? Do units really need a pointer to level?
    Level level;

    public Unit(Sprite sprite, Level level) {
        super(sprite);
        setLevel(level);
    }

    void move(int x, int y) {
        setXY(getX() + x, getY() + y);
    }

    void move(Tile tile) {
        setXY(tile.getX(), tile.getY());
        System.out.println(tile.getX() + "  " + tile.getY());
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        //System.out.println("Unit::doTurn - turn: " + turnInfo.getTurn());
        turnInfo.getTurnController().finishedTurn();
    }

    public Tile getTile() {
        return level.getTileAt(getX(), getY());
    }
}