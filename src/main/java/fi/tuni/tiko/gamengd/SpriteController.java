package fi.tuni.tiko.gamengd;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class SpriteController {

    private ArrayList<Sprite> entitySprites = new ArrayList<>();
    private ArrayList<Sprite> tileSprites = new ArrayList<>();

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
}