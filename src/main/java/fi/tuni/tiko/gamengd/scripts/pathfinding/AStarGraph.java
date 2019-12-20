package fi.tuni.tiko.gamengd.scripts.pathfinding;

import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Tile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * AStarGraph is the mapping of a game Level for pathfinding.
 *
 * AStarGraph takes a level and goes through its Tiles to see each Tile's
 * connection to other Tiles. It then constructs a comprehensive map of the
 * connections.
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class AStarGraph {
    /**
     * nodes is the mapping of the connections between the Tiles.
     */
    private HashMap<Tile, PathNode<Tile>> nodes;

    /**
     * AStarGraph constructor
     *
     * It iterates through all the Tiles of the level and maps their
     * connections to nodes HashMap.
     * @param level level to be mapped
     */
    public AStarGraph(Level level) {
        nodes = new HashMap<>();

        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                Tile tile = level.getTileAt(x, y);
                if (tile.isPassable()) {
                    PathNode<Tile> node = new PathNode<>();
                    node.setData(tile);
                    nodes.put(tile, node);
                }
            }
        }

        for (Tile tile : nodes.keySet()) {
            PathNode<Tile> node = nodes.get(tile);
            ArrayList<PathEdge<Tile>> edges = new ArrayList<>();

            Tile[] neighbours = tile.getNeighbours();
            for (Tile neighbour : neighbours) {
                if (neighbour != null && neighbour.isPassable()) {
                    PathEdge<Tile> e = new PathEdge<>();
                    e.node = nodes.get(neighbour);

                    edges.add(e);
                }
            }

            node.setEdges(edges);
        }
    }

    /**
     * getter for nodes.
     * @return nodes
     */
    public HashMap<Tile, PathNode<Tile>> getNodes() {
        return nodes;
    }
}
