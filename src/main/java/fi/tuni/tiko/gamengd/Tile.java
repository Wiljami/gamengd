package fi.tuni.tiko.gamengd;

import javafx.scene.image.Image;

public class Tile extends Entity {
    private static Image emptyTile;

    public static void setupTiles() {
        emptyTile = Util.loadImage("tile.png");
    }

    public Tile(int x, int y) {
        super(new Sprite(emptyTile), x, y);
    }
}