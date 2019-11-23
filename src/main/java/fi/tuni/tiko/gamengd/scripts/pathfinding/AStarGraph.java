package fi.tuni.tiko.gamengd.scripts.pathfinding;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Tile;

import java.util.ArrayList;
import java.util.HashMap;

public class AStarGraph {

    private HashMap<Tile, PathNode<Tile>> nodes;

    public AStarGraph(Level level) {
        System.out.println("AStarGraph::AStarGraph");
        nodes = new HashMap<>();

        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                Tile tile = level.getTileAt(x, y);
                if (tile.isPassable()) {
                    PathNode<Tile> node = new PathNode<>();
                    node.data = tile;
                    nodes.put(tile, node);
                }
            }
        }

        System.out.println("AStarGraph: Created " + nodes.size() + " nodes");

        int edgeCounter = 0;

        for (Tile tile : nodes.keySet()) {
            PathNode<Tile> node = nodes.get(tile);
            ArrayList<PathEdge<Tile>> edges = new ArrayList<>();

            Tile[] neighbours = tile.getNeighbours();
            for (int i = 0; i < neighbours.length; i++) {
                if (neighbours[i] != null && neighbours[i].isPassable()) {
                    PathEdge<Tile> e = new PathEdge<>();
                    e.node = nodes.get(neighbours[i]);

                    edges.add(e);

                    edgeCounter++;
                }
            }
        }

        System.out.println("AstarGraph: Edgecount " + edgeCounter);
    }
}