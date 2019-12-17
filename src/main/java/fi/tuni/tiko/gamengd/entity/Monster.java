package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.util.GameMechanic;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import fi.tuni.tiko.gamengd.util.json.JSONLoader;
import fi.tuni.tiko.gamengd.util.Util;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.scripts.pathfinding.AStar;
import fi.tuni.tiko.gamengd.util.json.JacksonMonster;
import java.io.File;
import java.util.HashMap;

/**
 * Monster is a game element of independent creatures on the game.
 *
 * Monsters are an Unit that acts on their own logic.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class Monster extends Unit {
    /**
     * Behavior enum is the collection of different behavior states.
     */
    public enum Behavior {
        DEFAULT,
        AGGRESSIVE
    }

    /**
     * List of monsterProtoTypes.
     */
    private static HashMap<String, Monster> monsterProtoTypes;

    /**
     * The folder where the monsterProtoTypes are read from.
     */
    private static final String MONSTERFOLDER = "monsters/";

    /**
     * id of the Monster.
     */
    private String id;

    /**
     * AStar pathfinding data of the Monster.
     */
    private transient AStar pathfind;

    /**
     * Behavior state of the Monster.
     */
    private Behavior behavior = Behavior.DEFAULT;

    /**
     * setup is a static method to setup the Monster.
     *
     * It creates the monsterProtoTypes by reading through the MONSTERFOLDER.
     */
    public static void setup() {
        monsterProtoTypes = new HashMap<>();
        File[] monsterFiles = Util.walkFolder(MONSTERFOLDER);
        for (File f : monsterFiles) {
            Monster monster = JSONLoader.loadMonster(f);
            if (monsterProtoTypes.containsKey(monster.getId())) {
                System.out.println("Monster.setup()::Duplicate MonsterID: + " + monster.getId());
            }
            monsterProtoTypes.put(monster.getId(), monster);
        }
    }

    /**
     * Monster constructor using JacksonMonster.
     * @param jm JacksonMonster data
     */
    public Monster(JacksonMonster jm) {
        super(new Sprite(ImageLoader.loadImage(jm.getGraphic())));
        setId(jm.getId());
        setName(jm.getName());
        setAttack(jm.getAttack());
        setDefense(jm.getDefense());
        setHitPoints(jm.getHitPoints());
        setMaxHitPoints(getHitPoints());
        setBehavior(jm.getBehavior());
    }

    /**
     * Monster constructor using by cloning a protoMonster.
     * @param protoMonster Monster to clone.
     */
    public Monster(Monster protoMonster) {
        super(new Sprite(protoMonster.getSprite().getImage()));
        setId(protoMonster.getId());
        setName(protoMonster.getName());
        setAttack(protoMonster.getAttack());
        setDefense(protoMonster.getDefense());
        setHitPoints(protoMonster.getHitPoints());
        setBehavior(protoMonster.getBehavior());
    }

    /**
     * spawn is a static method for creating more Monsters.
     * @param id id of the monster
     * @param x x-coordinate
     * @param y y-coordinate
     * @param level monster's level
     * @return created monster
     */
    public static Monster spawn(String id, int x, int y, Level level) {
        Monster protoMonster = monsterProtoTypes.get(id);
        Monster monster = new Monster(protoMonster);
        monster.setLevel(level);
        monster.setXY(x, y);
        return monster;
    }

    /**
     * spawn is a static method for creating more Monsters.
     * @param id id of the monster
     * @param tile tile of the monster
     * @param level monster's level
     * @return created monster
     */
    public static Monster spawn(String id, Tile tile, Level level) {
        return spawn(id, tile.getX(), tile.getY(), level);
    }

    /**
     * doTurn overrides the Unit's doTurn to add Monster's own behavior to it.
     * @param turnInfo info of the turn
     */
    @Override
    public void doTurn(TurnInfo turnInfo) {
        switch (getBehavior()) {
            case DEFAULT: randomBehavior(); break;
            case AGGRESSIVE: aggressiveBehavior(); break;
            default: randomMove(); break;
        }
        super.doTurn(turnInfo);
    }

    /**
     * randomBehavior is the Monster's action when on DEFAULT behavior.
     *
     * There is a small chance each turn that the monster will become
     * AGGRESSIVE.
     */
    private void randomBehavior() {
        if (level.getPlayer() != null) {
            if (GameMechanic.randomRoll() == 12) {
                setBehavior(Behavior.AGGRESSIVE);
            }
        }
        randomMove();
    }

    /**
     * aggressiveBehavior is the Monster's action when on AGGRESSIVE behavior.
     *
     * Checks if the player is next to the monster. If yes, then attacks, if
     * not then tries to move after the player.
     */
    private void aggressiveBehavior() {
        if (checkForPlayerAdjacency()) {
            deliverAttack(level.getPlayer());
        } else if (pathfind != null) {
            movePathFinding();
        } else {
            chasePlayer();
        }
    }

    /**
     * checkForPlayerAdjacency checks through the surrounding tiles for player.
     * @return boolean wether player is found
     */
    private boolean checkForPlayerAdjacency () {
        if (level.getPlayer() != null) {
            Tile[] neighbours = level.getTileAt(getX(), getY()).getNeighbours();
            for (Tile t : neighbours) {
                if (t == level.getPlayer().getTile()) return true;
            }
        }
        return false;
    }

    /**
     * movePathFinding moves the Monster along its pathfinding data.
     *
     * If for some reason the Monster is unable to move along the path, it
     * will then take a random move.
     */
    private void movePathFinding() {
        Tile nextStep = pathfind.getStep();
        if (nextStep == null) {
            pathfind = null;
            randomMove();
        } else {
            move(nextStep);
        }
    }

    /**
     * Upon receiving an attack, Monster becomes automatically Aggressive.
     *
     * Override Unit's receiveAttack method, but call it right away. Set
     * Monster behavior to Aggressive upon receiving an attack.
     * @param attacker the unit attacking
     * @param attackValue the attackValue
     */
    @Override
    void receiveAttack(Unit attacker, int attackValue) {
        super.receiveAttack(attacker, attackValue);
        setBehavior(Behavior.AGGRESSIVE);
    }

    /**
     * chasePlayer creates a new pathfinding data from the Monster's current
     * position to the player's position.
     */
    private void chasePlayer() {
        Tile endTile = level.getPlayer().getTile();
        createNewPath(endTile);
    }

    /**
     * createNewPath creates a new path to the endTile.
     * @param endTile desired target tile for pathfinding.
     */
    public void createNewPath(Tile endTile) {
        Tile startTile = level.getTileAt(getX(), getY());
        if (pathfind == null) pathfind = new AStar(level, startTile, endTile);
    }

    /**
     * move overrides the Unit's move method.
     *
     * Monster attempts to move to target Tile, if unable then it will take a
     * random move.
     * @param tile target Tile for movement.
     */
    @Override
    void move(Tile tile) {
        if (!tile.hasUnit()) {
            super.move(tile);
        } else {
            if (randomMoveCheck()) randomMove();
        }
    }

    /**
     * randomMoveCheck checks if the Monster is able to make a random move.
     *
     * If all tiles are blocked around the Monster, the Monster is unable
     * to make a randomMove and this returns false.
     * @return boolean wether a randomMove is possible
     */
    private boolean randomMoveCheck() {
        Tile[] neighbours = level.getTileAt(getX(), getY()).getNeighbours();
        boolean turnDoable = false;
        for(Tile t : neighbours) {
            if (t.isEnterable()) {
                turnDoable = true;
            }
        }
        return turnDoable;
    }

    /**
     * randomMove randomized a random move to a nearby Tile.
     */
    private void randomMove() {
        int x = (int)(Math.random()*3) - 1;
        int y = (int)(Math.random()*3) - 1;
        while (!level.getTileAt(getX()+x, getY()+y).isPassable()) {
            x = (int)(Math.random()*3) - 1;
            y = (int)(Math.random()*3) - 1;
        }
        move(x, y);
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

    /**
     * getter for behavior.
     * @return behavior enum
     */
    public Behavior getBehavior() {
        return behavior;
    }

    /**
     * setter for behavior.
     * @param behavior new behavior
     */
    public void setBehavior(Behavior behavior) {
        this.behavior = behavior;
    }

    /**
     * setHitPoints is overriden from Unit. Monster checks if it dies.
     *
     * setHitPoints checks if the hitPoints are under 1. If under the Monster dies.
     * Player is informed of the kill and the Monster is removed from the level.
     * @param hitPoints new hitPoints value
     */
    @Override
    public void setHitPoints(int hitPoints) {
        super.setHitPoints(hitPoints);
        if (getHitPoints() <= 0) {
            String msg = getName() + " dies!";
            level.getPlayer().addKill();
            uiController.updateGameLog(msg);
            getTile().removeUnit();
            level.removeUnit(this);
        }
    }
}
