package fi.tuni.tiko.gamengd.util.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import fi.tuni.tiko.gamengd.entity.Monster;
import fi.tuni.tiko.gamengd.util.Util;

import java.io.File;

public class JSONLoader {
    private static final String MONSTERSFOLDER = "monsters/";
    private static final String MAPSFOLDER = "maps/";

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

    public static Monster loadMonster(String fileName) {
        File file = Util.loadFile(MONSTERSFOLDER + fileName);

        return loadMonster(file);
    }

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
