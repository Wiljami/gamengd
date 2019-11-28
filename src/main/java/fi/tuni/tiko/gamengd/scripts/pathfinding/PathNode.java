package fi.tuni.tiko.gamengd.scripts.pathfinding;

import java.util.List;

public class PathNode<T> implements Comparable {
    private T data;

    /**
     * edges is a collection of Nodes leading out from this PathNode.
     */
    private List<PathEdge<T>> edges;

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setEdges(List<PathEdge<T>> edges) {
        this.edges = edges;
    }

    public List<PathEdge<T>> getEdges() {
        return edges;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}