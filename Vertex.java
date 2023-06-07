import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This generic class implements the VertexInterface.
 * 
 * @author Amanjot Dhillon
 *
 * @param <T> the type of vertex.
 */
public class Vertex<T> implements VertexInterface<T> {

    /**
     * Represents the label of the vertex.
     */
    private T label;

    /**
     * Stores if the vertex is visited or not, true if visited.
     */
    private boolean visited;

    /**
     * previous vertex on path to this vertex.
     */
    private VertexInterface<T> previousVertex;

    /**
     * cost of path to this vertex.
     */
    private double cost;

    /**
     * list of edges to neighbors.
     */
    protected LinkedList<Edge> edgeList;

    /**
     * This is a helper class that instantiates edges.
     * 
     * @author amandhillon.
     *
     */
    protected class Edge {
        /**
         * weight of edge.
         */
        private double weight;

        /**
         * vertex of the edge.
         */
        private VertexInterface<T> vertex;

        /**
         * Constructor for Edge class.
         * 
         * @param weightOfEdge is the weight of edge.
         * @param edgeVertex   is the edge vertex.
         */
        protected Edge(double weightOfEdge, VertexInterface<T> edgeVertex) {
            this.vertex = edgeVertex;
            this.weight = weightOfEdge;
        }

        /**
         * Method to get the vertex.
         * 
         * @return vertex for the edge list.
         */
        protected VertexInterface<T> getEdgeVertex() {
            return this.vertex;
        }

        /**
         * Method to get the weight for edge.
         * 
         * @return weight for the edge list.
         */
        protected double getWeight() {
            return this.weight;
        }

    }

    /**
     * parameterized constructor for Vertex class.
     * 
     * @param vertexLabel is the label for the vertex.
     */
    public Vertex(T vertexLabel) {
        label = vertexLabel;
        this.visited = false;
        this.cost = 0.0;
        this.previousVertex = null;
        this.edgeList = new LinkedList<>();
    }

    /**
     * This is a Iterator class that transverses across vertices.
     * 
     * @author amandhillon
     *
     */
    private class NeighborIterator implements Iterator<VertexInterface<T>> {
        /**
         * This is a iterator for neighboring edges.
         */
        private Iterator<Edge> walker;

        /**
         * This method gets a neighbor Iterator.
         */
        private NeighborIterator() {
            this.walker = edgeList.listIterator();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return this.walker.hasNext();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public VertexInterface<T> next() {
            VertexInterface<T> curr = null;
            if (this.walker.hasNext()) {

                Edge nextEdge = this.walker.next();
                curr = nextEdge.getEdgeVertex();

            } else
                throw new NoSuchElementException();
            return curr;
        }
    }

    /**
     * This is a Iterator class that transverses through the weights on edges.
     * 
     * @author amandhillon.
     *
     */
    private class WeightIterator implements Iterator<Double> {
        /**
         * edge iterator.
         */
        Iterator<Edge> walker;

        /**
         * weight iterator constructor.
         */
        private WeightIterator() {
            this.walker = edgeList.iterator();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return walker.hasNext();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Double next() {

            if (!hasNext())
                throw new NoSuchElementException();
            else {
                Double temp = 0.0;

                Edge nextEdge = walker.next();
                temp = nextEdge.getWeight();

                return temp;
            }

        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {

        boolean isEqual;

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            isEqual = false;
        }

        if (getClass() != obj.getClass()) {
            isEqual = false;
        } else {
            Vertex<T> otherObj = (Vertex<T>) obj;
            isEqual = label.equals(otherObj.label);
        }
        return isEqual;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getLabel() {
        return this.label;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfNeighbors() {
        int n = 0;
        Iterator<VertexInterface<T>> adjacent = getNeighborIterator();

        while (adjacent.hasNext()) {
            VertexInterface<T> nextAdjacent = adjacent.next();
            if (!nextAdjacent.isVisited()) {
                n++;
            }
        }
        return n;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visit() {
        this.visited = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unvisit() {
        this.visited = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        boolean connected = false;
        boolean seenBefore = false;
        if (!this.equals(endVertex)) {
            Iterator<VertexInterface<T>> adj = this.getNeighborIterator();

            while (adj.hasNext() && !seenBefore) {
                VertexInterface<T> curr = adj.next();

                if (endVertex.equals(curr)) {
                    seenBefore = true;
                }

            }

            if (!seenBefore) {
                edgeList.add(new Edge(edgeWeight, endVertex));
                connected = true;
            }
        }

        return connected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex, double edgeWeight) {
        for (Edge edge : this.edgeList) {
            if (endVertex.equals(edge.getEdgeVertex()) && edgeWeight == edge.getWeight()) {
                return edgeList.remove(edge);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean disconnect(VertexInterface<T> endVertex) {
        return disconnect(endVertex, 0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<VertexInterface<T>> getNeighborIterator() {
        return new NeighborIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<Double> getWeightIterator() {
        return new WeightIterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNeighbor() {
        return !edgeList.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {
        VertexInterface<T> notSeen = null;
        Iterator<VertexInterface<T>> adjacent = getNeighborIterator();

        while ((adjacent.hasNext()) && (notSeen == null)) {
            VertexInterface<T> nextAdjacent = adjacent.next();
            if (!nextAdjacent.isVisited()) {
                notSeen = nextAdjacent;
            }
        }
        return notSeen;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCost() {
        return this.cost;
    }

}
