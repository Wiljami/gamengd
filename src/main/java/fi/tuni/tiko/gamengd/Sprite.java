package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.util.ImageLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Sprite holds texture and geometry for drawing 2d Sprites on Canvas.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class Sprite {
    /**
     * Image of the Sprite
     */
    private Image image;
    /**
     * Sprite's x-coordinate
     */
    private double positionX;
    /**
     * Sprite's y-coordinate
     */
    private double positionY;
    /**
     * Sprite's velocity on x-axis
     */
    private double velocityX;
    /**
     * Sprite's velocity on y-axis
     */
    private double velocityY;
    /**
     * Sprite's width
     */
    private double width;
    /**
     * Sprite's height
     */
    private double height;

    /**
     * Sprite constructor.
     * @param file filename of the Image
     */
    public Sprite(String file) {
        this(file, 0, 0);
    }

    /**
     * Sprite constructor with location.
     * @param file filename of the Image
     * @param positionX x-coordinate position
     * @param positionY y-coordinate position
     */
    public Sprite(String file, double positionX, double positionY) {
        setImage(ImageLoader.loadImage(file));
        setupPosition(positionX, positionY);
    }

    /**
     * Sprite constructor with Image object.
     * @param image Image object.
     */
    public Sprite(Image image) {
        this(image, 0, 0);
    }

    /**
     * Sprite constructor with Image object and location.
     * @param image Image object
     * @param positionX x-coordinate position
     * @param positionY y-coordinate position
     */
    public Sprite(Image image, double positionX, double positionY) {
        setImage(image);
        setupPosition(positionX, positionY);
    }

    /**
     * setupPosition places the Sprite to the given coordinates.
     * @param positionX x-coordinate position
     * @param positionY y-coordinate position
     */
    private void setupPosition(double positionX, double positionY) {
        setPositionX(positionX);
        setPositionY(positionY);
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
    }

    /**
     * update recalculates the Sprite's location.
     *
     * update uses the Sprite's velocity and the time since last update to
     * calculate the new position for the Sprite.
     * @param time time since last frame
     */
    public void update(double time) {
        setPositionX(getPositionX() + getVelocityX() * time);
        setPositionY(getPositionY() + getVelocityY() * time);
    }

    /**
     * render draws the Sprite using the GraphicsContext.
     * @param gc GraphicsContext used for the drawing
     */
    public void render(GraphicsContext gc) {
        gc.drawImage(getImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    /**
     * render draws the Sprite with specific size in mind.
     * @param gc GraphicsContext used for drawing
     * @param size size of the Sprite
     */
    public void render(GraphicsContext gc, double size) {
        gc.drawImage(getImage(), getPositionX(), getPositionY(), size, size);
    }

    /**
     * collides checks if this Sprite is overlapping another Sprite.
     * @param s Another Sprite
     * @return boolean wether collision is happening
     */
    public boolean collides(Sprite s) {
        return this.getEdge().intersects(s.getEdge());
    }

    /**
     * getEdge returns a 2d geometric shape that encompasses the Sprite.
     * @return Rectangle2D in shape of the Sprite
     */
    public Rectangle2D getEdge() {
        return new Rectangle2D(getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    /**
     * Image setter
     * @param image new image
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Image getter
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**
     * positionX setter.
     * @param positionX new positionX
     */
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    /**
     * positionX getter.
     * @return positionX
     */
    public double getPositionX() {
        return positionX;
    }

    /**
     * positionY setter.
     * @param positionY new positionY
     */
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    /**
     * positionY getter.
     * @return positionY
     */
    public double getPositionY() {
        return positionY;
    }

    /**
     * velocityX setter.
     * @param velocityX new velocityX
     */
    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    /**
     * velocityX getter.
     * @return velocityX
     */
    public double getVelocityX() {
        return velocityX;
    }

    /**
     * velocityY setter.
     * @param velocityY new velocityY
     */
    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    /**
     * velocityY getter.
     * @return velocityY
     */
    public double getVelocityY() {
        return velocityY;
    }

    /**
     * with setter.
     * @param width new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * width getter.
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * height setter.
     * @param height new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * height getter.
     * @return height
     */
    public double getHeight() {
        return height;
    }
}