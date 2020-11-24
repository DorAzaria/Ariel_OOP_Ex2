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
                        graphcopy.addNode(new NodeData(cast(edge).getDestination()));// getting the dest node by casting to EdgeData.
                    }
                    graphcopy.connect(runner.getKey(),edge.getDest(),edge.getWeight());
                }
            }
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        // return graph.edgeSize() == graph.nodeSize()*(graph.nodeSize()-1); <-- why not to do this method instead of stronglyConnectedCheck?
        return stronglyConnectedCheck(); // the heavy maybe unnecessary algo.
    }

    private boolean stronglyConnectedCheck() {
        int countNodes = 0, countEdges = 0;
        if(graph.nodeSize()!=0) {
            reset();
            node_data starter = graph.getV().iterator().next(); // the first node in the list
            LinkedList<node_data> queue = new LinkedList<>();
            queue.add(starter);
            starter.setInfo("visited");
            while(!queue.isEmpty()) {
                node_data node = queue.poll();
                countNodes++;
                for(edge_data edge : graph.getE(node.getKey())) {
                    if(!edge.getInfo().equals("visited")) { // maybe it's unnecessary
                        edge.setInfo("visited"); // maybe it's unnecessary
                        countEdges++;
                    }
                    if(!getNeighbour(edge).getInfo().equals("visited")) { // if the neighbor isn't visited yet.
                        getNeighbour(edge).setInfo("visited");
                        queue.add(getNeighbour(edge));
                    }
                } // end for loop on each edge of the current node
            }
        }
        return countNodes*(countNodes-1) == countEdges;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    private void Dijkstra(node_data src, node_data dest) {
        reset();
        PriorityQueue<node_data> pq = new PriorityQueue<>((o1,o2)->Double.compare(o1.getWeight(),o2.getWeight()));
        pq.add(src);
        src.setWeight(0.0);

        while(!pq.isEmpty()) {
            node_data current = pq.poll();
            for(edge_data edge : graph.getE(current.getKey())) {
            }
        }
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
