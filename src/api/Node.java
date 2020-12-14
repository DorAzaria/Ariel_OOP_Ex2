package api;

import gameClient.util.Point3D;

import java.awt.*;

/**
 * This class represents a node with some fields.
 * each node has a unique key which is given in its initial process.<br>
 * Each key is fits to the same key of the nodes HashMap in DWGraph_Algo.
 */
public class Node implements node_data {
    public geo_location pos;
    private final int key;
    static int increment = 0;
    private Color tag;
    private double weight;
    private String info;

    /**
     * a default constructor
     */
    public Node() {
        pos = new GeoLocation(0, 0, 0);
        key = increment++;
        info = "unvisited";
        weight = Double.MAX_VALUE;
        tag = Color.RED;

    }

    /**
     * a parametric constructor, used especially for copy methods.
     *
     * @param n a node_data type
     */
    public Node(node_data n) {
        pos = n.getLocation();
        key = n.getKey();
        tag = new Color(n.getTag());
        weight = n.getWeight();
        info = n.getInfo();
    }

    /**
     * a parametric constructor, used especially for copy methods.
     *
     * @param id  a key of the node.
     * @param geo a geo_location type.
     */
    public Node(int id, geo_location geo) {
        pos = geo;
        key = id;
        info = "unvisited";
        weight = Double.MAX_VALUE;
        tag = Color.RED;
    }

    /**
     * @return the key of this node.
     */
    @Override
    public int getKey() {
        return key;
    }

    /**
     * @return the position of this node.
     */
    @Override
    public geo_location getLocation() {
        return pos;
    }

    /**
     * @param p - new new location  (position) of this node.
     */
    @Override
    public void setLocation(geo_location p) {
        pos = new GeoLocation(p.x(), p.y(), p.z());
    }

    /**
     * Returns the weight associated with this node.
     *
     * @return the weight of this node.
     */
    @Override
    public double getWeight() {
        return weight;
    }

    /**
     * Allows changing this node's weight.
     *
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        weight = w;
    }

    /**
     * @return the info of this node.
     */
    @Override
    public String getInfo() {
        return info;
    }

    /**
     * Set the information (meta-data) of this node.
     *
     * @param s the value of the information.
     */
    @Override
    public void setInfo(String s) {
        info = s;
    }

    /**
     * @return the tag of this node (Color RGB code).
     */
    @Override
    public int getTag() {
        return tag.getRGB();
    }

    /**
     * tag is basically a Color type.
     *
     * @param t - the new value of the tag (int)
     */
    @Override
    public void setTag(int t) {
        tag = new Color(t);
    }

    /**
     * This  class represents a geo location <x,y,z>, aka Point3D
     */
    public static class GeoLocation implements geo_location {
        public Point3D point;

        /**
         * @param x - the x-axis.
         * @param y - the y-axis.
         * @param z - the z-axis
         */
        public GeoLocation(double x, double y, double z) {
            point = new Point3D(x, y, z);
        }

        /**
         * @return - the x-axis.
         */
        @Override
        public double x() {
            return point.x();
        }

        /**
         * @return - the y-axis.
         */
        @Override
        public double y() {
            return point.y();
        }

        /**
         * @return - the z-axis.
         */
        @Override
        public double z() {
            return point.z();
        }

        /**
         * @param g - a given geo_location type.
         * @return the distance from this point to the 'g' point.
         */
        @Override
        public double distance(geo_location g) {
            return point.distance(g);
        }
    }
}