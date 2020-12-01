package api;

import java.util.*;

public class DWGraph_DS implements directed_weighted_graph {
    HashMap<Integer,node_data> vertices;
    HashMap<Integer,HashMap<node_data,edge_data>> adjacency;
    private int e, mc;

    public DWGraph_DS() {
        vertices = new HashMap<>();
        adjacency = new HashMap<>();
        e = 0;
        mc = 0;
    }
    @Override
    public node_data getNode(int key) {
        if(vertices.containsKey(key)) {
            return vertices.get(key);
        }
        return null;
    }


    @Override
    public edge_data getEdge(int src, int dest) {
        node_data source = getNode(src);
        node_data destination = getNode(dest);
        if(vertices.containsValue(source) && vertices.containsValue(destination) && adjacency.get(src).containsKey(destination)) {
            return adjacency.get(src).get(destination);
        }
        return null;
    }

    @Override
    public void addNode(node_data n) {
        if (!vertices.containsKey(n.getKey())) {
            vertices.put(n.getKey(), n);
            adjacency.put(n.getKey(), new HashMap<>());
            mc++;
        }
    }

    public boolean hasNode(int key) { return vertices.containsKey(key); }

    @Override
    public void connect(int src, int dest, double w) {
        if(vertices.containsKey(src) && vertices.containsKey(dest) && src!=dest && w>=0) {
            node_data source = getNode(src), destination = getNode(dest);
            if(adjacency.get(src).containsKey(destination)) {
                ((Edges)getEdge(src,dest)).setWeight(w); // get edge
                mc++;
            } else {
                adjacency.get(src).put(destination,new Edges(source,destination,w));
                mc++;
                e++;
            }
        }
    }

    @Override
    public Collection<node_data> getV() {
        return new HashSet<>(vertices.values());
    }

    public Collection<node_data> getV(int key) {
        return new HashSet<>(adjacency.get(key).keySet());
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
    if(vertices.containsKey(node_id)) {
        HashSet<edge_data> edges  = new HashSet<>(adjacency.get(node_id).values());
        return edges;
        }
    return new HashSet<>();
    }

    @Override
    public node_data removeNode(int key) {
      if(vertices.containsKey(key)) {
          node_data source = getNode(key);
          for(edge_data runner : getE(key)) {
              node_data destination = getNode(runner.getDest());
              //out degree: delete dest from source
              removeEdge(source.getKey(),destination.getKey());
             // in degree : delete source from dest
              if(adjacency.get(destination.getKey()).containsKey(source)) {
                  removeEdge(destination.getKey(),source.getKey());
              }
          }
          mc++;
          return vertices.remove(key);
      }
        return null;
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        if(vertices.containsKey(src) && vertices.containsKey(dest) && adjacency.get(src).containsKey(getNode(dest))) {
            e--;
            mc++;
            return adjacency.get(src).remove(getNode(dest));
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return vertices.size();
    }

    @Override
    public int edgeSize() {return e;}

        @Override
    public int getMC() {return mc;}

}
