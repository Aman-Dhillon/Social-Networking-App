import java.util.List;
import java.util.Stack;
import java.util.Queue;

/**
 * This interface represents all the functionalities of the graph data structure
 * you are going to use in this project. The graph that we are going to use to
 * implement MasonConnect is undirected graph.
 * 
 * @author Amanjot Dhillon
 *
 * @param <T> type of vertex.
 */
public interface GraphInterface<T> {

    /**
     * Adds a given vertex to this graph.
     * 
     * @param vertexLabel the vertex to add.
     * @return if vertex is added returns true.
     */
    boolean addVertex(T vertexLabel);

    /**
     * Removes a vertex with the given vertexLabel from this graph.
     * 
     * @param vertexLabel the vertex to remove.
     * @return the removed vertex. If vertex does not exist, it will return null.
     */
    VertexInterface<T> removeVertex(T vertexLabel);

    /**
     * Adds a weighted edge between two given distinct vertices that are currently
     * in this graph. The desired edge must not already be in the graph. The graph
     * is undirected graph.
     * 
     * @param begin      the beginning vertex of the edge.
     * @param end        the end vertex of the edge.
     * @param edgeWeight the weight of the edge.
     * @return true if weighted edge is added.
     */
    boolean addEdge(T begin, T end, double edgeWeight);

    /**
     * Adds an unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must not already be in the graph.
     * 
     * @param begin is the beginning vertex of the edge.
     * @param end   is the end vertex of the edge.
     * @return true if unweighted edge is added.
     */
    boolean addEdge(T begin, T end);

    /**
     * Removes a weighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must already be in the graph.
     * 
     * @param begin      is the beginning vertex of the edge.
     * @param end        is the end vertex of the edge.
     * @param edgeWeight is the weight of the edge.
     * @return true is weighted edge is removed.
     */
    boolean removeEdge(T begin, T end, double edgeWeight);

    /**
     * Removes a unweighted edge between two given distinct vertices that are
     * currently in this graph. The desired edge must already be in the graph.
     * 
     * @param begin is beginning vertex of the edge.
     * @param end   is end vertex of the edge.
     * @return true if unweighted edge is removed.
     */
    boolean removeEdge(T begin, T end);

    /**
     * Checks whether an undirected edge exists between two given vertices.
     * 
     * @param begin is beginning vertex of the edge.
     * @param end   is the end vertex of the edge.
     * @return true if edge between vertexes exists.
     */
    boolean hasEdge(T begin, T end);

    /**
     * This method checks the number of Vertices in this graph.
     * 
     * @return number of vertices in graph.
     */
    int getNumberOfVertices();

    /**
     * This method checks the number of undirected Edges in this graph.
     * 
     * @return number of edges in graph.
     */
    int getNumberOfEdges();

    /**
     * This method checks if this graph is empty.
     * 
     * @return true if graph is empty.
     */
    boolean isEmpty();

    /**
     * This method determines the list of all vertices in the graph.
     * 
     * @return the list of vertices in graph.
     */
    List<VertexInterface<T>> getVertices();

    /**
     * clears the graph.
     */
    void clear();

    /**
     * Performs a breadth-first traversal of a graph and produces the queue that
     * contains the result.
     * 
     * @param origin is the beginning of vertex.
     * @return the resulting queue.
     */
    Queue<T> getBreadthFirstTraversal(T origin);

    /**
     * Determines the shortest distance between the origin and destination.
     * 
     * @param origin      is the beginning vetex.
     * @param destination is the ending vertex.
     * @param path        is the edges connecting the shortest path.
     * @return the maximum integer (to simulate infinity).
     */
    int getShortestPath(T origin, T destination, Stack<T> path);

}
