package fi.tuni.tiko.gamengd.controller;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Sprite;
import fi.tuni.tiko.gamengd.elements.Tile;
import fi.tuni.tiko.gamengd.controller.input.CommandTarget;
import fi.tuni.tiko.gamengd.controller.turn.TurnInfo;
import fi.tuni.tiko.gamengd.controller.turn.TurnListener;
import fi.tuni.tiko.gamengd.entity.Furniture;
import fi.tuni.tiko.gamengd.entity.Unit;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import java.util.HashMap;

/**
 * CameraController controls what is shown on the canvas.
 *
 * CameraController calculates what game elements are actually shown on the
 * canvas. It is responsible for making sure that no Sprites outside the
 * canvas are being drawn for no reason.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class CameraController implements CommandTarget, TurnListener {
    /**
     * x-coordinate of the center point of the camera.
     */
    private double x;
    /**
     * y-coordinate of the center point of the camera.
     */
    private double y;
    /**
     * boolean wether the camera has been changed.
     */
    private boolean cameraChanged;
    /**
     * size of a game tile in pixels.
     */
    private double tileSize;
    /**
     * Reference to the game canvas.
     */
    private Canvas canvas;
    /**
     * Reference to the spriteController.
     */
    private SpriteController spriteController;
    /**
     * Reference to the current Level.
     */
    private Level level;

    /**
     * CameraController constructor.
     * @param canvas canvas to be controlled
     * @param spriteController spriteController reference
     */
    public CameraController(Canvas canvas, SpriteController spriteController) {
        this.tileSize = GameConfig.getTileSize();
        this.canvas = canvas;
        this.spriteController = spriteController;
        ChangeListener<Number> canvasSizeListener = (observable, oldValue, newValue) ->
                this.setCameraChanged(true);

        this.canvas.widthProperty().addListener(canvasSizeListener);
        this.canvas.heightProperty().addListener(canvasSizeListener);
    }

    /**
     * updateSprites updates what Sprites are displayed on the canvas.
     */
    public void updateSprites() {

        double centerSpriteX = canvas.getWidth() / 2 - (tileSize / 2);
        double centerSpriteY = canvas.getHeight() / 2 - (tileSize / 2);

        updateTileSprites(centerSpriteX, centerSpriteY);
        updateUnitSprites(centerSpriteX, centerSpriteY);

        setCameraChanged(false);
    }

    /**
     * updateUnitSprites adds Units on the SpriteController.
     *
     * @param centerSpriteX x-coordinate of the center tile
     * @param centerSpriteY y-coordinate of the center tile
     */
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

    /**
     * updateTileSprites determines what Tiles are displayed on the canvas.
     *
     * updateTileSprites calculates using the canvas size and the tileSize
     * which Tiles are actually within the canvas. It then adds those tiles to
     * the spriteController.
     *
     * @param centerSpriteX x-coordinate of the center tile
     * @param centerSpriteY y-coordinate of the center tile
     */
    private void updateTileSprites(double centerSpriteX, double centerSpriteY) {
        int horizontalTiles = (int) Math.ceil(canvas.getWidth() / tileSize) + 1;
        if (horizontalTiles % 2 == 0) horizontalTiles++;

        int centerTileX = (int) getX();
        int centerTileY = (int) getY();

        int verticalTiles = (int) Math.ceil(canvas.getHeight() / tileSize) + 1;
        if (verticalTiles % 2 == 0) verticalTiles++;

        HashMap<Rectangle, String> toolTips = new HashMap<>();

        for (int x = -horizontalTiles / 2; x <= horizontalTiles / 2; x++) {
            for (int y = -verticalTiles / 2; y <= verticalTiles / 2; y++) {
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
                Rectangle rect = new Rectangle(positionX, positionY, tileSize, tileSize);
                toolTips.put(rect, tile.getToolTip());
            }
        }
        if (GameConfig.isToolTips()) addToolTips(toolTips);
    }

    /**
     * addTooltips creates a toolTip popup for the mouse.
     * @param tooltips HashMap of all the toolTips within the canvas.
     */
    private void addToolTips(HashMap<Rectangle, String> tooltips) {
        Popup popup = new Popup();
        Label label = new Label("");
        label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        popup.getContent().add(label);
        popup.setAutoHide(true);

        canvas.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                popup.show(canvas, e.getScreenX(), e.getScreenY());
                tooltips.forEach((bounds, toolTip) -> {
                    if (bounds.contains(e.getX(), e.getY())) {
                        label.setText(toolTip);
                    }
                });
            }
        });

        canvas.setOnMouseReleased(e -> {
            popup.hide();
        });
    }

    /**
     * isCameraChanged.
     * @return boolean cameraChanged.
     */
    public boolean isCameraChanged() {
        return cameraChanged;
    }

    /**
     * setter for x.
     * @param x new x
     */
    public void setX(double x) {
        this.x = x;
        setCameraChanged(true);
    }

    /**
     * getter for x.
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * setter for y.
     * @param y new y
     */
    public void setY(double y) {
        this.y = y;
        setCameraChanged(true);
    }

    /**
     * getter for y.
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * setter for x and y.
     * @param x new x
     * @param y new y
     */
    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * setter for tileSize.
     *
     * The setter clamps the tileSize between 10 and 100.
     * @param tileSize new tileSize.
     */
    public void setTileSize(double tileSize) {
        if (tileSize < 10) tileSize = 10;
        if (tileSize > 100) tileSize = 100;
        this.tileSize = tileSize;
        setCameraChanged(true);
    }

    /**
     * getter for tileSize.
     * @return tileSize
     */
    public double getTileSize() {
        return tileSize;
    }

    /**
     * setter for level.
     * @param level new level
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * getter for level.
     * @return level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * zoomIn zooms by changing the tileSize by 2%.
     */
    private void zoomIn() {
        setTileSize(getTileSize() * 1.02);
    }

    /**
     * zoomOut zooms by changing the tileSize by 2%.
     */
    private void zoomOut() {
        setTileSize(getTileSize() * 0.98);
    }

    public void setCameraChanged(boolean cameraChanged) {
        this.cameraChanged = cameraChanged;
    }

    /**
     * receiveCommand is a method from CommandTarget interface.
     *
     * receiveCommand checks if the message received is either of the
     * zoom messages.
     * @param message String message from the Command.
     */
    @Override
    public void receiveCommand(String message) {
        if(message.equals("ZOOMIN")) zoomIn();
        else if(message.equals("ZOOMOUT")) zoomOut();
    }

    /**
     * inform is a method from TurnListener interface.
     * @param turnInfo info of the current turn.
     */
    @Override
    public void inform(TurnInfo turnInfo) {
        setCameraChanged(true);
    }
}
