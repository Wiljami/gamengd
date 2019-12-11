package fi.tuni.tiko.gamengd.util.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JacksonMap {
    @JsonProperty("compressionlevel")
    private int compressionLevel;
    private int height;
    private int width;
    private boolean infinite;
    private ArrayList<MapLayer> layers;
    @JsonProperty("nextlayerid")
    private int nextLayerId;
    @JsonProperty("nextobjectid")
    private int nextObjectId;
    private String orientation;
    @JsonProperty("renderorder")
    private String renderOrder;
    @JsonProperty("tiledversion")
    private String tiledVersion;
    @JsonProperty("tileheight")
    private int tileHeight;
    @JsonProperty("tilewidth")
    private int tileWidth;
    @JsonProperty("tilesets")
    private ArrayList<TileSet> tileSets;
    private String type;
    private String version;

    public JacksonMap() {
    }

    public int getCompressionLevel() {
        return compressionLevel;
    }

    public void setCompressionLevel(int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public ArrayList<MapLayer> getLayers() {
        return layers;
    }

    public void setLayers(ArrayList<MapLayer> layers) {
        this.layers = layers;
    }

    public int getNextLayerId() {
        return nextLayerId;
    }

    public void setNextLayerId(int nextLayerId) {
        this.nextLayerId = nextLayerId;
    }

    public int getNextObjectId() {
        return nextObjectId;
    }

    public void setNextObjectId(int nextObjectId) {
        this.nextObjectId = nextObjectId;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getRenderOrder() {
        return renderOrder;
    }

    public void setRenderOrder(String renderOrder) {
        this.renderOrder = renderOrder;
    }

    public String getTiledVersion() {
        return tiledVersion;
    }

    public void setTiledVersion(String tiledVersion) {
        this.tiledVersion = tiledVersion;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public ArrayList<TileSet> getTileSets() {
        return tileSets;
    }

    public void setTileSets(ArrayList<TileSet> tileSets) {
        this.tileSets = tileSets;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

