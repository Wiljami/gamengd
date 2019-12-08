package fi.tuni.tiko.dot;

import fi.tuni.tiko.gamengd.GameCore;

/**
 * App is a helper class for development and testing.
 *
 * App is a pseudogame that is going to use the game engine being created.
 */

public class App extends GameCore {
    /**
     * main method.
     * @param args console arguements.
     */
    public static void main(String[] args) {
        System.out.println("Author: Viljami Pietarila");
        launch();
    }
}
