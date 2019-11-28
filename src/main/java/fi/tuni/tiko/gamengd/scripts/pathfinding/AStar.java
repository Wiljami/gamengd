package fi.tuni.tiko.gamengd.scripts.pathfinding;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Tile;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * A* search algorithm
 *
 * https://en.wikipedia.org/wiki/A*_search_algorithm
 */

public class AStar {
    LinkedList<Tile> path;

    public AStar(Level level, Tile startTile, Tile endTile) {
        if (level.getaStarGraph() == null) {
            level.setaStarGraph(new AStarGraph(level));
        }

        HashMap<Tile, PathNode<Tile>> nodes = level.getaStarGraph().getNodes();

        if (!nodes.containsKey(startTile)) {
            System.out.println("AStar: Starting tile not among nodes.");
            return;
        }

        if (!nodes.containsKey(endTile)) {
            System.out.println("AStar: Ending tile not among nodes.");
            return;
        }

        PathNode<Tile> start = nodes.get(startTile);
        PathNode<Tile> end = nodes.get(endTile);

        PriorityQueue<PathNode<Tile>> closedSet = new PriorityQueue<>();

        PriorityQueue<PathNode<Tile>> openSet = new PriorityQueue<>();

        openSet.add(start);

        HashMap<PathNode<Tile>, PathNode<Tile>> cameFrom = new HashMap<>();

        HashMap<PathNode<Tile>, Double> fScore = new HashMap<>();
        HashMap<PathNode<Tile>, Double> gScore = new HashMap<>();
        for (PathNode<Tile> node : nodes.values()) {
            gScore.put(node, Double.MAX_VALUE);
            fScore.put(node, Double.MAX_VALUE);
        }
        gScore.put(start, 0.0);
        fScore.put(start, heuristicEstimate(start, end));

    }

    private double heuristicEstimate(PathNode<Tile> start, PathNode<Tile> end) {
        Tile s = start.getData();
        Tile e = end.getData();
        return Math.sqrt(
                Math.pow((s.getX() - e.getX()), 2) +
                Math.pow((s.getY() - e.getY()), 2));
    }
}