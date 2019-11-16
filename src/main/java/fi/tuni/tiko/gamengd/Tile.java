package fi.tuni.tiko.gamengd;

public class Tile {
    private Sprite sprite;
    public Tile() {
        sprite = new Sprite("tile.png");
    }

    public Sprite getSprite() {
        return sprite;
    }
}