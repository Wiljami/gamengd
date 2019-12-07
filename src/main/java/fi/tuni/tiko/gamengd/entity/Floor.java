package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import javafx.scene.image.Image;

public class Floor extends Entity {
    private static Image floor;
    private static Image emptyFloor;

    public static void setup() {
        floor = ImageLoader.loadImage("tile.png");
        emptyFloor = ImageLoader.loadImage("voidtile.png");
    }

    public Floor(String type) {
        super(new Sprite(floor));
    }

    public Floor() {
        super(new Sprite(emptyFloor));
    }
}