package fi.tuni.tiko.gamengd.scripts.pathfinding;

import fi.tuni.tiko.gamengd.Level;
import fi.tuni.tiko.gamengd.Tile;

public class AStarGraph {

    public AStarGraph(Level level) {
        System.out.println("AStarGraph::AStarGraph");

        for (int x = 0; x < level.getWidth(); x++) {
            for (int y = 0; y < level.getHeight(); y++) {
                Tile t = level.getTileAt(x, y);
                if (t.isPassable()) {

                }
            }
        }
    }
}


