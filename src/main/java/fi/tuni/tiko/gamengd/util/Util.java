package fi.tuni.tiko.gamengd.util;

import java.io.File;
import java.net.URL;

/**
 * Util class is colleciton of helpful methods.
 *
 * Util class holds collection of helpful methods for running the game.
 * The methods try to make the game engine run painlessly and try to make
 * errors obvious without crashing the game.
 *
 * @author Viljami Pietarila
 * @version 2019.1123
 */
public class Util {

    public static File[] walkFolder(String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(folder);
        String path = url.getPath();
        return new File(path).listFiles();
    }

    public static File loadFile(String fileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

}