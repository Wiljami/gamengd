package fi.tuni.tiko.gamengd.controller;

import fi.tuni.tiko.gamengd.InputListener;
import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.Tile;
import fi.tuni.tiko.gamengd.entity.Furniture;
import fi.tuni.tiko.gamengd.entity.Unit;
import javafx.scene.canvas.Canvas;

import java.util.List;

public class CameraController implements InputListener {
    private double x;
    private double y;
    boolean cameraChanged;
    private double tileSize = 100;
    private Canvas canvas;
    private SpriteController spriteController;
    private Level level;

    public CameraController(Canvas canvas, SpriteController spriteController) {
        this.canvas = canvas;
        this.spriteController = spriteController;
    }

    public void updateSprites() {

        double centerSpriteX = canvas.getWidth() / 2 - (tileSize / 2);
        double centerSpriteY = canvas.getHeight() / 2 - (tileSize / 2);

        updateTileSprites(centerSpriteX, centerSpriteY);
        updateUnitSprites(centerSpriteX, centerSpriteY);

        setCameraChanged(false);
    }

    private void updateUnitSprites(double centerSpriteX, double centerSpriteY) {
        for (Unit unit : level.getUnits()) {
            Sprite s = unit.getSprite();
            int centerTileX = (int) getX();
            int centerTileY = (int) getY();
            s.setPositionX(centerSpriteX + (unit.getX() - centerTileX) * tileSize);
            s.setPositionY(centerSpriteY + (unit.getY() - centerTileY) * tileSize);
            spriteController.addUnitSprite(s);
        }
    }

    private void updateTileSprites(double centerSpriteX, double centerSpriteY) {
        int horizontalTiles = (int) Math.ceil(canvas.getWidth() / tileSize) + 1;
        if (horizontalTiles % 2 == 0) horizontalTiles++;

        int centerTileX = (int) getX();
        int centerTileY = (int) getY();

        int verticalTiles = (int) Math.ceil(canvas.getHeight() / tileSize) + 1;
        if (verticalTiles % 2 == 0) verticalTiles++;
        for (int x = -horizontalTiles/2; x <= horizontalTiles/2; x++) {
            for (int y = -verticalTiles/2; y <= verticalTiles/2; y++) {
                Tile tile = level.getTileAt(centerTileX + x, centerTileY + y);
                Sprite floor = tile.getFloor().getSprite();

                double positionX = centerSpriteX + x * tileSize;
                double positionY = centerSpriteY + y * tileSize;

                floor.setPositionX(positionX);
                floor.setPositionY(positionY);

                spriteController.addFloorSprite(floor);
                if (tile.hasWall()) {
                    Sprite wall = tile.getWall().getSprite();
                    wall.setPositionX(positionX);
                    wall.setPositionY(positionY);
                    spriteController.addWallSprite(wall);
                }
                for (Furniture f : tile.getFurnitures()) {
                    Sprite furn = f.getSprite();
                    furn.setPositionX(positionX);
                    furn.setPositionY(positionY);
                    spriteController.addFurnSprite(furn);
                }
            }
        }
    }

    public boolean isCameraChanged() {
        return cameraChanged;
    }

    public void setX(double x) {
        this.x = x;
        setCameraChanged(true);
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
        setCameraChanged(true);
    }

    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getY() {
        return y;
    }

    public void setTileSize(double tileSize) {
        if (tileSize < 10) tileSize = 10;
        if (tileSize > 100) tileSize = 100;
        this.tileSize = tileSize;
        setCameraChanged(true);
    }

    public double getTileSize() {
        return tileSize;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    //TODO: Smooth out the zoom with some sort of algorithm. Currently it zooms too fast once close.
    private void zoomIn() {
        setTileSize(getTileSize()+1);
    }

    private void zoomOut() {
        setTileSize(getTileSize()-1);
    }

    public void setCameraChanged(boolean cameraChanged) {
        this.cameraChanged = cameraChanged;
    }

    @Override
    public void receiveInput(List<String> input, double elapsedTime) {
        if (input.contains("ADD") || input.contains(("PLUS"))) {
            zoomIn();
        } else if (input.contains("SUBTRACT") || input.contains("MINUS")) {
            zoomOut();
        }
    }

    @Override
    public void receiveInput(String input) {
    }
}