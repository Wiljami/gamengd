package fi.tuni.tiko.gamengd.util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Util class is colleciton of helpful methods.
 *
 * Util class holds collection of helpful methods for running the game.
 * The methods try to make the game engine run painlessly and try to make
 * errors obvious without crashing the game.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class Util {
    /**
     * walkFolder examines a folder and lists the files in it.
     * @param folder folder to examine
     * @return array of Files in the folder
     */
    public static File[] walkFolder(String folder) {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString() + "/" + folder;
        return new File(path).listFiles();
    }

    /**
     * loadFile loads a File by the name fileName
     * @param fileName name of the file in the system
     * @return loaded File
     */
    public static File loadFile(String fileName) {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString() + "/" + fileName;
        return new File(path);
    }

}