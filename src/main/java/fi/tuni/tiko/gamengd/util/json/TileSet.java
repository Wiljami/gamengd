package fi.tuni.tiko.gamengd.util.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * TileSet is json data read to an object.
 *
 * MonsterSpawn contains information for TileSet within a level. Its
 * data is read from a json file using Jackson. It contains all the Tiled
 * Map information from the json.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */

public class TileSet {
    /**
     * columns within the json file.
     */
    private int columns;
    /**
     * firstgid within the json file.
     */
    private int firstgid;
    /**
     * image String within the json file.
     */
    private String image;
    /**
     * height of the image within the json file.
     */
    @JsonProperty("imageheight")
    private int imageHeight;
    /**
     * width of the image within the json file.
     */
    @JsonProperty("imagewidth")
    private int imageWidth;
    /**
     * margin within the json file.
     */
    private int margin;
    /**
     * name within the json file.
     */
    private String name;
    /**
     * spacing within the json file.
     */
    private int spacing;
    /**
     * tileCount within the json file.
     */
    @JsonProperty("tilecount")
    private int tileCount;
    /**
     * tileHeight within the json file.
     */
    @JsonProperty("tileheight")
    private int tileHeight;
    /**
     * tileWidth within the json file.
     */
    @JsonProperty("tilewidth")
    private int tileWidth;
    /**
     * Constructor for TileSet.
     */
    public TileSet() {
    }

    /**
     * getter for columns.
     * @return columns
     */
    public int getColumns() {
        return columns;
    }

    /**
     * setter for columns.
     * @param columns new columns
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * getter for firstgid.
     * @return firstgid
     */
    public int getFirstgid() {
        return firstgid;
    }

    /**
     * setter for firstgid.
     * @param firstgid new firstgid
     */
    public void setFirstgid(int firstgid) {
        this.firstgid = firstgid;
    }

    /**
     * getter for image.
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * setter for image.
     * @param image new image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * getter for imageHeight.
     * @return imageHeight
     */
    public int getImageHeight() {
        return imageHeight;
    }

    /**
     * setter for imageHeight.
     * @param imageHeight new imageHeight
     */
    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * getter for imageWidth.
     * @return imageWidth
     */
    public int getImageWidth() {
        return imageWidth;
    }

    /**
     * setter for imageWidth.
     * @param imageWidth new imageWidth
     */
    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * getter for margin.
     * @return margin
     */
    public int getMargin() {
        return margin;
    }

    /**
     * setter for margin.
     * @param margin new margin
     */
    public void setMargin(int margin) {
        this.margin = margin;
    }

    /**
     * getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter for name.
     * @param name new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter for spacing.
     * @return spacing
     */
    public int getSpacing() {
        return spacing;
    }

    /**
     * setter for spacing.
     * @param spacing new spacing
     */
    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    /**
     * getter for tileCount.
     * @return tileCount
     */
    public int getTileCount() {
        return tileCount;
    }

    /**
     * setter for tileCount.
     * @param tileCount new tileCount
     */
    public void setTileCount(int tileCount) {
        this.tileCount = tileCount;
    }

    /**
     * getter for tileHeight.
     * @return tileHeight
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * setter for tileHeight.
     * @param tileHeight new tileHeight
     */
    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    /**
     * getter tileWidth.
     * @return tileWidth
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * setter tileWidth.
     * @param tileWidth new tileWidth
     */
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }
}
