package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.controller.input.InputController;
import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * UI is the collection of all the displayed UI elements.
 *
 * It extends BorderPane and sets each of BorderPane's areas to contain
 * ui elements.
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class UI extends BorderPane {
    /**
     * Reference to inputController
     */
    private InputController inputController;
    /**
     * Reference to uiController
     */
    private UIController uiController;
    /**
     * Reference to gameCore
     */
    private GameCore gameCore;
    /**
     * Reference to player
     */
    private Player player;

    /**
     * UI constructor.
     *
     * UI constructor sets up the different UI elements within itself.
     * @param gameCore Reference to the gameCore
     */
    public UI(GameCore gameCore) {
        this.inputController = gameCore.getInputController();
        this.uiController = gameCore.getUiController();
        this.gameCore = gameCore;
        this.player = gameCore.getCurrentLevel().getPlayer();
        setCenter(gameCore.getGameView());
        setTop(topBar());
        setBottom(bottomBar());
        setRight(rightColumn());
        setLeft(leftColumn());
    }

    /**
     * topBar is the ui element set to the top of the UI.
     *
     * topBar holds the GameMenuBar.
     * @return topBar Pane
     */
    private Pane topBar() {
        HBox topBar = new HBox(new GameMenuBar(player, gameCore));
        return topBar;
    }

    /**
     * bottomBar is the ui element set to the bottom of the UI.
     *
     * bottomBar holds the gameLog.
     * @return bottomBar Pane
     */
    private Pane bottomBar() {
        GameLog gameLog = new GameLog();
        uiController.addGameLog(gameLog);
        HBox bottomBar = new HBox(gameLog);
        return bottomBar;
    }

    /**
     * rightColumn is the ui element set to the right side of the UI.
     *
     * rightColumn holds the playerDisplay and the arrowPad.
     * @return rightColumn Pane
     */
    private Pane rightColumn() {
        PlayerDisplay playerDisplay = new PlayerDisplay(player);
        uiController.addUIListener(playerDisplay);
        VBox rightColumn = new VBox(playerDisplay);
        if (GameConfig.isMouseControl()) {
            ArrowPad arrowPad = new ArrowPad(inputController);
            rightColumn.getChildren().add(arrowPad);
        }
        return rightColumn;
    }

    /**
     * leftColumn is the ui element set to the left of the UI.
     *
     * leftColumn holds nothing.
     * @return leftColumn Pane
     */
    private Pane leftColumn() {
        VBox topBar = new VBox();
        return topBar;
    }
}
