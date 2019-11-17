package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Camera;
import fi.tuni.tiko.gamengd.KeyListener;
import fi.tuni.tiko.gamengd.Sprite;

import java.util.List;

public class Player extends Entity implements KeyListener {
    private Camera camera;
    public Player(Sprite sprite) {
        super(sprite);
    }

    public void setupCamera (Camera camera) {
        this.camera = camera;
    }

    @Override
    public void receiveInput(List<String> input, double elapsedTime) {
    }

    @Override
    public void receiveInput(String input) {
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
    }

    @Override
    void move(int x, int y) {
        super.move(x, y);
        camera.setXY(getX() + 0.5, getY() + 0.5);
    }
}