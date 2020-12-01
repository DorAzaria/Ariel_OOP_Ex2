package api;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class importGame {
    ArrayList<Edges> Edges;
    ArrayList<Nodes> Nodes;

    public importGame() {
        Edges = new ArrayList<>();
        Nodes = new ArrayList<>();
    }

    public List<Nodes> getNodes() {
        return Nodes;
    }

    public List<Edges> getEdges() {
        return Edges;
    }
}
