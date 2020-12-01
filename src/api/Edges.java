package api;
import java.awt.Color;

    public class Edges implements edge_data {
    int src;
    int dest;
    double w;
    node_data source;
    node_data destination;
    String info;
    Color tag;


    public Edges(node_data source , node_data desti , double weight) {
        src = source.getKey();
        dest = desti.getKey();
        w = weight;
        this.source = source;
        this.destination = desti;
        this.tag = Color.RED;
        this.info = "";
    }

    public Edges(Edges edge) {
        src = edge.getSrc();
        dest = edge.getDest();
        w = edge.getWeight();
        source = edge.getSource();
        destination = edge.getDestination();
        info = edge.getInfo();
        tag = new Color(edge.getTag());
    }


    @Override
    public int getSrc() {return source.getKey();}
    public node_data getSource() { return source;}
    @Override
    public int getDest() {return destination.getKey();}
    public node_data getDestination() { return destination;}
    @Override
    public double getWeight() {return w;}
    public void setWeight(double w) {
        this.w = w;}
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
                ", dest=" + destination.getKey() +
                ", weight=" + w +
                ", info='" + info + '\'' +
                ", tag=" + tag +
                '}';
    }
    
}
