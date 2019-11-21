package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Util;
import fi.tuni.tiko.gamengd.controller.TurnInfo;
import javafx.scene.image.Image;

public class Monster extends Unit {
    private static Image image;

    public static void setup() {
        image = Util.loadImage("monster.png");
    }

    public Monster(Level level) {
        super(new Sprite(image), level);
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        int x = (int)(Math.random()*3) - 1;
        int y = (int)(Math.random()*3) - 1;
        while (!level.getTileAt(getX()+x, getY()+y).isPassable()) {
            x = (int)(Math.random()*3) - 1;
            y = (int)(Math.random()*3) - 1;
        }
        move(x, y);
        super.doTurn(turnInfo);
    }
}