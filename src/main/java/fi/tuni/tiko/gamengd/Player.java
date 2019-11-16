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
        if (input.equals("LEFT")) {
            moveLeft();
        }
        if (input.equals("RIGHT")) {
            moveRight();
        }
        if (input.equals("UP")) {
            moveUp();
        }
        if (input.equals("DOWN")) {
            moveDown();
        }
    }
}