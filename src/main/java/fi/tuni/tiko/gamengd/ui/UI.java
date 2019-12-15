package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.controller.input.InputController;
import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.scene.control.*;
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
        Label label = new Label("Hi, I am the topBar");
        VBox topBar = new VBox(new GamengdMenuBar(), label);
        return topBar;
    }

    private VBox bottomBar() {
        GameLog gameLog = new GameLog();
        uiController.addGameLog(gameLog);
        VBox topBar = new VBox(gameLog);
        return topBar;
    }

    private Pane rightColumn() {
        PlayerDisplay playerDisplay = new PlayerDisplay(player);
        uiController.addUIListener(playerDisplay);
        VBox rightColumn = new VBox(playerDisplay, new ArrowPad(inputController));
        return rightColumn;
    }

    private VBox leftColumn() {
        Label label = new Label("Hi, I am the leftColumn");
        VBox topBar = new VBox(label);
        return topBar;
    }
}
