package fi.tuni.tiko.gamengd;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

public class AnimatedSprite extends Sprite {
    private double duration;
    private WritableImage[] frames;
    private int index;
    private double timeKey;

    public AnimatedSprite(String file, int framesX, int framesY, double duration) {
        super(file);
        frames = Util.createAnimatedArray(getImage(), framesX, framesY);
        this.duration = duration;
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