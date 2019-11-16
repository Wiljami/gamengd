package fi.tuni.tiko.gamengd;

import java.util.List;

public class Player extends Entity implements KeyListener {
    public Player(Sprite sprite) {
        super(sprite);
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
}