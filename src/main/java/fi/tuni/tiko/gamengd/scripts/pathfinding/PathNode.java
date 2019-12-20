package fi.tuni.tiko.gamengd.scripts.pathfinding;

import java.util.List;

/**
 * PathNode is a node within the pathfinding.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 * @param <T> the type of object this PathNode is made of.
 */
public class PathNode<T> implements Comparable<PathNode<T>>  {
    /**
     * Reference to the object of this PathNode.
     */
    private T data;

    /**
     * fScore of this node.
     */
    private double fScore;
    /**
     * gScore of this node.
     */
    private double gScore;

    /**
     * PathNode constructor.
     */
    public PathNode() {
        setFScore(Double.MAX_VALUE);
        setGScore(Double.MAX_VALUE);
    }

    /**
     * edges is a collection of Nodes leading out from this PathNode.
     */
    private List<PathEdge<T>> edges;

    /**
     * setter for data.
     * @param data new data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * getter for data.
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * setter for edges.
     * @param edges new edges
     */
    public void setEdges(List<PathEdge<T>> edges) {
        this.edges = edges;
    }

    /**
     * getter for edges.
     * @return edges
     */
    public List<PathEdge<T>> getEdges() {
        return edges;
    }

    /**
     * compareTo is from Comparable interface.
     *
     * It compares the fScores of two PathNodes.
     * @param pathNode the other PathNode.
     * @return comparision value.
     */
    @Override
    public int compareTo(PathNode<T> pathNode) {
        if (getFScore() < pathNode.getFScore()) return -1;
        else if (pathNode.getFScore() < getFScore()) return 1;
        return 0;
    }

    /**
     * Getter for fScore
     *
     * @return value of fScore
     */
    public double getFScore() {
        return fScore;
    }

    /**
     * Sets fScore
     *
     * @param fScore new value
     */
    public void setFScore(double fScore) {
        this.fScore = fScore;
    }

    /**
     * Getter for gScore
     *
     * @return value of gScore
     */
    public double getGScore() {
        return gScore;
    }

    /**
     * Sets gScore
     *
     * @param gScore new value
     */
    public void setGScore(double gScore) {
        this.gScore = gScore;
    }
}
