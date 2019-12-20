package fi.tuni.tiko.gamengd.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

/**
 * GameView is the displayed game elements in the middle of the UI.
 *
 * GameView holds a canvas and sets the canvas size according to the size
 * of the window. Sets the canvas to automatically resize itself if the size
 * changes.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class GameView extends Pane {
    /**
     * the Canvas within the GameView
     */
    private Canvas canvas;

    /**
     * GameView constructor.
     */
    public GameView() {
        canvas = new Canvas();
        getChildren().add(canvas);

        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
    }

    /**
     * getter for canvas.
     * @return canvas
     */
    public Canvas getCanvas() {
        return canvas;
    }
}
