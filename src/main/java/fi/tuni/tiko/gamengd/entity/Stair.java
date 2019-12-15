package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;
import javafx.scene.image.Image;

public class Stair extends Furniture {
    public Stair(JacksonLevel.StairData stairData) {
        Image image;
        if (stairData.isUp()) {
            image = ImageLoader.loadImage("stairsUp.png");
        } else {
            image = ImageLoader.loadImage("stairsDown.png");
        }
        setSprite(new Sprite(image));
        setXY(stairData.getX(), stairData.getY());
    }
}
