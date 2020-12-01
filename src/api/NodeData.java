package api;

import gameClient.util.Point3D;
import org.w3c.dom.Node;

import java.awt.*;

    public class NodeData implements node_data {
    private geo_location location;
    static int increment = 1;
    private int key;
    private Color tag;
    private double weight;
    private String info;

    public NodeData() {
        key = increment++;
        info = "unvisited";
        weight = Double.MAX_VALUE;
        tag = Color.RED;

    }
    public NodeData(node_data n) {
        location = n.getLocation();
        key = n.getKey();
        tag = new Color(n.getTag());
        weight = n.getWeight();
        info = n.getInfo();
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
    public int getTag() {return tag.getRGB();}


        @Override
    public void setTag(int t) { tag = new Color(t);}

    private class GeoLocation implements geo_location {
        public Point3D point;

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