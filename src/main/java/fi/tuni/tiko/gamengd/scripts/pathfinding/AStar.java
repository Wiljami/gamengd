package fi.tuni.tiko.gamengd.scripts.pathfinding;

import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.elements.Tile;

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

        while (openSet.size() > 0) {
            PathNode<Tile> currentNode = openSet.poll();
            if (currentNode == end) {
                constructPath(cameFrom, currentNode);
                return;
            }
            closedSet.add(currentNode);

            for (PathEdge<Tile> edgeNeighbor : currentNode.getEdges()) {
                PathNode<Tile> neighbor = edgeNeighbor.node;
                if (closedSet.contains(neighbor)) continue;

                double tentGScore = gScore.get(currentNode) + 1;
                if (openSet.contains(neighbor) && tentGScore >= gScore.get(neighbor)) continue;

                cameFrom.put(neighbor, currentNode);
                gScore.put(neighbor, tentGScore);
                fScore.put(neighbor, gScore.get(neighbor) + heuristicEstimate(neighbor, end));

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                }
            }
        }
    }

    /**
     * heuristicEstimate calculates the shortest path between two nodes.
     *
     * heuristicEstimate uses Pythagorean theorem to calculate the shortest
     * path between two points. It does not take in account any obstacles.
     * @param start start PathNode
     * @param end end PathNode
     * @return length of the hypotenuse
     */
    private double heuristicEstimate(PathNode<Tile> start, PathNode<Tile> end) {
        Tile s = start.getData();
        Tile e = end.getData();
        return Math.sqrt(
                Math.pow((s.getX() - e.getX()), 2) +
                Math.pow((s.getY() - e.getY()), 2));
    }

    private void constructPath(HashMap<PathNode<Tile>, PathNode<Tile>> cameFrom, PathNode<Tile> currentTile) {
        path = new LinkedList<>();
        path.add(currentTile.getData());
        while (cameFrom.containsKey(currentTile)) {
            currentTile = cameFrom.get(currentTile);
            path.add(currentTile.getData());
        }
        path.pollLast();
    }

    public Tile getStep() {
        if (path == null) return null;
        return path.pollLast();
    }

    @Override
    public String toString() {
        String tmp = "";
        for (int n = 0; n < path.size(); n++ ) {
            tmp += "n: " + n + " x: " + path.get(n).getX() + " y: " + path.get(n).getY() + "\n";
        }
        return tmp;
    }
}