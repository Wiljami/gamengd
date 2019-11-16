package fi.tuni.tiko.gamengd;

import java.util.List;

public class Player extends Entity implements KeyListener {
    public Player(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void receiveInput(List<String> input) {
        if (input.contains("LEFT")) {
            System.out.println("LEFT");
        }
        if (input.contains("RIGHT")) {
            System.out.println("RIGHT");
        }
    }
}