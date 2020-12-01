package api;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DWGraph_Algo implements dw_graph_algorithms {
    directed_weighted_graph graph;
    static HashSet<edge_data> pairs = new HashSet<>(); // list that contains all the pairs
    static final int GRAY = Color.GRAY.getRGB();

    @Override
    public void init(directed_weighted_graph g) {
        graph = g;
    }

    @Override
    public directed_weighted_graph getGraph() {
        return graph;
    }

    @Override
    public directed_weighted_graph copy() {
        if(graph.getV().size() != 0) { // its implies that the graph is not empty
            DWGraph_DS graphcopy = new DWGraph_DS();
            for(node_data runner : graph.getV()) {  // for loop that's copies the vertices
                graphcopy.addNode(new NodeData(runner)); // copies the current "runner" node
                for(edge_data edge : graph.getE(runner.getKey())) { // for loop that's copies the edges
                    if(!graphcopy.hasNode(edge.getDest())) { // if its neighbour isn't in the new graph yet
                        graphcopy.addNode(new NodeData(getNeighbour(edge)));// getting the dest node by casting to EdgeData.
                    }
                    graphcopy.connect(runner.getKey(),edge.getDest(),edge.getWeight());
                }
            }
            return graphcopy;
        }
        return null;
    }

    @Override
    public boolean isConnected() {
       if(graph.getV().size() > 1){
          directed_weighted_graph temp_graph = new DWGraph_DS();
          node_data first = graph.getV().iterator().next();
          Kosaraju(graph,first);
          if(graph.edgeSize() != pairs.size()) return false;
          graphTranspose(temp_graph);
          node_data second = graph.getNode(first.getKey());
          pairs = new HashSet<>();
          Kosaraju(temp_graph,second);
          if(pairs.size() == graph.edgeSize()) return true;
          return false;
       }
    return true;
    }

    private void graphTranspose(directed_weighted_graph g) {
        for(node_data runner : graph.getV()) {
            runner.setTag(Color.red.getRGB());
            g.addNode(new NodeData(runner));
        }
        for(edge_data edge : pairs) {
            g.connect(edge.getDest(),edge.getSrc(),edge.getWeight());
        }

    }

    private void swap(edge_data edge) {
        node_data source = cast(edge).source;
        node_data dest = cast(edge).dest;
        double weight = edge.getWeight();
        cast(graph).adjacency.put(dest.getKey(),new HashMap<>());
        graph.connect(dest.getKey(),source.getKey(),weight);

    }

    private void Kosaraju(directed_weighted_graph graph,node_data runner) {
        runner.setTag(GRAY); // visited node colored by gray
        for(edge_data neighbours : graph.getE(runner.getKey())) {
            node_data dest = cast(neighbours).getDestination();
            pairs.add(neighbours);
            if(dest.getTag() != GRAY) {
                Kosaraju(graph,dest);
            }
        }
    }
    private void Dijkstra(directed_weighted_graph graph , node_data source , node_data destination) {
            Reset();
            Queue<node_data> queue = new PriorityQueue<>(new Comparator());

    }

    @Override
    public double shortestPathDist(int src, int dest) {

        return 0;
    }


    @Override
    public List<node_data> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }

    private DWGraph_DS cast(directed_weighted_graph g) {
        return ((DWGraph_DS)g);
    }
    private NodeData cast(node_data n) {
        return ((NodeData)n);
    }
    private EdgeData cast(edge_data e) {
        return ((EdgeData)e);
    }

    private void Reset() {
        for(node_data runner : graph.getV()) {
            runner.setTag(Color.RED.getRGB());
            runner.setWeight(Double.MAX_VALUE);
        }
    }
    private static class Comparator implements java.util.Comparator<node_data> {

        @Override
        public int compare(node_data o1, node_data o2) {
            return Double.compare(o1.getTag(),o2.getTag());
        }
    }

    private node_data getNeighbour(edge_data e) {
        return cast(e).getDestination();
    }

    }
