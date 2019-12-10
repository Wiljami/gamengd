package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Tile;
import fi.tuni.tiko.gamengd.controller.turn.TurnActor;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.util.GameMechanic;

public class Unit extends Entity implements TurnActor {
    Level level;

    private String name;
    private int attack;
    private int defense;
    private int hitPoints;

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

    void deliverAttack (Unit target) {
        int attackValue = getAttack() + GameMechanic.randomRoll();
        target.receiveAttack(attackValue);
    }

    void receiveAttack (int attackValue) {
        int defenseValue = getDefense() + GameMechanic.randomRoll();
        //System.out.println(defenseValue + " vs. " + attackValue + " = " + (defenseValue - attackValue));
        int damage = defenseValue - attackValue;
        takeDamage(damage);
    }

    private void takeDamage(int amount) {
        setHitPoints(getHitPoints()-amount);
    }

    public Tile getTile() {
        return level.getTileAt(getX(), getY());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}