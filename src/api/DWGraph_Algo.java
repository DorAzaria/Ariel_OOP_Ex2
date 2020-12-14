package api;

import com.google.gson.*;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * This interface represents a Directed (positive) Weighted Graph Theory Algorithms including:<br>
 * 0. clone(); (copy)<br>
 * 1. init(graph);<br>
 * 2. isConnected(); // strongly (all ordered pais connected)<br>
 * 3. double shortestPathDist(int src, int dest);<br>
 * 4. List<node_data> shortestPath(int src, int dest);<br>
 * 5. Save(file); // JSON file<br>
 * 6. Load(file); // JSON file
 */
public class DWGraph_Algo implements dw_graph_algorithms {
    directed_weighted_graph graph;
    static HashSet<edge_data> pairs = new HashSet<>(); // list that contains all the pairs
    static final int GRAY = Color.GRAY.getRGB();
    static final double EPSILON = 0.001;

    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g - a directed_weighted_graph type.
     */
    @Override
    public void init(directed_weighted_graph g) {
        graph = g;
    }

    /**
     * @return the underlying graph of which this class works.
     */
    @Override
    public directed_weighted_graph getGraph() {
        return graph;
    }

    /**
     * Compute a deep copy of this weighted graph.
     * Time Complexity - O(N^2).
     * @return a deep-copied graph.
     */
    @Override
    public directed_weighted_graph copy() {
        if (graph.getV().size() != 0) {
            DWGraph_DS copied = new DWGraph_DS();
            for (node_data runner : graph.getV()) {
                copied.addNode(new Node(runner));
                for (edge_data edge : graph.getE(runner.getKey())) {
                    if (!copied.hasNode(edge.getDest())) {
                        copied.addNode(new Node(((Edge) edge).destination));
                    }
                    copied.connect(runner.getKey(), edge.getDest(), edge.getWeight());
                }
            }
            return copied;
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        if (graph.getV().size() > 1) {
            Reset();
            directed_weighted_graph graph_transpose = new DWGraph_DS();
            node_data first = graph.getV().iterator().next();
            Kosaraju(graph, first);
            if (graph.edgeSize() != pairs.size()) return false;
            graphTranspose(graph_transpose);
            node_data second = graph.getNode(first.getKey());
            pairs = new HashSet<>();
            Kosaraju(graph_transpose, second);
            return pairs.size() == graph.edgeSize();
        }
        return true;
    }

    private void graphTranspose(directed_weighted_graph g) {
        for (node_data runner : graph.getV()) {
            runner.setTag(Color.red.getRGB());
            g.addNode(new Node(runner));
        }
        for (edge_data edge : pairs) {
            g.connect(edge.getDest(), edge.getSrc(), edge.getWeight());
        }

    }

    private void Kosaraju(directed_weighted_graph graph, node_data runner) {
        runner.setTag(GRAY); // visited node colored by gray
        for (edge_data neighbours : graph.getE(runner.getKey())) {
            node_data dest = ((Edge) neighbours).destination;
            pairs.add(neighbours);
            if (dest.getTag() != GRAY) {
                Kosaraju(graph, dest);
            }
        }
    }

    private Collection<node_data> Dijkstra(node_data source, node_data destination) {
        if (graph.getV().contains(source) && graph.getV().contains(destination) && source != destination
                && source != null && destination != null) {
            Reset();
            pairs = new HashSet<>();
            Queue<node_data> queue = new PriorityQueue<>(new Comparator());
            source.setWeight(0);
            queue.add(source);
            while (!queue.isEmpty()) {
                node_data w = queue.poll();
                w.setInfo("visited");
                for (edge_data edge : graph.getE(w.getKey())) {
                    node_data dest = ((Edge) edge).destination;
                    pairs.add(edge);
                    if (dest.getInfo().equals("unvisited")) {
                        double weight = w.getWeight() + edge.getWeight();
                        if (weight < dest.getWeight()) {
                            dest.setWeight(weight);
                            queue.add(dest);
                        }
                    }
                }
            }
            directed_weighted_graph graph_transpose = new DWGraph_DS();
            graphTranspose(graph_transpose);
            node_data src = graph_transpose.getNode(source.getKey());
            node_data dest = graph_transpose.getNode(destination.getKey());
            return makePath(graph_transpose, src, dest);
        }
        return new LinkedList<>();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        node_data source = graph.getNode(src);
        node_data destination = graph.getNode(dest);
        LinkedList<node_data> path = new LinkedList<>(Dijkstra(source, destination));
        if (!path.isEmpty()) {
            return path.getLast().getWeight();
        }
        return -1;
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
        node_data source = graph.getNode(src);
        node_data destination = graph.getNode(dest);
        LinkedList<node_data> path = new LinkedList<>(Dijkstra(source, destination));
        if (!path.isEmpty()) {
            return path;
        }
        return null;
    }

    @Override
    public boolean save(String file) {
        Gson gson = new Gson();
        JsonObject jsonGraph = new JsonObject();
        JsonArray Edges = new JsonArray();
        JsonArray Nodes = new JsonArray();
        // initialize the nodes from the graph to Nodes jsonArray object
        for (node_data runner : graph.getV()) {
            JsonObject v = new JsonObject();
            String location = runner.getLocation().x() + "," + runner.getLocation().y() + "," + runner.getLocation().z();
            v.addProperty("pos", location);
            v.addProperty("id", runner.getKey());
            Nodes.add(v);
        }
        // initialize the edges from graph to Edges JsonArray object
        for (edge_data edge : ((DWGraph_DS) graph).edges) {
            JsonObject e = new JsonObject();
            e.addProperty("src", edge.getSrc());
            e.addProperty("w", edge.getWeight());
            e.addProperty("dest", edge.getDest());
            Edges.add(e);
        }
        jsonGraph.add("Edges", Edges);
        jsonGraph.add("Nodes", Nodes);
        String json = gson.toJson(jsonGraph);
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(json);
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            GsonBuilder builder = new GsonBuilder()
                    .registerTypeAdapter(directed_weighted_graph.class, new graphDeserialization());
            Gson gson = builder.create();
            FileReader fr = new FileReader(file);
            this.graph = gson.fromJson(fr, directed_weighted_graph.class);
            return true;
        } catch (FileNotFoundException f) {
            return false;
        }
    }

    private Collection<node_data> makePath(directed_weighted_graph graph, node_data source, node_data destination) {
        Stack<node_data> stack = new Stack<>();
        Queue<node_data> queue = new LinkedList<>();
        LinkedList<node_data> path = new LinkedList<>();
        if (destination.getWeight() != Double.MAX_VALUE) {
            stack.add(destination);
            node_data current = destination;
            while (current != source) {
                for (edge_data edge : graph.getE(current.getKey())) {
                    double weight = current.getWeight() - edge.getWeight();
                    node_data w = ((Edge) edge).destination;
                    if (Math.abs(w.getWeight() - weight) < EPSILON) {
                        stack.push(w);
                        queue.add(w);
                        break;
                    }
                }
                current = queue.poll();
            }
            while (!stack.isEmpty()) {
                path.add(stack.pop());
            }
        }
        return path;
    }

    private void Reset() {
        for (node_data runner : graph.getV()) {
            runner.setTag(Color.RED.getRGB());
            runner.setWeight(Double.MAX_VALUE);
            runner.setInfo("unvisited");
        }
    }

    private static class Comparator implements java.util.Comparator<node_data> {

        @Override
        public int compare(node_data o1, node_data o2) {
            return Double.compare(o1.getWeight(), o2.getWeight());
        }
    }

}
