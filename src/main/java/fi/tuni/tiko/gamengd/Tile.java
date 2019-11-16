package fi.tuni.tiko.gamengd;

import javafx.scene.image.Image;

public class Tile extends Entity {
    private static Image emptyTile;
    private static Image voidTile;

    public static void setupTiles() {
        emptyTile = Util.loadImage("tile.png");
        voidTile = Util.loadImage("voidtile.png");
    }

    public Tile(int x, int y) {
        super(new Sprite(emptyTile), x, y);
    }

    public static Sprite voidTile() {
        return new Sprite(voidTile);
    }
}