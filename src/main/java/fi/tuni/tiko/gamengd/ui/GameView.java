package fi.tuni.tiko.gamengd.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;

public class GameView extends Pane {
    private Canvas canvas;
    public GameView() {
        canvas = new Canvas();
        getChildren().add(canvas);

        canvas.widthProperty().bind(this.widthProperty());
        canvas.heightProperty().bind(this.heightProperty());
    }

    public Canvas getCanvas() {
        return canvas;
    }
}