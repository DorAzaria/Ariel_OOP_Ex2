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

    @Override
    public int getSrc() {return source.getKey();}
    @Override
    public int getDest() {return dest.getKey();}
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
