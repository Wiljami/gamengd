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

    @Override
    public void init() {
        super.init();

        Sprite dude = new Sprite("dude.png");
        dude.setVelocityX(10);
        dude.setVelocityY(10);

        Sprite dude2 = new Sprite("fasdail.png", 10, 10);

        sc.addEntitySprite(dude);

        Player player = new Player(dude2);

        Level level = new Level(10,10);
        addLevel(level);


        addPlayer(player);
    }
}