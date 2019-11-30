package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.scripts.Util;
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
        setImage(Util.loadImage(file));
        setupPosition(positionX, positionY);
    }

    public Sprite(Image image) {
        this(image, 0, 0);
    }

    public Sprite(Image image, double positionX, double positionY) {
        setImage(image);
        setupPosition(positionX, positionY);
    }

    private void setupPosition(double positionX, double positionY) {
        setPositionX(positionX);
        setPositionY(positionY);
        setWidth(getImage().getWidth());
        setHeight(getImage().getHeight());
    }

    public void update(double time) {
        setPositionX(getPositionX() + getVelocityX() * time);
        setPositionY(getPositionY() + getVelocityY() * time);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(getImage(), getPositionX(), getPositionY(), getWidth(), getHeight());
    }


    public void render(GraphicsContext gc, double size) {
        gc.drawImage(getImage(), getPositionX(), getPositionY(), size, size);
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