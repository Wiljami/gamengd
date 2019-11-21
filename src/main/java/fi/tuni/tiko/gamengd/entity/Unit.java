package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.controller.TurnActor;
import fi.tuni.tiko.gamengd.controller.TurnInfo;

public class Unit extends Entity implements TurnActor {
    public Unit(Sprite sprite) {
        super(sprite);
    }

    void move(int x, int y) {
        setXY(getX() + x, getY() + y);
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        System.out.println("Unit::doTurn - turn: " + turnInfo.getTurn());
    }
}