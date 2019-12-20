package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.controller.input.CommandTarget;
import fi.tuni.tiko.gamengd.util.json.JacksonPlayer;

/**
 * Player class is the game entity controlled by the actual player.
 *
 * Player object is controlled by the actual player. The class holds the logic
 * and checks for the functionality.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class Player extends Unit implements CommandTarget {
    /**
     * Reference to the CameraController.
     */
    private CameraController camera;

    /**
     * Player controller.
     * @param playerData JacksonPlayer data package.
     * @param level Level where the player begins.
     */
    public Player(JacksonPlayer playerData, Level level) {
        super(new Sprite(playerData.getGraphicFile()));
        setGraphicFile(playerData.getGraphicFile());
        setLevel(level);
        setXY(playerData.getSpawnX(), playerData.getSpawnY());
        setAttack(playerData.getAttack());
        setDefense(playerData.getDefense());
        setHitPoints(playerData.getHitPoints());
        setMaxHitPoints(playerData.getMaxHitPoints());
        setName(playerData.getName());
        setKillCount(playerData.getKills());
        setMovementDelay(GameConfig.getPlayerMovementDelay());
        registerChange();
    }

    /**
     * info package of the latest turn.
     */
    private TurnInfo latestTurn;
    /**
     * Time since last move in milliseconds.
     */
    private long timeSinceLastMove = 0;
    /**
     * Delay between the player actions in milliseconds.
     */
    private int movementDelay = 150;
    /**
     * Number of kills the player has done.
     */
    private int killCount = 0;
    /**
     * Wether the player is dead or not.
     */
    private boolean dead = false;

    private String graphicFile;

    private boolean playerTurn = false;

    public void setupCamera (CameraController camera) {
        this.camera = camera;
    }

    private void sortInput(String input) {
        if (!dead) {
            if (playerTurn) {
                int moveX = 0;
                int moveY = 0;
                boolean move = true;
                boolean action = false;
                switch (input) {
                    case "NW":
                        moveX = -1;
                        moveY = -1;
                        break;
                    case "SW":
                        moveX = -1;
                        moveY = 1;
                        break;
                    case "NE":
                        moveX = 1;
                        moveY = -1;
                        break;
                    case "SE":
                        moveX = 1;
                        moveY = 1;
                        break;
                    case "W":
                        moveX = -1;
                        moveY = 0;
                        break;
                    case "E":
                        moveX = 1;
                        moveY = 0;
                        break;
                    case "N":
                        moveX = 0;
                        moveY = -1;
                        break;
                    case "S":
                        moveX = 0;
                        moveY = 1;
                        break;
                    case "NONE":
                        action = true;
                        break;
                    default:
                        move = false;
                        break;
                }

                if (System.currentTimeMillis() - timeSinceLastMove > movementDelay) {
                    timeSinceLastMove = System.currentTimeMillis();

                    if (!action) {
                        Tile targetTile = level.getTileAt(getX() + moveX, getY() + moveY);
                        if (move && targetTile.isPassable()) sortMove(targetTile);
                    }
                    finishTurn();
                }
            }
        } else {
            if (System.currentTimeMillis() - timeSinceLastMove > movementDelay) {
                uiController.updateGameLog("Looks like you're dead. Too bad. You can close the game and start a new game");
                timeSinceLastMove = System.currentTimeMillis();
            }
        }
    }

    private void finishTurn() {
        playerTurn = false;
        latestTurn.getTurnController().finishedTurn();
    }

    private void sortMove(Tile targetTile) {
        if (targetTile.hasUnit()) {
            attack(targetTile.getUnit());
        } else if (targetTile.isPassable()) {
            move(targetTile);
        }
    }

    private void attack(Unit unit) {
        deliverAttack(unit);
    }

    @Override
    public void receiveCommand(String message) {
        sortInput(message);
    }

    @Override
    void move(Tile tile) {
        super.move(tile);
        camera.setXY(getX() + 0.5, getY() + 0.5);
    }

    private void registerChange() {
        if (uiController != null) {
            uiController.trigger(this);
        }
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        playerTurn = true;
        latestTurn = turnInfo;
    }

    public int getMovementDelay() {
        return movementDelay;
    }

    public void setMovementDelay(int movementDelay) {
        if (movementDelay < 0) movementDelay = 0;
        this.movementDelay = movementDelay;
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        registerChange();
    }

    @Override
    public void setAttack(int attack) {
        super.setAttack(attack);
        registerChange();
    }

    @Override
    public void setDefense(int defense) {
        super.setDefense(defense);
        registerChange();
    }

    @Override
    public void setHitPoints(int hitPoints) {
        super.setHitPoints(hitPoints);
        registerChange();
        if (hitPoints <= 0) {
            uiController.updateGameLog("Oh no! " + getName() + " has perished!");
            dead = true;
        }
    }

    @Override
    public void changeLevel(String id, int x, int y) {
        setLevel(gameCore.getLevel(id));
        setXY(x, y);
        gameCore.changeLevel(id, this);
    }

    public int getKillCount() {
        return killCount;
    }

    public void setKillCount(int killCount) {
        this.killCount = killCount;
        registerChange();
    }

    public void addKill() {
        setKillCount(getKillCount() + 1);
    }

    public String getGraphicFile() {
        return graphicFile;
    }

    public void setGraphicFile(String graphicFile) {
        this.graphicFile = graphicFile;
    }

    public JacksonPlayer createJacksonPlayer() {
        JacksonPlayer player = new JacksonPlayer();
        player.setAttack(getAttack());
        player.setDefense(getDefense());
        player.setSpawnX(getX());
        player.setSpawnY(getY());
        player.setHitPoints(getHitPoints());
        player.setMaxHitPoints(getMaxHitPoints());
        player.setKills(getKillCount());
        player.setName(getName());
        player.setLevelId(getLevel().getId());
        player.setGraphicFile(getGraphicFile());
        return player;
    }
}
