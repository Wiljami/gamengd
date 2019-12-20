package fi.tuni.tiko.gamengd.util.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
/**
 * JacksonMap is an object created from a JSONFile using Jackson.
 *
 * In this case the JSONFile is created using the Tiled map editor software.
 * @author Viljami Pietarila
 * @version 2019.1220
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonMap {
    /**
     * compressionLevel data.
     */
    @JsonProperty("compressionlevel")
    private int compressionLevel;
    /**
     * map height.
     */
    private int height;
    /**
     * map width.
     */
    private int width;
    /**
     * boolean wether the map is infinite.
     */
    private boolean infinite;
    /**
     * layers of the map.
     */
    private ArrayList<MapLayer> layers;
    /**
     * nextLayerId information.
     */
    @JsonProperty("nextlayerid")
    private int nextLayerId;
    /**
     * nextObjectId information.
     */
    @JsonProperty("nextobjectid")
    private int nextObjectId;
    /**
     * orientation of the map.
     */
    private String orientation;
    /**
     * renderOrder of the map.
     */
    @JsonProperty("renderorder")
    private String renderOrder;
    /**
     * Version of the Tiled map editor.
     */
    @JsonProperty("tiledversion")
    private String tiledVersion;
    /**
     * Height of the tiles.
     */
    @JsonProperty("tileheight")
    private int tileHeight;
    /**
     * Width of the tiles.
     */
    @JsonProperty("tilewidth")
    private int tileWidth;
    /**
     * ArrayList of tileSets.
     */
    @JsonProperty("tilesets")
    private ArrayList<TileSet> tileSets;
    /**
     * String type.
     */
    private String type;
    /**
     * String version of the map.
     */
    private String version;

    /**
     * JacksonMap constructor.
     */
    public JacksonMap() {
    }

    /**
     * Getter for compressionLevel
     *
     * @return value of compressionLevel
     */
    public int getCompressionLevel() {
        return compressionLevel;
    }

    /**
     * Sets compressionLevel
     *
     * @param compressionLevel new value
     */
    public void setCompressionLevel(int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    /**
     * Getter for height
     *
     * @return value of height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets height
     *
     * @param height new value
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter for width
     *
     * @return value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets width
     *
     * @param width new value
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Getter for infinite
     *
     * @return value of infinite
     */
    public boolean isInfinite() {
        return infinite;
    }

    /**
     * Sets infinite
     *
     * @param infinite new value
     */
    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    /**
     * Getter for layers
     *
     * @return value of layers
     */
    public ArrayList<MapLayer> getLayers() {
        return layers;
    }

    /**
     * Sets layers
     *
     * @param layers new value
     */
    public void setLayers(ArrayList<MapLayer> layers) {
        this.layers = layers;
    }

    /**
     * Getter for nextLayerId
     *
     * @return value of nextLayerId
     */
    public int getNextLayerId() {
        return nextLayerId;
    }

    /**
     * Sets nextLayerId
     *
     * @param nextLayerId new value
     */
    public void setNextLayerId(int nextLayerId) {
        this.nextLayerId = nextLayerId;
    }

    /**
     * Getter for nextObjectId
     *
     * @return value of nextObjectId
     */
    public int getNextObjectId() {
        return nextObjectId;
    }

    /**
     * Sets nextObjectId
     *
     * @param nextObjectId new value
     */
    public void setNextObjectId(int nextObjectId) {
        this.nextObjectId = nextObjectId;
    }

    /**
     * Getter for orientation
     *
     * @return value of orientation
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * Sets orientation
     *
     * @param orientation new value
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * Getter for renderOrder
     *
     * @return value of renderOrder
     */
    public String getRenderOrder() {
        return renderOrder;
    }

    /**
     * Sets renderOrder
     *
     * @param renderOrder new value
     */
    public void setRenderOrder(String renderOrder) {
        this.renderOrder = renderOrder;
    }

    /**
     * Getter for tiledVersion
     *
     * @return value of tiledVersion
     */
    public String getTiledVersion() {
        return tiledVersion;
    }

    /**
     * Sets tiledVersion
     *
     * @param tiledVersion new value
     */
    public void setTiledVersion(String tiledVersion) {
        this.tiledVersion = tiledVersion;
    }

    /**
     * Getter for tileHeight
     *
     * @return value of tileHeight
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Sets tileHeight
     *
     * @param tileHeight new value
     */
    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    /**
     * Getter for tileWidth
     *
     * @return value of tileWidth
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Sets tileWidth
     *
     * @param tileWidth new value
     */
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    /**
     * Getter for tileSets
     *
     * @return value of tileSets
     */
    public ArrayList<TileSet> getTileSets() {
        return tileSets;
    }

    /**
     * Sets tileSets
     *
     * @param tileSets new value
     */
    public void setTileSets(ArrayList<TileSet> tileSets) {
        this.tileSets = tileSets;
    }

    /**
     * Getter for type
     *
     * @return value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type
     *
     * @param type new value
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for version
     *
     * @return value of version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version
     *
     * @param version new value
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
