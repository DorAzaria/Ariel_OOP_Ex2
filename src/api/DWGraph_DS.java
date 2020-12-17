package api;

import java.util.*;

/**
 * This class represents an directed weighted graph.<br>
 * It has a 3 HashMaps:
 * 1. vertices - contains all the nodes of the graph.
 * 2. adjacency - contains the neighbors of each node.
 * 3. edges - contains all the edge of the graph.
 */
public class DWGraph_DS implements directed_weighted_graph {
    public HashMap<Integer, node_data> vertices;
    public HashMap<Integer, HashMap<node_data, edge_data>> adjacency;
    public HashSet<edge_data> edges;
    private int e, mc;

    /**
     * A default constructor.
     */
    public DWGraph_DS() {
        vertices = new HashMap<>();
        adjacency = new HashMap<>();
        edges = new HashSet<>();
        e = 0;
        mc = 0;
    }

    /**
     * @param key the node_id
     * @return the node from the nodes list, null if none.
     */
    @Override
    public node_data getNode(int key) {
        if (hasNode(key)) {
            return vertices.get(key);
        }
        return null;
    }

    /**
     * @param src  a key of a node.
     * @param dest a key of a node.
     * @return the edge between n1 to n2, if there's no edge it returns null.
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        node_data source = getNode(src);
        node_data destination = getNode(dest);
        if (vertices.containsValue(source) && vertices.containsValue(destination) && adjacency.get(src).containsKey(destination)) {
            return adjacency.get(src).get(destination);
        }
        return null;
    }

    /**
     * Adding a new initialized node to the nodes list.<br>
     * It also init its neighbor list.
     *
     * @param n a node_data type.
     */
    @Override
    public void addNode(node_data n) {
        if ( n!= null && !hasNode(n.getKey())) {
            vertices.put(n.getKey(), n);
            adjacency.put(n.getKey(), new HashMap<>());
            mc++;
        }
    }

    /**
     * check if a node is exists in the graph.
     *
     * @param key a unique key of a node.
     * @return true if the node is exists.
     */
    public boolean hasNode(int key) {
        return vertices.containsKey(key);
    }

    /**
     * Creating an edge between src to dest, setting a weight
     * and adding it to the edges HashMap.
     *
     * @param src  a key of a node.
     * @param dest a key of a node.
     * @param w    the weight value must be at least 0.
     */
    @Override
    public void connect(int src, int dest, double w) {
        if (hasNode(src) && hasNode(dest) && src != dest && w >= 0) {
            node_data source = getNode(src), destination = getNode(dest);
            if (adjacency.get(src).containsKey(destination)) {
                if(getEdge(src,dest).getWeight() == w) return;
                ((Edge) getEdge(src, dest)).setWeight(w);
                edges.add(getEdge(src, dest));
                mc++;
            } else {
                adjacency.get(src).put(destination, new Edge(source, destination, w));
                edges.add(getEdge(src, dest));
                mc++;
                e++;
            }
        }
    }

    /**
     * @return a pointer for a HashSet of all the nodes of the graph.
     */
    @Override
    public Collection<node_data> getV() {
        return new HashSet<>(vertices.values());
    }

    /**
     * @param node_id a key of a node.
     * @return a HashSet of all the "edge's neighbors" of the given node key.
     */
    @Override
    public Collection<edge_data> getE(int node_id) {
        if (hasNode(node_id)) {
            return new HashSet<>(adjacency.get(node_id).values());
        }
        return new HashSet<>();
    }

    /**
     * Removes all of its neighbors from its neighbor list<br>
     * and the edges that are connected to him.
     * At the end of the process it removes the given node.
     *
     * @param key of a node.
     * @return the removed node, null if none.
     */
    @Override
    public node_data removeNode(int key) {
        if (hasNode(key)) {
            node_data source = getNode(key);
            for (edge_data runner : getE(key)) {
                node_data destination = getNode(runner.getDest());
                //out degree: delete dest from source
                removeEdge(source.getKey(), destination.getKey());
                // in degree : delete source from dest
                if (adjacency.get(destination.getKey()).containsKey(source)) {
                    removeEdge(destination.getKey(), source.getKey());
                }
            }
            mc++;
            return vertices.remove(key);
        }
        return null;
    }

    /**
     * Removes the edge between src to dest.
     *
     * @param src  the removed node.
     * @param dest its neighbor node.
     */
    @Override
    public edge_data removeEdge(int src, int dest) {
        if (hasNode(src) && hasNode(dest) && adjacency.get(src).containsKey(getNode(dest))) {
            e--;
            mc++;
            edges.remove(adjacency.get(src).remove(getNode(dest)));
            return adjacency.get(src).remove(getNode(dest));
        }
        return null;
    }

    /**
     * @return a string that represents all the data-fields of this class.
     */
    @Override
    public String toString() {
        return "DWGraph_DS{" +
                "vertices=" + vertices +
                ", adjacency=" + adjacency +
                ", edges=" + edges +
                ", e=" + e +
                ", mc=" + mc +
                '}';
    }

    /**
     * @return the number of nodes in this graph.
     */
    @Override
    public int nodeSize() {
        return vertices.size();
    }

    /**
     * @return the number of edges in this graph.
     */
    @Override
    public int edgeSize() {
        return e;
    }

    /**
     * @return the number of mode counts of this graph.
     */
    @Override
    public int getMC() {
        return mc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DWGraph_DS that = (DWGraph_DS) o;
        if (e != that.e) return false;
        if (mc != that.mc) return false;
        if (!Objects.equals(vertices, that.vertices)) return false;
        if (!Objects.equals(adjacency, that.adjacency)) return false;
        return Objects.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        int result = vertices != null ? vertices.hashCode() : 0;
        result = 31 * result + (adjacency != null ? adjacency.hashCode() : 0);
        result = 31 * result + (edges != null ? edges.hashCode() : 0);
        result = 31 * result + e;
        result = 31 * result + mc;
        return result;
    }
}
