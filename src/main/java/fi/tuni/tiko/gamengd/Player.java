package fi.tuni.tiko.gamengd;

import java.util.List;

public class Player extends Entity implements KeyListener {
    private int playerSpeed = 50;
    public Player(Sprite sprite) {
        super(sprite);
    }


    //TODO: Add location to entity and have the sprite recalculate its position based on that.
    @Override
    public void receiveInput(List<String> input, double elapsedTime) {
        if (input.contains("LEFT")) {
            moveLeft();
        }
        if (input.contains("RIGHT")) {
            moveRight();
        }
        if (input.contains("UP")) {
            moveUp();
        }
        if (input.contains("DOWN")) {
            moveDown();
        }
    }
}