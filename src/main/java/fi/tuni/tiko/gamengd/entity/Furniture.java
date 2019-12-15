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
 * @version 2019.1118
 */
public class Furniture extends Entity {
    public Furniture(Sprite sprite, int x, int y) {
        super(sprite, x, y);
    }

    public Furniture(Sprite sprite) {
        super(sprite);
    }

    public Furniture() {
        super();
    }

    public void unitEntered(Unit unit, Tile tile) {
    }

    public void unitLeft(Unit unit, Tile tile) {
    }
}