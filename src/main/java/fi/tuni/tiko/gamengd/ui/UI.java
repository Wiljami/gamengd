package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.controller.input.InputController;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;

public class UI extends BorderPane {
    private InputController inputController;

    public UI(GameView gameView, InputController inputController) {
        this.inputController = inputController;
        setCenter(gameView);
        setTop(topBar());
        setBottom(bottomBar());
        setRight(rightColumn());
        setLeft(leftColumn());
    }


    private VBox topBar() {
        MenuBar menuBar = new MenuBar();
        Label label = new Label("Hi, I am the topBar");
        VBox topBar = new VBox(menuBar, label);
        return topBar;
    }

    private VBox bottomBar() {
        Label label = new Label("Hi, I am the bottomBar");
        VBox topBar = new VBox(label);
        return topBar;
    }

    private Pane rightColumn() {
        HBox rightColumn = new HBox(new ArrowPad(inputController));
        return rightColumn;
    }

    private VBox leftColumn() {
        Label label = new Label("Hi, I am the leftColumn");
        VBox topBar = new VBox(label);
        return topBar;
    }

}
