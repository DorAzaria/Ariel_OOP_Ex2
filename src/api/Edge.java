package api;
import java.awt.Color;

/**
 * This class represents the set of operations applicable on a
 * directional edge(src,dest) in a (directional) weighted graph.
 */
public class Edge implements edge_data {
    int src;
    int dest;
    double w;
    node_data source;
    node_data destination;
    String info;
    Color tag;

    /**
     * a parametric constructor
     * @param source a node_data type (the source of the edge)
     * @param desti  a node_data type (the destination of the edge)
     * @param weight a double type
     */
    public Edge(node_data source, node_data desti, double weight) {
        src = source.getKey();
        dest = desti.getKey();
        w = weight;
        this.source = source;
        this.destination = desti;
        this.tag = Color.RED;
        this.info = "";
    }

    /**
     * a copy constructor.
     * @param edge an Edges type
     */
    public Edge(Edge edge) {
        src = edge.getSrc();
        dest = edge.getDest();
        w = edge.getWeight();
        source = edge.getSource();
        destination = edge.getDestination();
        info = edge.getInfo();
        tag = new Color(edge.getTag());
    }

    /**
     * @return The id of the source node of this edge.
     */
    @Override
    public int getSrc() { return source.getKey(); }

    /**
     * @return a node_data type of the source node of this edge.
     */
    public node_data getSource() { return source; }

    /**
     * @return The id of the destination node of this edge
     */
    @Override
    public int getDest() { return destination.getKey(); }

    /**
     * @return a node_data type of the destination node of this edge.
     */
    public node_data getDestination() { return destination; }

    /**
     * @return the weight of this edge (positive value).
     */
    @Override
    public double getWeight() { return w; }

    /**
     * @param w - double type - allows changing the weight.
     */
    public void setWeight(double w) { this.w = w; }

    /**
     * @return the remark (meta data) associated with this edge.
     */
    @Override
    public String getInfo() { return info; }

    /**
     * Allows changing the remark (meta data) associated with this edge.
     *
     * @param s - a String type.
     */
    @Override
    public void setInfo(String s) { info = s; }

    /**
     * Temporal data (aka color: e,g, white, gray, black)
     * which can be used be algorithms
     *
     * @return a color tag (int value).
     */
    @Override
    public int getTag() { return tag.getRGB(); }

    /**
     * This method allows setting the "tag" value for temporal marking an edge - common
     * practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) { tag = new Color(t); }

    /**
     * @return a string that represents all the data-fields of this class.
     */
    @Override
    public String toString() {
        return "EdgeData{" +
                "source=" + source.getKey() +
                ", dest=" + destination.getKey() +
                ", weight=" + w +
                ", info='" + info + '\'' +
                ", tag=" + tag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (src != edge.src) return false;
        if (dest != edge.dest) return false;
        if (Double.compare(edge.w, w) != 0) return false;
        if (!source.equals(edge.source)) return false;
        if (!destination.equals(edge.destination)) return false;
        if (!info.equals(edge.info)) return false;
        return tag.equals(edge.tag);
    }

}
