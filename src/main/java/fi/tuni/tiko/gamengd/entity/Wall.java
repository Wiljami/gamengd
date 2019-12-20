package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.elements.Sprite;
import javafx.scene.image.WritableImage;

/**
 * Wall is an object in the game that is a visible boundary for the Units.
 *
 * Wall extends Entity. It is generally created using the JacksonMap data.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class Wall extends Entity {
    /**
     * Constructor for Wall.
     * @param image WritableImage of the Wall
     */
    public Wall(WritableImage image) {
        super(new Sprite(image));
    }
}
