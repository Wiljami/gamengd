package fi.tuni.tiko.gamengd.scripts.pathfinding;

/**
 * PathEdge is an edge on an object leading to another object for pathfinding.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 * @param <T> type of Object that makes up the Nodes.
 */
class PathEdge<T> {
    /**
     * reference to the node this PathEdge belongs to.
     */
    public PathNode<T> node;
}
