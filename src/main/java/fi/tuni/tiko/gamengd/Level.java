package fi.tuni.tiko.gamengd;

public class Level {
    private Tile[][] map;
    private int width;
    private int height;

    public Level(int width, int height) {
        map = new Tile[width][height];
        this.width = width;
        this.height = height;
    }

    public void addTilesToSpriteController(SpriteController sc) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sc.addSprite(map[x][y].getSprite());
            }
        }
    }
}