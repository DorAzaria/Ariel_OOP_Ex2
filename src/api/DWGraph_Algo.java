package api;

import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {
    directed_weighted_graph graph;

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
        // return graph.edgeSize() == graph.nodeSize()*(graph.nodeSize()-1); <-- why not to do this method instead of stronglyConnectedCheck?
        return true; // the heavy maybe unnecessary algo.
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

    private void reset() {

    }

    private node_data getNeighbour(edge_data e) {
        return cast(e).getDestination();
    }
}
