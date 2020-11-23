package api;
import gameClient.*;
import gameClient.util.Point3D;

import java.util.Collection;

public class DWGraph_DS implements directed_weighted_graph{
    @Override
    public node_data getNode(int key) {
        return null;
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(node_data n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

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
        return 0;
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
        private int key, tag;
        private double weight;
        private String info;

        public NodeData() {
            key = increment++;
            info = "";
            tag = 0;
            weight = 0.0;
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
            return tag;
        }

        @Override
        public void setTag(int t) { tag = t;}

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