package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.application.Platform;
import javafx.scene.control.*;

public class GameMenuBar extends MenuBar {
    private Player player;
    private GameCore gameCore;
    public GameMenuBar(Player player, GameCore gameCore) {
        this.player = player;
        this.gameCore = gameCore;
        getMenus().addAll(menuFile(), gameMenu(), menuAbout());
    }

    private Menu menuFile() {
        Menu menuFile = new Menu("File");

        MenuItem separator = new SeparatorMenuItem();

        MenuItem saveGame = new MenuItem("Save game");
        saveGame.setOnAction(actionEvent -> gameCore.saveGame());

        MenuItem loadGame = new MenuItem("Load game");
        loadGame.setOnAction(actionEvent -> gameCore.loadGame());

        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnAction(actionEvent -> Platform.exit());

        menuFile.getItems().addAll(saveGame, loadGame, separator, itemExit);
        return menuFile;
    }

    private Menu gameMenu() {
        Menu gameMenu = new Menu("Game");
        MenuItem rename = new MenuItem("Rename Player");
        rename.setOnAction(actionEvent -> {
            TextInputDialog renameItem = renameCharacter();
            renameItem.showAndWait();
            player.setName(renameItem.getResult());
        });
        gameMenu.getItems().addAll(rename);
        return gameMenu;
    }

    private TextInputDialog renameCharacter() {
        TextInputDialog dialog = new TextInputDialog(player.getName());
        dialog.setHeaderText("Rename your character");
        return dialog;
    }

    private Menu menuAbout() {
        Menu menuAbout =  new Menu("About");
        MenuItem itemAbout = new MenuItem("About Gamengd");
        itemAbout.setOnAction(actionEvent -> aboutDialog().showAndWait());
        menuAbout.getItems().addAll(itemAbout);
        return menuAbout;
    }

    private Alert aboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gamengd Game Engine");
        alert.setHeaderText("Gamengd");
        alert.setContentText("Gamengd");
        return alert;
    }
}
