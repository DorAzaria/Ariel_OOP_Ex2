package api;

import com.google.gson.Gson;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class DWGraph_Algo implements dw_graph_algorithms {
    directed_weighted_graph graph;
    static HashSet<edge_data> pairs = new HashSet<>(); // list that contains all the pairs
    static final int GRAY = Color.GRAY.getRGB();
    static final double EPSILON = 0.001;

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
                graphcopy.addNode(new Nodes(runner)); // copies the current "runner" node
                for(edge_data edge : graph.getE(runner.getKey())) { // for loop that's copies the edges
                    if(!graphcopy.hasNode(edge.getDest())) { // if its neighbour isn't in the new graph yet
                        graphcopy.addNode(new Nodes(getNeighbour(edge)));// getting the dest node by casting to EdgeData.
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
          Reset();
          directed_weighted_graph graph_transpose = new DWGraph_DS();
          node_data first = graph.getV().iterator().next();
          Kosaraju(graph,first);
          if(graph.edgeSize() != pairs.size()) return false;
          graphTranspose(graph_transpose);
          node_data second = graph.getNode(first.getKey());
          pairs = new HashSet<>();
          Kosaraju(graph_transpose,second);
          if(pairs.size() == graph.edgeSize()) return true;
          return false;
       }
    return true;
    }

    private void graphTranspose(directed_weighted_graph g) {
        for(node_data runner : graph.getV()) {
            runner.setTag(Color.red.getRGB());
            g.addNode(new Nodes(runner));
        }
        for(edge_data edge : pairs) {
            g.connect(edge.getDest(),edge.getSrc(),edge.getWeight());
        }

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
    private Collection<node_data> Dijkstra(node_data source , node_data destination) {
            if(graph.getV().contains(source) && graph.getV().contains(destination) && source != destination
            && source != null && destination !=null) {
                Reset();
                pairs = new HashSet<>();
                Queue<node_data> queue = new PriorityQueue<>(new Comparator());
                source.setWeight(0);
                queue.add(source);
                while (!queue.isEmpty()) {
                    node_data w = queue.poll();
                    w.setInfo("visited");
                    for (edge_data edge : graph.getE(w.getKey())) {
                        node_data dest = cast(edge).getDestination();
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
                return makePath(graph_transpose,src,dest);
            }
            return new LinkedList<>();
    }

    @Override
    public double shortestPathDist(int src, int dest) {
            node_data source = graph.getNode(src);
            node_data destination = graph.getNode(dest);
            LinkedList<node_data> path = new LinkedList<>(Dijkstra(source,destination));
            if(!path.isEmpty()){
                return path.getLast().getWeight();
            }
        return -1;
    }


    @Override
    public List<node_data> shortestPath(int src, int dest) {
        node_data source = graph.getNode(src);
        node_data destination = graph.getNode(dest);
        LinkedList<node_data> path = new LinkedList<>(Dijkstra(source,destination));
        if(!path.isEmpty()){
            return path;
        }
        return null;
    }

    @Override
    public boolean save(String file) throws IOException {
//        Gson json = new Gson();
//        String filename = json.toJson(graph);
//        FileWriter fw = new FileWriter(new File(file));
//        fw.write(filename);
//        fw.close();
        return false;
    }

    @Override
    public boolean load(String file) {
//
//        try {
//            Gson gson1 = new Gson();
//            FileReader reader = new FileReader("A0.json");
//            importGame importer = new importGame();
//            importer = gson1.fromJson(reader,importGame.class);
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        return false;
    }

    private Collection<node_data> makePath(directed_weighted_graph graph , node_data source , node_data destination) {
      Stack<node_data> stack = new Stack<>();
      Queue<node_data> queue = new LinkedList<>();
      LinkedList<node_data> path = new LinkedList<>();
      if(destination.getWeight() != Double.MAX_VALUE) {
            stack.add(destination);
            node_data current = destination;
            while(current != source) {
                for(edge_data edge : graph.getE(current.getKey())) {
                    double weight = current.getWeight() - edge.getWeight();
                    node_data w = cast(edge).getDestination();
                    if(Math.abs(w.getWeight() - weight) < EPSILON) {
                        stack.push(w);
                        queue.add(w);
                        break;
                    }
                }
                current = queue.poll();
            }

            while(!stack.isEmpty()) {
                path.add(stack.pop());
            }
      }
        return path;
    }

    private Edges cast(edge_data e) {
        return ((Edges)e);
    }
    private node_data getNeighbour(edge_data e) {
        return cast(e).getDestination();
    }

    private void Reset() {
        for(node_data runner : graph.getV()) {
            runner.setTag(Color.RED.getRGB());
            runner.setWeight(Double.MAX_VALUE);
            runner.setInfo("unvisited");
        }
    }
    private static class Comparator implements java.util.Comparator<node_data> {

        @Override
        public int compare(node_data o1, node_data o2) {
            return Double.compare(o1.getWeight(),o2.getWeight());
        }
    }


    }
