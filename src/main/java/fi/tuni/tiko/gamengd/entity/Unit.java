package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.controller.turn.TurnActor;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.util.GameMechanic;

/**
 * Unit class is an abstract acting Entity in the game.
 *
 * Unit itself does nothing and other entities such as Monster and Player
 * extend Unit to gain the common features they share.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public abstract class Unit extends Entity implements TurnActor {
    /**
     * reference to the Level where this Unit is.
     */
    Level level;
    /**
     * id of the Unit.
     */
    private String id;
    /**
     * name of the Unit.
     */
    private String name;
    /**
     * attack of the Unit.
     */
    private int attack;
    /**
     * defense of the Unit.
     */
    private int defense;
    /**
     * hitPoints of the Unit.
     */
    private int hitPoints;
    /**
     * maxHitPoints of the Unit.
     */
    private int maxHitPoints;
    /**
     * Static reference to the UiController.
     */
    static UIController uiController;

    /**
     * UnitConstructor.
     * @param sprite Sprite of the Unit.
     */
    public Unit(Sprite sprite) {
        super(sprite);
    }

    /**
     * registerUIController registers a UIController for the Unit.
     * @param controller UIController
     */
    public static void registerUIController(UIController controller) {
        uiController = controller;
    }

    /**
     * setXY sets both x and y-coordinates.
     *
     * setXY also lets the target Tile know that this Unit entered it.
     * @param x new x-coordinate
     * @param y new y-coordinate
     */
    @Override
    public void setXY(int x, int y) {
        super.setXY(x, y);
        level.getTileAt(x, y).unitEnters(this);
    }

    /**
     * move moves this Unit to a destination Tile.
     *
     * move also lets the original Tile know that this Unit left it.
     * @param tile destination Tile.
     */
    void move(Tile tile) {
        level.getTileAt(getX(), getY()).unitLeaves(this);
        setXY(tile.getX(), tile.getY());
    }

    /**
     * move moves the unit to destination coordinates.
     * @param x destination x-coordinate
     * @param y destination y-coordinate
     */
    void move(int x, int y) {
        move(level.getTileAt(getX() + x, getY() + y));
    }

    /**
     * setter for level.
     * @param level new level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * doTurn is a method from TurnActor interface.
     *
     * doTurn is called when this Unit's turn starts.
     * @param turnInfo TurnInfo of the current turn.
     */
    @Override
    public void doTurn(TurnInfo turnInfo) {
        turnInfo.getTurnController().finishedTurn();
    }

    /**
     * deliverAttack makes this Unit attack another Unit.
     *
     * deliverAttack rolls a random value adds the Unit's base attack and
     * delivers this attack to the target Unit.
     * @param target target Unit.
     */
    void deliverAttack (Unit target) {
        int randomRoll = GameMechanic.randomRoll();
        int attackValue = getAttack() + randomRoll;
        String message = this.name + " rolls " + randomRoll + " for attack for total " + attackValue + ".";
        uiController.updateGameLog(message);
        target.receiveAttack(this, attackValue);
    }

    /**
     * receiveAttack takes an incoming attack and applies it to this Unit.
     *
     * receiveAttack makes this Unit roll for defense and compare it to the
     * incoming attackValue.
     * @param attacker attacking Unit
     * @param attackValue the value of the incoming attack
     */
    void receiveAttack (Unit attacker, int attackValue) {
        int randomRoll = GameMechanic.randomRoll();
        int defenseValue = getDefense() + randomRoll;

        String message = this.name + " rolls " + randomRoll + " for defense for total " + defenseValue + ".";
        uiController.updateGameLog(message);

        takeDamage(attackValue, defenseValue);
    }

    /**
     * takeDamage makes this unit take damage based on the attack and defense.
     * @param attackValue incoming attack
     * @param defenseValue defense
     */
    private void takeDamage(int attackValue, int defenseValue) {
        int damage = attackValue - defenseValue;
        if (damage < 0) damage = 0;
        String message = this.name + " takes " + damage + " points of damage";
        uiController.updateGameLog(message);
        setHitPoints(getHitPoints() - damage);
    }

    /**
     * getter for this Unit's tile. It grabs it from the Unit's level.
     * @return Unit's Tile
     */
    public Tile getTile() {
        return level.getTileAt(getX(), getY());
    }

    /**
     * getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for attack.
     * @return attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * setter for attack.
     * @param attack new attack
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * getter for defense.
     * @return new defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * setter for defense.
     * @param defense new defense
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * getter for hitPoints.
     * @return hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * setter for hitPoints.
     * @param hitPoints new hitPoints
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * getter for maxHitPoints.
     * @return maxHitPoints
     */
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    /**
     * setter for maxHitPoints.
     * @param maxHitPoints new maxHitPoints
     */
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    /**
     * getter for level.
     * @return level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * changeLevel currently does nothing for Units.
     * @param id target Level
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void changeLevel(String id, int x, int y) {
    }

    /**
     * getter for id.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * setter for id.
     * @param id new id
     */
    public void setId(String id) {
        this.id = id;
    }
}