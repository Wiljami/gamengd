package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Player;

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

    @Override
    public void init() {
        super.init();
        Sprite dude = new Sprite("dude.png");
        Player player = new Player(dude);
        player.setXY(4,4);

        Level level = new Level(9,9);
        addLevel(level);
        addPlayer(player);
    }
}