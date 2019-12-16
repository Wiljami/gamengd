package fi.tuni.tiko.gamengd.scripts.pathfinding;

import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class AStarGraph {

    private HashMap<Tile, PathNode<Tile>> nodes;

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

    public HashMap<Tile, PathNode<Tile>> getNodes() {
        return nodes;
    }
}