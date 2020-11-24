package api;
import java.awt.Color;
public class EdgeData implements edge_data {
    node_data source;
    node_data dest;
    double weight;
    String info;
    Color tag;


    public EdgeData (node_data source , node_data dest , double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
        this.tag = Color.RED;
    }

    public EdgeData(EdgeData edge) {
        source = edge.getSource();
        dest = edge.getDestination();
        weight = edge.getWeight();
        info = edge.getInfo();
        tag = new Color(edge.getTag());
    }


    @Override
    public int getSrc() {return source.getKey();}
    public node_data getSource() { return source;}
    @Override
    public int getDest() {return dest.getKey();}
    public node_data getDestination() { return dest;}
    @Override
    public double getWeight() {return weight;}
    public void setWeight(double w) {weight = w;}
    @Override
    public String getInfo() {return info;}
    @Override
    public void setInfo(String s) { info = s;}
    @Override
    public int getTag() {return tag.getRGB();}
    @Override
    public void setTag(int t) {tag = new Color(t);}

    @Override
    public String toString() {
        return "EdgeData{" +
                "source=" + source.getKey() +
                ", dest=" + dest.getKey() +
                ", weight=" + weight +
                ", info='" + info + '\'' +
                ", tag=" + tag +
                '}';
    }

    private class EdgeLocation implements edge_location {
        edge_data edge;

        @Override
        public edge_data getEdge() {
            return edge;
        }

        @Override
        public double getRatio() {
           return edge.getWeight();
        }
    }
}
