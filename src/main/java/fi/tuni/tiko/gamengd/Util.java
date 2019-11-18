package fi.tuni.tiko.gamengd;

import javafx.scene.image.Image;

/**
 * Util class is colleciton of helpful methods.
 *
 * Util class holds collection of helpful methods for running the game.
 * The methods try to make the game engine run painlessly and try to make
 * errors obvious without crashing the game.
 *
 * @author Viljami Pietarila
 * @version 2019.1118
 */
public class Util {
    /**
     * loadImage method creates an Image from a file.
     *
     * loadImage attempts to open an image file and create an Image object
     * out of it. If it fails to load the file, it will create an Image object
     * using an obvious error image.
     * @param file filename of the picture
     * @return Image created of the picture
     */
    public static Image loadImage(String file) {
        Image image;
        try {
            image = new Image(file);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " " + file);
            image = new Image("fail.png");
        }
        return image;
    }
}