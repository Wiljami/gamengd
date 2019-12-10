package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Tile;
import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.controller.input.CommandTarget;

public class Player extends Unit implements CommandTarget {
    private CameraController camera;
    public Player(Sprite sprite) {
        super(sprite);
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
                    moveX = 0;
                    moveY = 0;
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

    private void sortMove(Tile targetTile) {
        if (targetTile.hasUnit()) {
            attack(targetTile.getUnit());
        } else if (targetTile.isPassable()) {
            move(targetTile);
        }
        playerTurn = false;
        latestTurn.getTurnController().finishedTurn();
    }

    private void attack(Unit unit) {
        System.out.println("PUNCH MONSTER");
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
}