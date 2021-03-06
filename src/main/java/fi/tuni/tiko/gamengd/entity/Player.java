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

    /**
     * fileName of the player Sprite Image.
     */
    private String graphicFile;

    /**
     * boolean wether it is Player's turn now.
     */
    private boolean playerTurn = false;

    /**
     * setupCamera gives this Player reference to a CameraController.
     * @param camera CameraController
     */
    public void setupCamera (CameraController camera) {
        this.camera = camera;
    }

    /**
     * sortInput sorts through an input handed to the player and acts on it.
     *
     * sortInput receives a String input that contains a code which is compared
     * against the Player's set of codes. If match is found the Player will
     * then act accordingly. If player is dead, no action is taken and a message
     * is printed to the game log.
     * @param input input String generated by inputController.
     */
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

    /**
     * finishTurn method contains actions taken at the end of each player turn.
     */
    private void finishTurn() {
        playerTurn = false;
        latestTurn.getTurnController().finishedTurn();
    }

    /**
     * sortMove checks the result of a specific movement.
     *
     * If the targetTile contains another Unit then it turns the movement to an
     * attack. If the targetTile is not passable, nothing happens.
     * @param targetTile destination Tile of the movement.
     */
    private void sortMove(Tile targetTile) {
        if (targetTile.hasUnit()) {
            deliverAttack(targetTile.getUnit());
        } else if (targetTile.isPassable()) {
            move(targetTile);
        }
    }

    /**
     * receiveCommand is a method from CommandTarget interface.
     * @param message String message from the Command.
     */
    @Override
    public void receiveCommand(String message) {
        sortInput(message);
    }

    /**
     * move is Unit's method that is overridden in Player.
     *
     * The override is for letting the camera know its new center position
     * on the player. Otherwise the Player uses super.
     * @param tile destination Tile for movemnt
     */
    @Override
    void move(Tile tile) {
        super.move(tile);
        camera.setXY(getX() + 0.5, getY() + 0.5);
    }

    /**
     * registerChange lets the uiController know of a change in stats.
     */
    private void registerChange() {
        if (uiController != null) {
            uiController.trigger(this);
        }
    }

    /**
     * doTurn is a TurnActor interface method.
     * @param turnInfo information of the turn.
     */
    @Override
    public void doTurn(TurnInfo turnInfo) {
        playerTurn = true;
        latestTurn = turnInfo;
    }

    /**
     * getter for movementDelay.
     * @return movementDelay
     */
    public int getMovementDelay() {
        return movementDelay;
    }

    /**
     * setter for movementDelay.
     * @param movementDelay new movementDelay.
     */
    public void setMovementDelay(int movementDelay) {
        if (movementDelay < 0) movementDelay = 0;
        this.movementDelay = movementDelay;
    }

    /**
     * setter for name.
     * @param name new name
     */
    @Override
    public void setName(String name) {
        super.setName(name);
        registerChange();
    }

    /**
     * setter for attack.
     * @param attack new attack
     */
    @Override
    public void setAttack(int attack) {
        super.setAttack(attack);
        registerChange();
    }

    /**
     * setter for defense.
     * @param defense new defense
     */
    @Override
    public void setDefense(int defense) {
        super.setDefense(defense);
        registerChange();
    }

    /**
     * setter for hitPoints.
     *
     * setter also checks if the player has died or not. So all changes in
     * hitPoints should be done through this setter!
     * @param hitPoints new hitPoints value.
     */
    @Override
    public void setHitPoints(int hitPoints) {
        super.setHitPoints(hitPoints);
        registerChange();
        if (hitPoints <= 0) {
            uiController.updateGameLog("Oh no! " + getName() + " has perished!");
            dead = true;
        }
    }

    /**
     * ChangeLevel is Unit method overridden here.
     * @param id id of the new Level
     * @param x x-coordinate on the new Level
     * @param y y-coordinate on the new level
     */
    @Override
    public void changeLevel(String id, int x, int y) {
        setLevel(gameCore.getLevel(id));
        setXY(x, y);
        gameCore.changeLevel(id, this);
    }

    /**
     * getter for killCount.
     * @return killCount
     */
    public int getKillCount() {
        return killCount;
    }

    /**
     * setter for killCount
     * @param killCount new killCount
     */
    public void setKillCount(int killCount) {
        this.killCount = killCount;
        registerChange();
    }

    /**
     * addKill adds a single kill to the killCount.
     */
    public void addKill() {
        setKillCount(getKillCount() + 1);
    }

    /**
     * getter for graphicFile.
     * @return graphicFile
     */
    public String getGraphicFile() {
        return graphicFile;
    }

    /**
     * setter for graphicFile.
     * @param graphicFile new graphicFile
     */
    public void setGraphicFile(String graphicFile) {
        this.graphicFile = graphicFile;
    }

    /**
     * createJacksonPlayer turns the player data to a JacksonPlayer object.
     *
     * createJacksonPlayer gathers the player information to a JacksonPlayer
     * object that is then used for saving the game.
     * @return JacksonPlayer object
     */
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
