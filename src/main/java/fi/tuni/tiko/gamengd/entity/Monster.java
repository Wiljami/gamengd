package fi.tuni.tiko.gamengd.entity;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Tile;
import fi.tuni.tiko.gamengd.Util;
import fi.tuni.tiko.gamengd.controller.TurnInfo;
import fi.tuni.tiko.gamengd.scripts.pathfinding.AStar;
import javafx.scene.image.Image;

public class Monster extends Unit {
    private static Image image;
    private AStar pathfind;

    public static void setup() {
        image = Util.loadImage("monster.png");
    }

    public Monster(Level level) {
        super(new Sprite(image), level);
    }

    @Override
    public void doTurn(TurnInfo turnInfo) {
        if (pathfind != null) {
            movePathFinding();
        } else {
            chasePlayer();
        }
        super.doTurn(turnInfo);
    }

    private void movePathFinding() {
        Tile nextStep = pathfind.getStep();
        if (nextStep == null) {
            pathfind = null;
            randomMove();
        } else {
            move(nextStep);
        }
    }

    private void chasePlayer() {
        Tile endTile = level.getPlayer().getTile();
        createNewPath(endTile);
    }

    public void createNewPath(Tile endTile) {
        Tile startTile = level.getTileAt(getX(), getY());
        if (pathfind == null) pathfind = new AStar(level, startTile, endTile);
    }

    private void randomMove() {
        int x = (int)(Math.random()*3) - 1;
        int y = (int)(Math.random()*3) - 1;
        while (!level.getTileAt(getX()+x, getY()+y).isPassable()) {
            x = (int)(Math.random()*3) - 1;
            y = (int)(Math.random()*3) - 1;
        }
        move(x, y);
    }
}