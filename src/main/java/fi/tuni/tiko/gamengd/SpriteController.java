package fi.tuni.tiko.gamengd;

import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.List;

public class SpriteController {

    private List<Sprite> entitySprites = new LinkedList<>();
    private List<Sprite> tileSprites = new LinkedList<>();

    public void render(GraphicsContext gc, double time) {
        for (Sprite s : tileSprites) {
            s.update(time);
            s.render(gc);
        }

        for (Sprite s : entitySprites) {
            s.update(time);
            s.render(gc);
        }
    }

    public void addEntitySprite(Sprite sprite) {
        entitySprites.add(sprite);
    }

    public void addTileSprite(Sprite sprite) {
        tileSprites.add(sprite);
    }

    public void clear() {
        entitySprites.clear();
        tileSprites.clear();
    }
}