package fi.tuni.tiko.gamengd.controller;

import fi.tuni.tiko.gamengd.elements.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.List;

public class SpriteController {

    private List<Sprite> tileSprites = new LinkedList<>();
    private List<Sprite> wallSprites = new LinkedList<>();
    private List<Sprite> furnSprites = new LinkedList<>();
    private List<Sprite> unitSprites = new LinkedList<>();

    public void render(GraphicsContext gc, double time, double tileSize) {
        renderSprites(gc, time, tileSize, tileSprites);
        renderSprites(gc, time, tileSize, wallSprites);
        renderSprites(gc, time, tileSize, furnSprites);
        renderSprites(gc, time, tileSize, unitSprites);
    }

    private void renderSprites(GraphicsContext gc, double time,
                               double tileSize, List<Sprite> sprites) {
        for (Sprite s : sprites) {
            s.update(time);
            s.render(gc, tileSize);
        }
    }

    public void addFloorSprite(Sprite sprite) {
        tileSprites.add(sprite);
    }

    public void addWallSprite(Sprite sprite) {
        wallSprites.add(sprite);
    }

    public void addFurnSprite(Sprite sprite) {
        furnSprites.add(sprite);
    }

    public void addUnitSprite(Sprite sprite) {
        unitSprites.add(sprite);
    }

    public void clear() {
        tileSprites.clear();
        wallSprites.clear();
        furnSprites.clear();
        unitSprites.clear();
    }
}