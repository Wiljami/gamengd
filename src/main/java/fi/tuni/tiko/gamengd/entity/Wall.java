package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Util;
import javafx.scene.image.Image;

public class Wall extends Entity {
    private static Image wall;

    public static void setupWalls() {
        wall = Util.loadImage("wall.png");
    }

    public Wall() {
        super(new Sprite(wall));
    }
}