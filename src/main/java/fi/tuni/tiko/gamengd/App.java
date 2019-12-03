package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.entity.Furniture;
import fi.tuni.tiko.gamengd.entity.Monster;
import fi.tuni.tiko.gamengd.entity.Player;
import fi.tuni.tiko.gamengd.scripts.Util;
import org.json.simple.JSONObject;

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

        JSONObject levelObject = Util.readJSON("resources\\map2.json");
        Level level = new Level(levelObject);

/**
        Level level = new Level(100,100);
        level.createRoom(0,0, 9,9);
        level.createRoom(3,8, 3, 10);
        level.createRoom(5, 15, 10, 3);
        level.createRoom(14, 12, 9, 9);
        level.getTileAt(4,8).removeWall();
        level.getTileAt(5,16).removeWall();
        level.getTileAt(14,16).removeWall();
*/
        addLevel(level);
        Sprite dude = new Sprite("dude.png");
        Player player = new Player(dude, level);
        player.setXY(3,3);

        addPlayer(player);
/**
        for (int x = 0; x < 6; x++) {
            level.getTileAt(x,0).addFurniture(new Furniture(new AnimatedSprite("fire.png", 10, 6, 5), 0 ,0));
        }

        Sprite dude = new Sprite("dude.png");
        Player player = new Player(dude, level);
        player.setXY(3,3);

        addPlayer(player);

        Monster monster = new Monster(level);
        monster.setXY(15,15);
        addMonster(monster);*/
    }
}