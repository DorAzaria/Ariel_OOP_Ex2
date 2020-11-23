package api;
import gameClient.*;
import gameClient.util.Point3D;
import java.awt.Color;

import java.util.Collection;
import java.util.HashMap;

public class DWGraph_DS implements directed_weighted_graph {

    HashMap<Integer,node_data> vertexes;
    HashMap<Integer,HashMap<node_data,edge_data>> adjacency;
    private int e, mc;

    @Override
    public node_data getNode(int key) {
        if(vertexes.containsKey(key)) {
            return vertexes.get(key);
        }
        return null;
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(node_data n) {
        vertexes.put(n.getKey(),n);
        adjacency.put(n.getKey(),new HashMap<>());
        mc++;
    }

    @Override
    public void connect(int src, int dest, double w) {
        if(vertexes.containsKey(src) && vertexes.containsKey(dest) && src!=dest && w>=0) {
            node_data source = getNode(src), destination = getNode(dest);
            if(adjacency.get(src).containsKey(destination)) {
                ((EdgeData)adjacency.get(src).get(destination)).setWeight(w); // get edge
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
        return null;
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        return null;
    }

    @Override
    public node_data removeNode(int key) {
        return null;
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return vertexes.size();
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }

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