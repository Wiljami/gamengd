package fi.tuni.tiko.gamengd;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class SpriteController {

    private ArrayList<Sprite> sprites = new ArrayList<>();

    public void render(GraphicsContext gc, double time) {
        for (Sprite s : sprites) {
            s.update(time);
            s.render(gc);
        }
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
}