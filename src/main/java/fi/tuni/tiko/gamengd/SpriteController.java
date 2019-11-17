package fi.tuni.tiko.gamengd;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.List;

public class SpriteController {

    private List<Sprite> tileSprites = new LinkedList<>();
    private List<Sprite> wallSprites = new LinkedList<>();
    private List<Sprite> unitSprites = new LinkedList<>();

    public void render(GraphicsContext gc, double time, double tileSize) {
        for (Sprite s : tileSprites) {
            s.update(time);
            s.render(gc, tileSize);
        }

        for (Sprite s : wallSprites) {
            s.update(time);
            s.render(gc, tileSize);
        }

        for (Sprite s : unitSprites) {
            s.update(time);
            s.render(gc, tileSize);
        }
    }

    public void addTileSprite(Sprite sprite) {
        tileSprites.add(sprite);
    }

    public void addWallSprite(Sprite sprite) {
        wallSprites.add(sprite);
    }

    public void addUnitSprite(Sprite sprite) {
        unitSprites.add(sprite);
    }

    public void clear() {
        unitSprites.clear();
        tileSprites.clear();
    }
}