package fi.tuni.tiko.gamengd.ui;

import javafx.application.Platform;
import javafx.scene.control.*;

public class GameMenuBar extends MenuBar {
    public GameMenuBar() {
        getMenus().addAll(menuFile(), gameMenu(), menuAbout());
    }

    private Menu menuFile() {
        Menu menuFile = new javafx.scene.control.Menu("File");

        MenuItem separator = new SeparatorMenuItem();

        MenuItem itemExit = new javafx.scene.control.MenuItem("Exit");
        itemExit.setOnAction(actionEvent -> Platform.exit());

        menuFile.getItems().addAll(separator, itemExit);
        return menuFile;
    }

    private Menu gameMenu() {
        Menu gameMenu = new Menu("Game");
        MenuItem rename = new MenuItem("Rename Player");
        rename.setOnAction(actionEvent -> new TextInputDialog().showAndWait());
        gameMenu.getItems().addAll(rename);
        return gameMenu;
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
