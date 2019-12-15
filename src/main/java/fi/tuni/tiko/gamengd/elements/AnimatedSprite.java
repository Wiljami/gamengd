package fi.tuni.tiko.gamengd.elements;

import fi.tuni.tiko.gamengd.util.ImageLoader;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;

/**
 * AnimatedSprite is a Sprite with multiple Image states.
 *
 * AnimatedSprite is a Sprite with collection of Images that it runs through
 * to create an animation.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class AnimatedSprite extends Sprite {
    /**
     * Overall duration of the animation.
     */
    private double duration;
    /**
     * Array of the Images.
     */
    private WritableImage[] frames;
    /**
     * Current image in the frames being displayed.
     */
    private int index;
    /**
     * TimeKey for timing the animation.
     */
    private double timeKey;

    /**
     * AnimatedSprite Constructor.
     * @param file filename of the image frames
     * @param framesX number of frames horizontally
     * @param framesY number of frames vertically
     * @param dur dur of the animation
     */
    public AnimatedSprite(String file, int framesX, int framesY, double dur) {
        super(file);
        frames = ImageLoader.createAnimatedArray(getImage(), framesX, framesY);
        this.duration = dur;
    }

    /**
     * Override update method from Sprite.
     *
     * The update method is ran every frame. AnimatedSprite needs to calculate
     * the index each time.
     * @param time time since last update
     */
    @Override
    public void update(double time) {
        super.update(time);
        timeKey += time;
        index = (int) ((timeKey % duration) / duration * frames.length);
    }

    /**
     * Override render method from Sprite.
     *
     * AnimatedSprite needs to draw the current frame. Thus image is set to the
     * frame in question.
     * @param gc GraphicsContext
     * @param size Size of the Sprite
     */
    @Override
    public void render(GraphicsContext gc, double size) {
        setImage(frames[index]);
        super.render(gc, size);
    }
}
