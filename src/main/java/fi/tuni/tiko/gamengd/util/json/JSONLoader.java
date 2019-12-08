package fi.tuni.tiko.gamengd.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.tuni.tiko.gamengd.entity.Monster;
import fi.tuni.tiko.gamengd.util.Util;

import java.io.File;

/**
 * JSONLoader class uses Jackson to load and parse json files.
 *
 * JSONLoader uses Jackson to sort out json files to java objects.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class JSONLoader {
    /**
     * Folder for monster jsons.
     */
    private static final String MONSTERSFOLDER = "monsters/";
    /**
     * Folder for maps.
     */
    private static final String MAPSFOLDER = "maps/";

    /**
     * loadLevel loads a level from json file and creates an object of it.
     *
     * loadLevel loads a file and creates a JacksonLevel data object from it.
     * @param fileName name of the file to load
     * @return JacksonLevel object
     */
    public static JacksonLevel loadLevel(String fileName) {
        JacksonLevel jl = new JacksonLevel();
        ObjectMapper objectMapper = new ObjectMapper();

        File file = Util.loadFile(fileName);

        try {
            jl = objectMapper.readValue(file, JacksonLevel.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return jl;
    }

    /**
     * loadConfig loads a config from json file and creates an object of it.
     *
     * loadConfig loads a file and creates a JacksonConfig data object from it.
     * @param fileName name of the file to load
     * @return JacksonConfig object
     */
    public static JacksonConfig loadConfig(String fileName) {
        JacksonConfig config = new JacksonConfig();
        ObjectMapper objectMapper = new ObjectMapper();

        File file = Util.loadFile(fileName);

        try {
            config = objectMapper.readValue(file, JacksonConfig.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return config;
    }

    /**
     * loadMonster creates a monster out of json file.
     *
     * loadMonster loads a file and creates a JacksonMonster object out of it.
     * It then creates a Monster object out of the JacksonMonster and returns
     * the newly created Monster object.
     * @param file File object of the file to load.
     * @return Monster object.
     */
    public static Monster loadMonster(File file) {
        JacksonMonster jm = new JacksonMonster();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            jm = objectMapper.readValue(file, JacksonMonster.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new Monster(jm);
    }

    /**
     * loadMonster creates a monster out of json file using filename.
     *
     * loadMonster loads a file and creates a JacksonMonster object out of it.
     * It then creates a Monster object out of the JacksonMonster and returns
     * the newly created Monster object.
     * @param fileName name of the json file.
     * @return Monster object
     */
    public static Monster loadMonster(String fileName) {
        File file = Util.loadFile(MONSTERSFOLDER + fileName);

        return loadMonster(file);
    }

    /**
     * loadMap loads a map from json file and creates an object of it.
     *
     * loadMap loads a file and creates a JacksonMap data object from it.
     * @param fileName name of the file to load
     * @return JacksonMap object
     */
    public static JacksonMap loadMap(String fileName) {
        File file = Util.loadFile(MAPSFOLDER + fileName);

        ObjectMapper objectMapper = new ObjectMapper();
        JacksonMap map = new JacksonMap();
        try {
            map = objectMapper.readValue(file, JacksonMap.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;
    }
}
