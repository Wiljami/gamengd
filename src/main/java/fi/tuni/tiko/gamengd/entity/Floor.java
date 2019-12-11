package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Floor extends Entity {
    private static Image emptyFloor;

    public static void setup() {
        emptyFloor = ImageLoader.loadImage("voidtile.png");
    }

    public Floor(WritableImage image) {
        super(new Sprite(image));
    }

    public Floor() {
        super(new Sprite(emptyFloor));
    }
}