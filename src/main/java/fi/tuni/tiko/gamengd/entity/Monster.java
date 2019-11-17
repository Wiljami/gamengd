package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Util;
import javafx.scene.image.Image;

public class Monster extends Unit {
    private static Image image;

    public static void setup() {
        image = Util.loadImage("monster.png");
    }

    public Monster() {
        super(new Sprite(image));
    }
}