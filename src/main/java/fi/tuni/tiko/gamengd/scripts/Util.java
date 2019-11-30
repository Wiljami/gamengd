package fi.tuni.tiko.gamengd.scripts;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.util.TreeMap;

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

    /**
     * file name of the fail picture that is used when picture is not found.
     */
    private static final String failPicture = "fail.png";

    /**
     * TreeMap of the images that have been loaded.
     */
    private static TreeMap<String, Image> images;

    /**
     * loadImage method creates an Image from a file.
     *
     * loadImage attempts to open an image file and create an Image object
     * out of it. If it fails to load the file, it will create an Image object
     * using an obvious error image. It will store the Images in the images
     * TreeMap. This is to make sure that duplicate images are not loaded
     * into memory.
     * @param file filename of the picture
     * @return Image created of the picture
     */
    public static Image loadImage(String file) {
        if (images == null) initiateImages();
        if (images.containsKey(file)) return images.get(file);
        Image image;
        try {
            image = new Image(file);
            images.put(file, image);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " " + file);
            image = images.get(failPicture);
        }
        return image;
    }

    /**
     * loadImage initiation method.
     *
     * InitiateImages initiates the images TreeMap and loads the failPicture
     * Image in and adds it to this TreeMap.
     */
    private static void initiateImages() {
        images = new TreeMap<>();
        images.put(failPicture, new Image(failPicture));
    }

    /**
     * createAnimatedArray created array for animation.
     *
     * Creates an array of WriteableImages from an Image.
     * Checks if any of the frames have already been loaded into the TreeSet.
     * If there is already one, it does not copy it, just uses exiting one.
     *
     * @param image Image of the frames
     * @param framesX No of frames horizontally in the Image
     * @param framesY No of frames vertically in the Image
     * @return Array of WritableImages
     */
    public static WritableImage[] createAnimatedArray(Image image, int framesX, int framesY) {
        WritableImage[] frames = new WritableImage[framesX * framesY];
        PixelReader reader = image.getPixelReader();
        int i = 0;
        int width = (int) (image.getWidth()/framesX);
        int height = (int) (image.getHeight()/framesY);

        for (int x = 0; x < framesX; x++) {
            for (int y = 0; y < framesY; y++) {
                WritableImage writableImage;
                String key = image.toString() + "-x:" + x + "-y:" + y;
                if (images.containsKey(key)) {
                    writableImage = (WritableImage) images.get(key);
                } else {
                    writableImage = new WritableImage(reader, x * width, y * height, width, height);
                    images.put(key, writableImage);
                }
                frames[i] = writableImage;
                i++;
            }
        }
        return frames;
    }
}