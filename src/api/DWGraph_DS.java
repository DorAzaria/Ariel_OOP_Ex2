package api;
import gameClient.util.Point3D;
import java.awt.Color;

import java.util.*;

public class DWGraph_DS implements directed_weighted_graph {

    HashMap<Integer,node_data> vertices;
    HashMap<Integer,HashMap<node_data,edge_data>> adjacency;
    private int e, mc;

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
        vertices.put(n.getKey(),n);
        adjacency.put(n.getKey(),new HashMap<>());
        mc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(vertices.containsKey(src) && vertices.containsKey(dest) && src!=dest && w>=0) {
            node_data source = getNode(src), destination = getNode(dest);
            if(adjacency.get(src).containsKey(destination)) {
                ((EdgeData)getEdge(src,dest)).setWeight(w); // get edge
                mc++;
            } else {
                adjacency.get(src).put(destination,new EdgeData(source,destination,w));
                mc++;
                e++;
            }
        }
    }

    @Override
    public Collection<node_data> getV() {
        return new HashSet<>(vertices.values());
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
    if(vertices.containsKey(node_id)) {
        Set<node_data> neighbours = new HashSet<>(adjacency.get(node_id).keySet());
        HashSet<edge_data> edges = new HashSet<>();
        for(node_data runner : neighbours) {
           edge_data e = adjacency.get(node_id).get(runner);
           edges.add(e);
        }
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
        if(getE(src).contains(getNode(dest))) {
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


    static class NodeData implements node_data {
        GeoLocation location;
        static int increment = 1;
        private int key;
        private  Color tag;
        private double weight;
        private String info;

        public NodeData() {
            key = increment++;
            info = "";
            weight = 0.0;
            tag = Color.RED;
        }

        @Override
        public int getKey() {
            return key;
        }

        @Override
        public geo_location getLocation() {
            return location;
        }

        @Override
        public void setLocation(geo_location p) {
            location = new GeoLocation(p.x(),p.y(),p.z());
        }

        @Override
        public double getWeight() {
            return weight;
        }

        @Override
        public void setWeight(double w) { weight = w;}

        @Override
        public String getInfo() {
            return info;
        }

        @Override
        public void setInfo(String s) { info = s; }

        @Override
        public int getTag() {
            return tag.getRGB();
        }

        @Override
        public void setTag(int t) { tag = new Color(t);}

        private class GeoLocation implements geo_location {
            private Point3D point;

            public GeoLocation(double x, double y, double z) {
                point = new Point3D(x,y,z);
            }

            @Override
            public double x() {
                return point.x();
            }

            @Override
            public double y() {
                return point.y();
            }

            @Override
            public double z() { return point.z(); }

            @Override
            public double distance(geo_location g) { return point.distance(g); }
        }
    }
}