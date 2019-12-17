package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.elements.Tile;

/**
 * Furniture class is an Entity within game.
 *
 * Furniture is a game element that is placed above floors and walls, but
 * under Units. This could be literal furniture or something like torch that
 * could be placed on top of a wall.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class Furniture extends Entity {
    /**
     * Furniture constructor.
     * @param sprite Sprite of the furniture.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Furniture(Sprite sprite, int x, int y) {
        super(sprite, x, y);
    }

    /**
     * Furniture constructor.
     * @param sprite Sprite of the furniture.
     */
    public Furniture(Sprite sprite) {
        super(sprite);
    }

    /**
     * Furniture constructor.
     */
    public Furniture() {
        super();
    }

    /**
     * unitEntered is called when an Unit enters tile with this furniture.
     *
     * By default this does nothing. Classes that extend Furniture can override
     * this to get functionality out of it.
     * @param unit Unit entering
     * @param tile Tile where this happens
     */
    public void unitEntered(Unit unit, Tile tile) {
    }

    /**
     * unitLeft is called when an Unit leaves a tile with this furniture.
     *
     * By default this does nothing. Classes that extend Furniture can override
     * this to get functionality out of it.
     * @param unit Unit leaving
     * @param tile Tile where this happens
     */
    public void unitLeft(Unit unit, Tile tile) {
    }
}
