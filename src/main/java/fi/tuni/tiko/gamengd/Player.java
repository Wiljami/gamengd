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
        Sprite sprite = getSprite();
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