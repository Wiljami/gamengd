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
        target.receiveAttack(this, attackValue, message);
    }

    void receiveAttack (Unit attacker, int attackValue, String message) {
        int randomRoll = GameMechanic.randomRoll();
        int defenseValue = getDefense() + GameMechanic.randomRoll();

        message += " " + this.name + " rolls " + randomRoll + " for defence for total " + defenseValue + ".";
        uiController.trigger(message);

        takeDamage(attackValue, defenseValue);
    }

    private void takeDamage(int attackValue, int defenseValue) {
        int damage = defenseValue - attackValue;
        setHitPoints(getHitPoints() - damage);
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