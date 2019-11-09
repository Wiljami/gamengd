package fi.tuni.tiko.gamengd;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    public Sprite(String file) {
        this(file, 0, 0);
    }

    public Sprite(String file, double positionX, double positionY) {
        this(file, positionX, positionY, 0, 0);
    }

    public Sprite(String file, double positionX, double positionY, double velocityX, double velocityY) {
        setImage(Util.loadImage(file));
        setPositionX(positionX);
        setPositionY(positionY);
        setVelocityX(velocityX);
        setVelocityY(velocityY);
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
    }

    public void update(double time) {
        setPositionX(getPositionX() + getVelocityX() * time);
        setPositionY(getPositionY() + getVelocityY() * time);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    public boolean collides(Sprite s) {
        return this.getEdge().intersects(s.getEdge());
    }

    public Rectangle2D getEdge() {
        return new Rectangle2D(getPositionX(), getPositionY(), getWidth(), getHeight());
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }
}