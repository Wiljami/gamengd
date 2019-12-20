package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.entity.Player;
import javafx.application.Platform;
import javafx.scene.control.*;

/**
 * GameMenuBar creates the MenuBar UI element.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class GameMenuBar extends MenuBar {
    /**
     * Reference to the Player object.
     */
    private Player player;
    /**
     * Reference to the GameCore.
     */
    private GameCore gameCore;

    /**
     * GameMenuBar constructor.
     * @param player reference to the player
     * @param gameCore reference to the gameCore
     */
    public GameMenuBar(Player player, GameCore gameCore) {
        this.player = player;
        this.gameCore = gameCore;
        getMenus().addAll(menuFile(), gameMenu(), menuAbout());
    }

    /**
     * menuFile creates the menu section of the bar.
     *
     * menuFile adds to each MenuItem their functionality as well.
     * @return created Menu
     */
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

    /**
     * gameMenu creates the game Menu within the bar.
     *
     * Adds functionality to the MenuItems, including renaming the character.
     * @return created Menu
     */
    private Menu gameMenu() {
        Menu gameMenu = new Menu("Game");
        MenuItem rename = new MenuItem("Rename Player");
        rename.setOnAction(actionEvent -> {
            TextInputDialog renameItem = renameCharacter();
            renameItem.showAndWait();
            String result = renameItem.getResult();
            if (result != null && !result.equals("")) {
                player.setName(renameItem.getResult());
            }
        });
        gameMenu.getItems().addAll(rename);
        return gameMenu;
    }

    /**
     * renameCharacter is a dialog popUp for renaming the character.
     * @return TextInputDialog
     */
    private TextInputDialog renameCharacter() {
        TextInputDialog dialog = new TextInputDialog(player.getName());
        dialog.setHeaderText("Rename your character");
        return dialog;
    }

    /**
     * menuAbout is the about menu within the bar.
     * @return created Menu
     */
    private Menu menuAbout() {
        Menu menuAbout =  new Menu("About");
        MenuItem itemAbout = new MenuItem("About Gamengd");
        itemAbout.setOnAction(actionEvent -> aboutDialog().showAndWait());
        menuAbout.getItems().addAll(itemAbout);
        return menuAbout;
    }

    /**
     * aboutDialog is the about dialog for about Menu.
     * @return created about Alert
     */
    private Alert aboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gamengd Game Engine");
        alert.setHeaderText("Gamengd");
        alert.setContentText("Gamengd");
        return alert;
    }
}
