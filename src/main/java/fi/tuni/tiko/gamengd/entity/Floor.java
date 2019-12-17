package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * Floor is the bottom element on a Tile.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class Floor extends Entity {
    /**
     * Image of an empty Floor.
     */
    private static Image emptyFloor;

    /**
     * Floor's static setup method setups the emptyFloor image.
     */
    public static void setup() {
        emptyFloor = ImageLoader.loadImage(GameConfig.getVoidTile());
    }

    /**
     * Floor constructor.
     * @param image WritableImage of the floor.
     */
    public Floor(WritableImage image) {
        super(new Sprite(image));
    }

    /**
     * Floor constructor.
     *
     * A floor constructor without WritableImage will use the emptyFloor.
     */
    public Floor() {
        super(new Sprite(emptyFloor));
    }
}
