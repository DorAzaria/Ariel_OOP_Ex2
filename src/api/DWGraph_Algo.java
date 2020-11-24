package api;

import java.util.List;

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
            // for loop that's copies the vertices
            for(node_data runner : graph.getV()) {
                graphcopy.addNode(new NodeData(runner));
                // for loop that's copies the edges
                for(edge_data neighbour : graph.getE(runner.getKey())) {
                    if(!graphcopy.vertices.containsValue(neighbour)) {
                    node_data source = runner;
                    node_data destination = graph.getNode(neighbour.getDest());

                    }

                }

        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
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
}
