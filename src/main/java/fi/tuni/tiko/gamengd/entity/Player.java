package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.KeyListener;
import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.controller.TurnInfo;

import java.util.List;

public class Player extends Unit implements KeyListener {
    private CameraController camera;
    public Player(Sprite sprite, Level level) {
        super(sprite, level);
    }

    private TurnInfo latestTurn;

    private boolean playerTurn = false;

    public void setupCamera (CameraController camera) {
        this.camera = camera;
    }

    @Override
    public void receiveInput(List<String> input, double elapsedTime) {
    }

    @Override
    public void receiveInput(String input) {
        if (playerTurn) sortInput(input);
    }

    private void sortInput(String input) {
        int x = 0;
        int y = 0;
        if (input.equals("HOME") || input.equals("NUMPAD7")) {
            x = -1;
            y = -1;
        } else if (input.equals("END") || input.equals("NUMPAD1")) {
            x = -1;
            y = 1;
        } else if (input.equals("PAGE_UP") || input.equals("NUMPAD9")) {
            x = 1;
            y = -1;
        } else if (input.equals("PAGE_DOWN") || input.equals("NUMPAD3")) {
            x = 1;
            y = 1;
        } else if (input.equals("LEFT") || input.equals("NUMPAD4")) {
            x = -1;
            y = 0;
        } else if (input.equals("RIGHT") || input.equals("NUMPAD6")) {
            x = 1;
            y = 0;
        } else if (input.equals("UP") || input.equals("NUMPAD8")) {
            x = 0;
            y = -1;
        } else if (input.equals("DOWN") || input.equals("NUMPAD2")) {
            x = 0;
            y = 1;
        }
        if (level.getTileAt(getX()+x, getY()+y).isPassable()) {
            move(x, y);
            playerTurn = false;
            System.out.println("Player finished turn : " + latestTurn.getTurn());
            latestTurn.getTurnController().finishedTurn();
        }
    }

    @Override
    void move(int x, int y) {
        super.move(x, y);
        camera.setXY(getX() + 0.5, getY() + 0.5);
        System.out.println("Player at x: " + getX() + " y: " + getY());
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        playerTurn = true;
        latestTurn = turnInfo;
    }
}