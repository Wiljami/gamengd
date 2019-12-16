package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.Config;
import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.controller.input.InputController;
import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UI extends BorderPane {
    private InputController inputController;
    private UIController uiController;
    private Player player;

    public UI(GameCore gameCore) {
        this.inputController = gameCore.getInputController();
        this.uiController = gameCore.getUiController();
        this.player = gameCore.getCurrentLevel().getPlayer();
        setCenter(gameCore.getGameView());
        setTop(topBar());
        setBottom(bottomBar());
        setRight(rightColumn());
        setLeft(leftColumn());
    }


    private VBox topBar() {
        VBox topBar = new VBox(new GameMenuBar(player));
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
        VBox rightColumn = new VBox(playerDisplay);
        if (Config.mouseControl) {
            ArrowPad arrowPad = new ArrowPad(inputController);
            rightColumn.getChildren().add(arrowPad);
        }
        return rightColumn;
    }

    private VBox leftColumn() {
        VBox topBar = new VBox();
        return topBar;
    }
}
