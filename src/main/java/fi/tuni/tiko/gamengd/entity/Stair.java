package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;
import javafx.scene.image.Image;

/**
 * Stair is a furniture with the task to connect levels.
 *
 * Stair extends furniture, gaining the furniture traits such the layer where
 * it is drawn and more importantly it makes stairs to be notified when an
 * Unit enters or leaves the tile it is in.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class Stair extends Furniture {
    /**
     * targetLevelId is the id to which the stairs leads to.
     */
    private String targetLevelId;
    /**
     * targetX is the x-coordinate where this stairs leads to.
     */
    private int targetX;
    /**
     * targetY is the y-coordinate where this stairs leads to.
     */
    private int targetY;

    /**
     * Stair constructor. The constructor uses the StairData object.
     * @param stairData StairData data package.
     */
    public Stair(JacksonLevel.StairData stairData) {
        Image image;
        if (stairData.isUp()) {
            image = ImageLoader.loadImage(GameConfig.getStairsUp());
        } else {
            image = ImageLoader.loadImage(GameConfig.getStairsDown());
        }
        setSprite(new Sprite(image));
        setXY(stairData.getX(), stairData.getY());
        setTargetLevelId(stairData.getConnection());
        setTargetX(stairData.getConX());
        setTargetY(stairData.getConY());
    }

    /**
     * getter for targetLevelId.
     * @return targetLevelId
     */
    public String getTargetLevelId() {
        return targetLevelId;
    }

    /**
     * setter for targetLevelId.
     * @param targetLevelId new targetLevelId
     */
    public void setTargetLevelId(String targetLevelId) {
        this.targetLevelId = targetLevelId;
    }

    /**
     * getter for targetX.
     * @return targetX
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * setter for targetX.
     * @param targetX new targetX
     */
    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    /**
     * getter for targetY.
     * @return targetY
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * setter for targetY.
     * @param targetY new targetY
     */
    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    /**
     * Overrides the Furniture unitEntered method.
     *
     * Checks if the Unit entering the Tile where this Stair is, is the player.
     * If it is the player, the Stairs will then transport the player to another
     * location.
     * @param unit Unit entering
     * @param tile Tile where this happens
     */
    @Override
    public void unitEntered(Unit unit, Tile tile) {
        if (unit == unit.getLevel().getPlayer()) {
            tile.removeUnit();
            unit.changeLevel(getTargetLevelId(), getTargetX(), getTargetY());
        }
    }
}
