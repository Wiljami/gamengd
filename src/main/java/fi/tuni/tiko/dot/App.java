package fi.tuni.tiko.dot;

import fi.tuni.tiko.gamengd.GameCore;
import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Sprite;
import fi.tuni.tiko.gamengd.entity.Monster;
import fi.tuni.tiko.gamengd.entity.Player;
import fi.tuni.tiko.gamengd.util.Util;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

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

        Level level = new Level("map.json");

        addLevel(level);
        Sprite dude = new Sprite("dude.png");
        Player player = new Player(dude);
        player.setXY(12,12);

        addPlayer(player);

        Monster monster = Util.loadMonster("monster01.json");

        monster.setXY(45,60);
        addMonster(monster);

        String filename = "monsters/";
    }
}