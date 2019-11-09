package fi.tuni.tiko.gamengd;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class SpriteController extends AnimationTimer {
    private long lastNanoTime;
    private GraphicsContext gc;
    private ArrayList<Sprite> sprites;
    private Canvas canvas;

    public SpriteController(Canvas canvas) {
        lastNanoTime = System.nanoTime();
        gc = canvas.getGraphicsContext2D();
        sprites = new ArrayList<>();
        this.canvas = canvas;
    }

    @Override
    public void handle(long currentNanoTime) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
        lastNanoTime = currentNanoTime;
        for (Sprite s : sprites) {
            s.update(elapsedTime);
            s.render(gc);
        }
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }
}