package fi.tuni.tiko.gamengd;

import java.util.List;

public class Player extends Entity implements KeyListener {
    private int playerSpeed = 20;
    public Player(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void receiveInput(List<String> input, double elapsedTime) {
        sprite.setVelocityX(0);
        sprite.setVelocityY(0);
        if (input.contains("LEFT")) {
            sprite.setVelocityX(-playerSpeed);
        }
        if (input.contains("RIGHT")) {
            sprite.setVelocityX(playerSpeed);
        }
        if (input.contains("UP")) {
            sprite.setVelocityY(-playerSpeed);
        }
        if (input.contains("DOWN")) {
            sprite.setVelocityY(playerSpeed);
        }
    }
}