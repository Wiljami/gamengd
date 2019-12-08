package fi.tuni.tiko.gamengd.util.json;

/**
 * MapLayer is json data read to an object.
 *
 * MapLayer contains information for map within a level. Its
 * data is read from a json file using Jackson. It contains all the Tiled
 * Map information from the json. See Tiled documentation for more info about
 * these variables.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class MapLayer {
    /**
     * int array containing the map data.
     */
    private int[] data;
    /**
     * height of the layer.
     */
    private int height;
    /**
     * width of the layer.
     */
    private int width;
    /**
     * id of the layer.
     */
    private int id;
    /**
     * name of the layer.
     */
    private String name;
    /**
     * type of the layer.
     */
    private String type;
    /**
     * boolean for the visibility of the layer.
     */
    private boolean visible;
    /**
     * x-coordinate of the layer.
     */
    private int x;
    /**
     * y-coordinate of the layer.
     */
    private int y;
    /**
     * opacity of the layer.
     */
    private int opacity;
    /**
     * MapLayer constructor.
     */
    public MapLayer() {
    }

    /**
     * setter for data.
     * @param data new int[] data
     */
    public void setData(int[] data) {
        this.data = data;
    }

    /**
     * getter for data.
     * @return data
     */
    public int[] getData() {
        return data;
    }

    /**
     * getter for height.
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * setter for height.
     * @param height new height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * getter for width.
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * setter for width.
     * @param width new width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getter for id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id.
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
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
     * getter for visible.
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * setter for visible.
     * @param visible new visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * getter for x.
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * setter for x.
     * @param x new x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * getter for y.
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * setter for y.
     * @param y new y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * getter for opacity.
     * @return opacity
     */
    public int getOpacity() {
        return opacity;
    }

    /**
     * setter for opacity.
     * @param opacity new opacity
     */
    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    /**
     * getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * setter for type.
     * @param type new type
     */
    public void setType(String type) {
        this.type = type;
    }
}
