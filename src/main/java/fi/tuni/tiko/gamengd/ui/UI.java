package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.controller.input.InputController;
import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;

public class UI extends BorderPane {
    private InputController inputController;
    private UIController uiController;
    private Player player;

    public UI(GameCore gameCore) {
        this.inputController = gameCore.getInputController();
        this.uiController = gameCore.getUiController();
        this.player = gameCore.getLevel().getPlayer();
        setCenter(gameCore.getGameView());
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
        VBox rightColumn = new VBox(new PlayerDisplay(uiController, player), new ArrowPad(inputController));
        return rightColumn;
    }

    private VBox leftColumn() {
        Label label = new Label("Hi, I am the leftColumn");
        VBox topBar = new VBox(label);
        return topBar;
    }

}
