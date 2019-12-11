package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Wall extends Entity {
    public static void setup() {
    }

    public Wall(WritableImage image) {
        super(new Sprite(image));
    }
}