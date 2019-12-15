package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.controller.input.CommandTarget;
import fi.tuni.tiko.gamengd.util.json.JacksonPlayer;

public class Player extends Unit implements CommandTarget {
    private CameraController camera;

    public Player(JacksonPlayer playerData, Level level) {
        super(new Sprite(playerData.getPlayerGraphicFile()));
        setLevel(level);
        setXY(playerData.getPlayerSpawnX(), playerData.getPlayerSpawnY());
        setAttack(playerData.getPlayerAttack());
        setDefense(playerData.getPlayerDefense());
        setHitPoints(playerData.getPlayerHitPoints());
    }

    private TurnInfo latestTurn;
    private long timeSinceLastMove = 0;
    private int movementDelay = 150;

    private boolean playerTurn = false;

    public void setupCamera (CameraController camera) {
        this.camera = camera;
    }

    private void sortInput(String input) {
        if (playerTurn) {
            int moveX = 0;
            int moveY = 0;
            boolean move = true;
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
                    move = false;
                    finishTurn();
                    break;
                default:
                    move = false;
                    break;
            }

            if (System.currentTimeMillis() - timeSinceLastMove > movementDelay) {
                timeSinceLastMove = System.currentTimeMillis();

                Tile targetTile = level.getTileAt(getX() + moveX, getY() + moveY);
                if (move && targetTile.isPassable()) sortMove(targetTile);
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
        finishTurn();
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
            uiController.trigger("");
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
    }
}