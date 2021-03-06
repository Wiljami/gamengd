package fi.tuni.tiko.gamengd.util;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.util.json.TileSet;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * ImageLoader is a class responsible for loading and storing images.
 *
 * ImageLoader is a collection of methods that are responsible for images and
 * animations. It also has a TreeMap images for storing the images that have
 * been loaded. When an image is requested to be loaded, the images TreeMap is
 * first checked if the image already exists. If it does, the existing image is
 * returned to avoid creating duplicates.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class ImageLoader {
    /**
     * TreeMap of the images that have been loaded.
     */
    private static TreeMap<String, Image> images;

    /**
     * HashMap containing different map tilesets;
     */
    private static HashMap<String, WritableImage[]> tileSets;

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
            image = loadImageFromDisk(file);
            images.put(file, image);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " " + file);
            image = images.get(GameConfig.getFailPicture());
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
        tileSets = new HashMap<>();
        images.put(GameConfig.getFailPicture(), loadImageFromDisk(GameConfig.getFailPicture()));
    }

    /**
     * loadImageFromDisk loads a file from the disk to be converted to Image.
     * @param fileName file to be loaded.
     * @return Image loaded.
     */
    private static Image loadImageFromDisk(String fileName) {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString() + "/" + GameConfig.getGraphicsFolder() + fileName;
        path = new File(path).toURI().toString();
        return new Image(path);
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

    /**
     * readTileSets reads takes a TileSet data and reads them to tileSets.
     *
     * readTileSets loads the Images set in the TileSets and then creates the
     * WritableImage regions on the Images to match the TileSet data.
     * @param data ArrayList of TileSets
     */
    public static void readTileSet(ArrayList<TileSet> data) {
        for (TileSet ts : data) {
            String key = ts.getName();
            if (!tileSets.containsKey(key)) {
                Image tiles = loadImage(ts.getImage());
                WritableImage[] writableImages = new WritableImage[ts.getColumns()];
                PixelReader reader = tiles.getPixelReader();
                if (!tileSets.containsKey(key)) {
                    for (int x = 0; x < ts.getColumns(); x++) {
                        int width = ts.getTileWidth();
                        int height = ts.getTileHeight();
                        WritableImage wi = new WritableImage(reader, x * width, 0,
                                width, height);
                        writableImages[x] = wi;
                    }
                }
                tileSets.put(key, writableImages);
            }
        }
    }

    /**
     * getTileSet gets a tileSet matching the key.
     * @param key key of the tileSet
     * @return WritableImage array containing the tileSet
     */
    public static WritableImage[] getTileSet(String key) {
        return tileSets.get(key);
    }
}
