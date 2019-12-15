package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;

public class Stair extends Furniture {
    public Stair(JacksonLevel.StairData stairData) {
        super(new Sprite(ImageLoader.loadImage("asd")), stairData.getX(), stairData.getY());
    }
}
