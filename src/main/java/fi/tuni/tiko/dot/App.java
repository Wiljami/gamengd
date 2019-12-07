package fi.tuni.tiko.dot;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.entity.Monster;

/**
 * App is a helper class for development and testing.
 *
 * App is a pseudogame that is going to use the game engine being created.
 */

public class App extends GameCore {
    public static void main(String[] args) {
        System.out.println("Author: Viljami Pietarila");
        //Set windowTitle
        setWindowTitle("Dungeons of Tikonyr");
        //Set resolution for the game
        setResolution(800,600);
        launch();
    }

    @Override
    public void init() {
        super.init();

        Level level = new Level("level.json");

        addLevel(level);
    }
}