package fi.tuni.tiko.gamengd.controller;

import fi.tuni.tiko.gamengd.elements.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;
import java.util.List;

/**
 * SpriteController holds list of Sprites that are drawn.
 *
 * SpriteController's lists of Sprites are in turn handled by the
 * CameraController which calculates which Sprites actually are within the
 * canvas.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class SpriteController {

    /**
     * List of Tile Sprites.
     */
    private List<Sprite> tileSprites = new LinkedList<>();
    /**
     * List of Wall Sprites.
     */
    private List<Sprite> wallSprites = new LinkedList<>();
    /**
     * List of Furniture Sprites.
     */
    private List<Sprite> furnSprites = new LinkedList<>();
    /**
     * List of Unit Sprites.
     */
    private List<Sprite> unitSprites = new LinkedList<>();

    /**
     * render method goes through all the lists and renders their Sprites.
     *
     * render method calls the renderSprites in order. The order is important
     * as the first rendered Sprites go under the ones rendered after.
     * @param gc GraphicContext
     * @param time time since last frame
     * @param tileSize size of the tile
     */
    public void render(GraphicsContext gc, double time, double tileSize) {
        renderSprites(gc, time, tileSize, tileSprites);
        renderSprites(gc, time, tileSize, wallSprites);
        renderSprites(gc, time, tileSize, furnSprites);
        renderSprites(gc, time, tileSize, unitSprites);
    }

    /**
     * renderSprites renders a List of Sprites.
     * @param gc GraphicsContext
     * @param time time since last frame
     * @param tileSize size of the tiles
     * @param sprites list of Sprites
     */
    private void renderSprites(GraphicsContext gc, double time,
                               double tileSize, List<Sprite> sprites) {
        for (Sprite s : sprites) {
            s.update(time);
            s.render(gc, tileSize);
        }
    }

    /**
     * addFloorSprite adds a new Sprite to the tileSprites.
     * @param sprite Sprite to be added
     */
    public void addFloorSprite(Sprite sprite) {
        tileSprites.add(sprite);
    }

    /**
     * addWallSprite adds a new Sprite to the wallSprites.
     * @param sprite Sprite to be added
     */
    public void addWallSprite(Sprite sprite) {
        wallSprites.add(sprite);
    }

    /**
     * addFurnSprite adds a new Sprite to the furnSprites.
     * @param sprite Sprite to be added
     */
    public void addFurnSprite(Sprite sprite) {
        furnSprites.add(sprite);
    }

    /**
     * addUnitSprite adds a new Sprite to the unitSprites.
     * @param sprite Sprite to be added
     */
    public void addUnitSprite(Sprite sprite) {
        unitSprites.add(sprite);
    }

    /**
     * clear method clears all the Lists of the Sprites.
     */
    public void clear() {
        tileSprites.clear();
        wallSprites.clear();
        furnSprites.clear();
        unitSprites.clear();
    }
}
