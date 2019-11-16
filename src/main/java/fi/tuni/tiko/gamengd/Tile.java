package fi.tuni.tiko.gamengd;

public class Tile {
    private Sprite sprite;
    public Tile(int x, int y) {
        sprite = new Sprite("tile.png", x*100, y*100);
    }

    public Sprite getSprite() {
        return sprite;
    }
}