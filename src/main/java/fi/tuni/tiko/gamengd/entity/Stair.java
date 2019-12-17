package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;
import javafx.scene.image.Image;

public class Stair extends Furniture {
    private String targetLevelId;
    private int targetX;
    private int targetY;
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

    public String getTargetLevelId() {
        return targetLevelId;
    }

    public void setTargetLevelId(String targetLevelId) {
        this.targetLevelId = targetLevelId;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    @Override
    public void unitEntered(Unit unit, Tile tile) {
        if (unit == unit.getLevel().getPlayer()) {
            tile.removeUnit();
            unit.changeLevel(getTargetLevelId(), getTargetX(), getTargetY());
        }
    }
}
