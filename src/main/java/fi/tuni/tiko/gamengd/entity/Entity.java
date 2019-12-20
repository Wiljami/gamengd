package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.elements.Sprite;

/**
 * Entity is an abstract class that serves as the base of all the game pieces.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public abstract class Entity {
    /**
     * Sprite of the Entity
     */
    private Sprite sprite;
    /**
     * x-coordinate
     */
    private int x;
    /**
     * y-coordinate
     */
    private int y;

    /**
     * Reference to the GameCore in use.
     */
    static GameCore gameCore;

    /**
     * Constructor for Entity.
     * @param sprite Sprite of the Entity
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Entity (Sprite sprite, int x, int y) {
        setSprite(sprite);
        setXY(x, y);
    }

    /**
     * setup is a static method for Entity.
     *
     * setup will also call static setup methods for Floor and Monster.
     * @param c reference to the GameCore
     */
    public static void setup(GameCore c) {
        gameCore = c;
        Floor.setup();
        Monster.setup();
    }

    /**
     * Entity constructor.
     * @param sprite Sprite of the Entity.
     */
    public Entity (Sprite sprite) {
        setSprite(sprite);
    }

    /**
     * Entity constructor.
     */
    public Entity() {}

    /**
     * Sprite setter.
     * @param sprite new Sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Sprite getter.
     * @return sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * x getter.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * x setter.
     * @param x new x
     */
    public void setX(int x) {
        setXY(x, getY());
    }

    /**
     * y getter.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * y setter.
     * @param y new y
     */
    public void setY(int y) {
        setXY(getX(), y);
    }

    /**
     * x and y setter.
     * @param x new x
     * @param y new y
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
