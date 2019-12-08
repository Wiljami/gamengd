package fi.tuni.tiko.gamengd.entity;

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
            int x = 0;
            int y = 0;
            boolean move = true;
            switch (input) {
                case "NW":
                    x = -1;
                    y = -1;
                    break;
                case "SW":
                    x = -1;
                    y = 1;
                    break;
                case "NE":
                    x = 1;
                    y = -1;
                    break;
                case "SE":
                    x = 1;
                    y = 1;
                    break;
                case "W":
                    x = -1;
                    y = 0;
                    break;
                case "E":
                    x = 1;
                    y = 0;
                    break;
                case "N":
                    x = 0;
                    y = -1;
                    break;
                case "S":
                    x = 0;
                    y = 1;
                    break;
                case "NONE":
                    x = 0;
                    y = 0;
                    break;
                default:
                    move = false;
                    break;
            }

            if (System.currentTimeMillis() - timeSinceLastMove > movementDelay) {
                timeSinceLastMove = System.currentTimeMillis();
                if (level.getTileAt(getX() + x, getY() + y).isPassable() && move) {
                    move(x, y);
                    playerTurn = false;
                    //System.out.println("Player finished turn : " + latestTurn.getTurn());
                    latestTurn.getTurnController().finishedTurn();
                }
            }
        }
    }

    @Override
    public void receiveCommand(String message) {
        sortInput(message);
    }

    @Override
    void move(int x, int y) {
        super.move(x, y);
        camera.setXY(getX() + 0.5, getY() + 0.5);
        //System.out.println("Player at x: " + getX() + " y: " + getY());
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