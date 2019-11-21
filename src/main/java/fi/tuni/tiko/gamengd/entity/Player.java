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
        if (input.equals("HOME") || input.equals("NUMPAD7")) {
            move(-1, -1);
        } else if (input.equals("END") || input.equals("NUMPAD1")) {
            move(-1, 1);
        } else if (input.equals("PAGE_UP") || input.equals("NUMPAD9")) {
            move(1, -1);
        } else if (input.equals("PAGE_DOWN") || input.equals("NUMPAD3")) {
            move(1, 1);
        } else if (input.equals("LEFT") || input.equals("NUMPAD4")) {
            move(-1,0);
        } else if (input.equals("RIGHT") || input.equals("NUMPAD6")) {
            move(1,0);
        } else if (input.equals("UP") || input.equals("NUMPAD8")) {
            move(0,-1);
        } else if (input.equals("DOWN") || input.equals("NUMPAD2")) {
            move(0,1);
        }
        playerTurn = false;
        System.out.println("Player finished turn : " + latestTurn.getTurn());
        latestTurn.getTurnController().finishedTurn();
    }

    @Override
    void move(int x, int y) {
        if (level.getTileAt(getX()+x, getY()+y).isPassable()) {
            super.move(x, y);
            camera.setXY(getX() + 0.5, getY() + 0.5);
            System.out.println("Player at x: " + getX() + " y: " + getY());
        }
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        playerTurn = true;
        latestTurn = turnInfo;
    }
}