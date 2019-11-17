package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Util;
import javafx.scene.image.Image;

public class Floor extends Entity {
    private static Image floor;
    private static Image emptyFloor;

    public static void setupTiles() {
        floor = Util.loadImage("tile.png");
        emptyFloor = Util.loadImage("voidtile.png");
    }

    public Floor(int x, int y) {
        super(new Sprite(floor), x, y);
    }

    public static Sprite voidTile() {
        return new Sprite(emptyFloor);
    }
}