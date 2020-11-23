package api;
public class EdgeData implements edge_data {
    node_data source;
    node_data dest;
    double weight;
    String info;
    int tag;

    public EdgeData (node_data source , node_data dest , double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int getSrc() {return source.getKey();}
    @Override
    public int getDest() {return dest.getKey();}
    @Override
    public double getWeight() {return weight;}
    @Override
    public String getInfo() {return info;}
    @Override
    public void setInfo(String s) { info = s;}
    @Override
    public int getTag() {return tag;}
    @Override
    public void setTag(int t) {tag = t;}


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
