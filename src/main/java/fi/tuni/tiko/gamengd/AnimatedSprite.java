package fi.tuni.tiko.gamengd;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class AnimatedSprite extends Sprite {
    private double duration;
    private WritableImage[] frames;
    private int index;
    private double timeKey;

    public AnimatedSprite(String file, int framesX, int framesY, double duration) {
        super(file);
        frames = new WritableImage[framesX * framesY];
        this.duration = duration;
        PixelReader reader = getImage().getPixelReader();
        int i = 0;
        int width = (int) (getImage().getWidth()/framesX);
        int height = (int) (getImage().getHeight()/framesY);
        for (int x = 0; x < framesX; x++) {
            for (int y = 0; y < framesY; y++) {
                frames[i] = new WritableImage(reader, x * width, y * height, width, height);
                i++;
            }
        }
    }

    @Override
    public void update(double time) {
        super.update(time);
        timeKey += time;
        index = (int)((timeKey % duration)/duration * frames.length);
    }

    @Override
    public void render(GraphicsContext gc, double size) {
        gc.drawImage(frames[index], getPositionX(), getPositionY(), size, size);
    }
}