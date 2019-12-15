package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.controller.turn.TurnActor;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.util.GameMechanic;

public class Unit extends Entity implements TurnActor {
    Level level;

    private String name;
    private int attack;
    private int defense;
    private int hitPoints;
    private int maxHitPoints;
    static UIController uiController;

    public Unit(Sprite sprite) {
        super(sprite);
    }

    public static void registerUIController(UIController controller) {
        uiController = controller;
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
        int randomRoll = GameMechanic.randomRoll();
        int attackValue = getAttack() + randomRoll;
        String message = this.name + " rolls " + randomRoll + " for attack for total " + attackValue + ".";
        uiController.updateGameLog(message);
        target.receiveAttack(this, attackValue);
    }

    void receiveAttack (Unit attacker, int attackValue) {
        int randomRoll = GameMechanic.randomRoll();
        int defenseValue = getDefense() + randomRoll;

        String message = this.name + " rolls " + randomRoll + " for defense for total " + defenseValue + ".";
        uiController.updateGameLog(message);

        takeDamage(attackValue, defenseValue);
    }

    private void takeDamage(int attackValue, int defenseValue) {
        int damage = attackValue - defenseValue;
        if (damage < 0) damage = 0;
        setHitPoints(getHitPoints() - damage);
        String message = this.name + " takes " + damage + " points of damage";
        uiController.updateGameLog(message);
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

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public Level getLevel() {
        return level;
    }

    public void changeLevel(String id, int x, int y) {
    }
}