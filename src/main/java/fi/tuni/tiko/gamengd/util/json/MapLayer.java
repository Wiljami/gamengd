package fi.tuni.tiko.gamengd.util.json;

/**
 * MapLayer is json data read to an object.
 *
 * MapLayer contains information for map within a level. Its
 * data is read from a json file using Jackson. It contains all the Tiled
 * Map information from the json.
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

    public void setData(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getOpacity() {
        return opacity;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
