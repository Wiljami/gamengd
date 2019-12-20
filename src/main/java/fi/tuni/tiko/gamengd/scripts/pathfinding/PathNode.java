package fi.tuni.tiko.gamengd.scripts.pathfinding;

import java.util.List;

/**
 * PathNode is a node within the pathfinding.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 * @param <T> the type of object this PathNode is made of.
 */
public class PathNode<T>  {
    /**
     * Reference to the object of this PathNode.
     */
    private T data;

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
}
