package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.elements.Sprite;
import javafx.scene.image.WritableImage;

public class Wall extends Entity {
    public static void setup() {
    }

    public Wall(WritableImage image) {
        super(new Sprite(image));
    }
}