package fi.tuni.tiko.gamengd;

public class Level {
    private Tile[][] map;
    private int width;
    private int height;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        map = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = new Tile(x, y);
            }
        }
    }


    //TODO: REMOVE THIS?
    public void addTilesToSpriteController(SpriteController sc) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                sc.addTileSprite(map[x][y].getSprite());
            }
        }
    }

    public Sprite getTileAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return Tile.voidTile();
        }
        return map[x][y].getSprite();
    }
}