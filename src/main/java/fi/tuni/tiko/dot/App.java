package fi.tuni.tiko.dot;

import fi.tuni.tiko.gamengd.GameCore;

/**
 * App is a helper class for development and testing.
 *
 * App is a pseudogame that is going to use the game engine being created.
 */

public class App extends GameCore {
    public static void main(String[] args) {
        System.out.println("Author: Viljami Pietarila");
        launch();
    }

    @Override
    public void init() {
        super.init();
    }
}