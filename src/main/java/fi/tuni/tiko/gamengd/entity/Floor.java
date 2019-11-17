package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Util;
import javafx.scene.image.Image;

public class Floor extends Entity {
    private static Image floor;
    private static Image emptyFloor;

    public static void setupFloors() {
        floor = Util.loadImage("tile.png");
        emptyFloor = Util.loadImage("voidtile.png");
    }

    public Floor(String type) {
        super(new Sprite(floor));
    }

    public Floor() {
        super(new Sprite(emptyFloor));
    }
}