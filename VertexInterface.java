import java.util.Iterator;

/**
 * This interface represents all the functionalities of a Vertex in a graph.
 * 
 * @author Amanjot Dhillon
 *
 * @param <T> is type of vertex.
 */
public interface VertexInterface<T> {
    /**
     * Gets this vertex’s label.
     * 
     * @return vertex label.
     */
    T getLabel();

    /**
     * Gets the number of neighbors of this vertex.
     * 
     * @return number adjacent vertices.
     */
    int getNumberOfNeighbors();

    /**
     * Marks this vertex as visited.
     */
    void visit();

    /**
     * Removes this vertex’s visited mark.
     */
    void unvisit();

    /**
     * Checks if if the vertex is visited.
     * 
     * @return true if the vertex is visited.
     */
    boolean isVisited();

    /**
     * Connects this vertex and endVertex with a weighted edge. The two vertices
     * cannot be the same, and must not already have this edge between them. Two
     * vertices are equal (same)if their labels are equal (same).
     * 
     * @param endVertex  is the ending vertex.
     * @param edgeWeight is the weight of the edge.
     * @return true if the connection is successful, false otherwise.
     */
    boolean connect(VertexInterface<T> endVertex, double edgeWeight);

    /**
     * Connects this vertex and endVertex with a unweighted edge. The two vertices
     * cannot be the same, and must not already have this edge between them. Two
     * vertices are equal (same)if their labels are equal (same).
     * 
     * @param endVertex is the ending vertex.
     * @return true if the connection is successful.
     */
    boolean connect(VertexInterface<T> endVertex);

    /**
     * Disconnects this vertex from a given vertex with a weighted edge, i.e.,
     * removes the edge. The Edge should exist in order to be disconnected.
     * 
     * @param endVertex  is the end vertex.
     * @param edgeWeight is the weight of the edge.
     * @return true if the disconnection is successful.
     */
    boolean disconnect(VertexInterface<T> endVertex, double edgeWeight);

    /**
     * Disconnects this vertex from a given vertex with an unweighted edge. The Edge
     * should exist in order to be disconnected.
     * 
     * @param endVertex is the end vertex.
     * @return true if the disconnection is successful.
     */
    boolean disconnect(VertexInterface<T> endVertex);

    /**
     * creates an iterator of this vertex's neighbors by following all edges that
     * begin at this vertex.
     * 
     * @return iterator of adjacent vertices.
     */
    Iterator<VertexInterface<T>> getNeighborIterator();

    /**
     * creates an iterator of the weights of the edges this vertex's neighbors by
     * following all edges that begin at this vertex.
     * 
     * @return iterator of weights of edges.
     */
    Iterator<Double> getWeightIterator();

    /**
     * Sees whether this vertex has at least one neighbor.
     * 
     * @return true if vertex has a neighbor.
     */
    boolean hasNeighbor();

    /**
     * Gets an unvisited neighbor, if any, of this vertex.
     * 
     * @return unvisited neighbor of vertex.
     */
    VertexInterface<T> getUnvisitedNeighbor();

    /**
     * Records the previous vertex on a path to this vertex.
     * 
     * @param predecessor is the predecessor of this vertex.
     */
    void setPredecessor(VertexInterface<T> predecessor);

    /**
     * Gets the recorded predecessor of this vertex.
     * 
     * @return preceding vertex.
     */
    VertexInterface<T> getPredecessor();

    /**
     * Sees whether a predecessor was recorded for this vertex.
     * 
     * @return true if predecessor vertex exists.
     */
    boolean hasPredecessor();

    /**
     * Records the cost of a path to this vertex.
     * 
     * @param newCost is the cost of the path to particular vertex.
     */
    void setCost(double newCost);

    /**
     * gets the cost of a path to this vertex.
     * 
     * @return cost of a path to this vertex.
     */
    double getCost();

}
