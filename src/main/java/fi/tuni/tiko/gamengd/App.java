package fi.tuni.tiko.gamengd;

/**
 * App is a helper class for development and testing.
 *
 * App is a pseudogame that is going to use the game engine being created.
 */

public class App extends GameCore {
    public static void main(String[] args) {
        //Set windowTitle
        setWindowTitle("Dungeons of Tikonyr");
        //Set resolution for the game
        setResolution(800,600);
        launch();
    }
}