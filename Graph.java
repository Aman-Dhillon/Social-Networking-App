import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This is a generic undirected graph class that implements the GraphInterface.
 * 
 * @author amandhillon
 *
 * @param <T> is type of Graph.
 */
public class Graph<T> implements GraphInterface<T> {

    /**
     * Represents the graph of vertices.
     */
    protected Map<T, VertexInterface<T>> vertices;

    /**
     * Represents the number of edges in the graph.
     */
    protected int numOfEdges;

    /**
     * Default constructor for graph.
     */
    public Graph() {

        vertices = new HashMap<T, VertexInterface<T>>();
        this.numOfEdges = 0;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addVertex(T vertexLabel) {
        VertexInterface<T> toAdd = vertices.put(vertexLabel, new Vertex<T>(vertexLabel));

        return toAdd == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> removeVertex(T vertexLabel) {
        VertexInterface<T> toRemove = vertices.get(vertexLabel);
        ArrayList<T> edgeVertices = new ArrayList<T>();
        if (toRemove.getLabel() != null && toRemove != null) {
            if (toRemove.hasNeighbor()) {
                Iterator<VertexInterface<T>> neighbor = toRemove.getNeighborIterator();

                while (neighbor.hasNext()) {
                    VertexInterface<T> curr = neighbor.next();
                    edgeVertices.add(curr.getLabel());
                }
            }
            for (int i = 0; i < edgeVertices.size(); i++) {
                toRemove.disconnect(toRemove, i);
                numOfEdges = numOfEdges - 2;

            }

            vertices.remove(toRemove.getLabel(), toRemove);

        }

        return toRemove;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean wasAdded = false;

        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if ((beginVertex != null) && (endVertex != null)
                && (!(doesExist(begin, end, edgeWeight) || doesExist(end, begin, edgeWeight))))
            wasAdded = (beginVertex.connect(endVertex, edgeWeight) && endVertex.connect(beginVertex, edgeWeight));

        if (wasAdded) {
            numOfEdges = numOfEdges + 2;
        }

        return wasAdded;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(T begin, T end, double edgeWeight) {
        boolean wasRemoved = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if ((beginVertex != null) && (endVertex != null))
            wasRemoved = (beginVertex.disconnect(endVertex, edgeWeight)
                    && endVertex.disconnect(beginVertex, edgeWeight));

        if (wasRemoved) {
            numOfEdges = numOfEdges - 2;
        }

        return wasRemoved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(T begin, T end) {
        return removeEdge(begin, end, 0.0);
    }

    /**
     * Helper function to determine the existence of an edge between two vertices.
     * 
     * @param begin  vertex within graph.
     * @param end    vertex within graph.
     * @param weight is the weight of the edge.
     * @return true if edge exists otherwise false.
     */
    private boolean doesExist(T begin, T end, double weight) {
        boolean edgeExists = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        if ((beginVertex != null) && (endVertex != null)) {
            Iterator<VertexInterface<T>> adj = beginVertex.getNeighborIterator();
            Iterator<Double> w = beginVertex.getWeightIterator();
            while (adj.hasNext() && w.hasNext()) {
                VertexInterface<T> curr = adj.next();
                Double curr2 = w.next();
                if (endVertex.equals(curr) && curr2 == weight) {
                    edgeExists = true;
                }
            }

        }

        return edgeExists;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEdge(T begin, T end) {
        return doesExist(begin, end, 0.0) || doesExist(end, begin, 0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfVertices() {
        return vertices.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfEdges() {
        return numOfEdges;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VertexInterface<T>> getVertices() {
        if (vertices.isEmpty()) {
            return null;
        }
        ArrayList<VertexInterface<T>> valueList = new ArrayList<VertexInterface<T>>(vertices.values());

        return valueList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        vertices.clear();
        numOfEdges = 0;
    }

    /**
     * Resets vertices of graph.
     */
    private void reset() {
        for (VertexInterface<T> value : vertices.values()) {
            value.unvisit();
            value.setCost(0.0);
            value.setPredecessor(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Queue<T> getBreadthFirstTraversal(T origin) {

        reset();
        Queue<VertexInterface<T>> queueOfVertices = new LinkedList<VertexInterface<T>>();
        VertexInterface<T> start = vertices.get(origin);
        start.visit();
        Queue<T> pathSequence = new LinkedList<T>();
        pathSequence.add(origin);
        queueOfVertices.add(start);

        while (!queueOfVertices.isEmpty()) {
            VertexInterface<T> firstVertex = queueOfVertices.remove();
            Iterator<VertexInterface<T>> adj = firstVertex.getNeighborIterator();

            while (adj.hasNext()) {

                VertexInterface<T> curr = adj.next();

                if (!curr.isVisited()) {
                    curr.visit();
                    queueOfVertices.add(curr);
                    pathSequence.add(curr.getLabel());
                }
            }
        }
        return pathSequence;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShortestPath(T origin, T destination, Stack<T> path) {

        reset();
        Queue<VertexInterface<T>> queueOfVertices = new LinkedList<VertexInterface<T>>();
        VertexInterface<T> start = vertices.get(origin);
        start.visit();
        queueOfVertices.add(start);

        if (origin == destination) {
            return (int) start.getCost();
        }

        while (!queueOfVertices.isEmpty()) {
            VertexInterface<T> firstVertex = queueOfVertices.remove();
            Iterator<VertexInterface<T>> adj = firstVertex.getNeighborIterator();

            while (adj.hasNext()) {
                VertexInterface<T> curr = adj.next();

                if (!curr.isVisited()) {
                    curr.visit();
                    queueOfVertices.add(curr);
                    curr.setPredecessor(firstVertex);
                    curr.setCost(firstVertex.getCost() + 1);
                    if (curr.getLabel().equals(destination)) {
                        path.push(destination);

                        VertexInterface<T> v = curr;
                        while (v.getPredecessor() != null) {
                            path.push(v.getPredecessor().getLabel());
                            v = v.getPredecessor();
                        }
                        return (int) curr.getCost();
                    }

                }
            }
        }
        return Integer.MAX_VALUE;

    }
}
