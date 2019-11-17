package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Util;
import javafx.scene.image.Image;

public class Floor extends Entity {
    private static Image emptyTile;
    private static Image voidTile;

    public static void setupTiles() {
        emptyTile = Util.loadImage("tile.png");
        voidTile = Util.loadImage("voidtile.png");
    }

    public Floor(int x, int y) {
        super(new Sprite(emptyTile), x, y);
    }

    public static Sprite voidTile() {
        return new Sprite(voidTile);
    }
}