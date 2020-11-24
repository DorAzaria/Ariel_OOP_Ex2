import api.*;

import java.awt.Color;
import java.util.HashSet;

public class yeezeTest {

    public static void main(String[] args) {
        DWGraph_DS graph = new DWGraph_DS();
        for (int i = 0; i < 4; i++) {
            graph.addNode((new NodeData()));
        }
        graph.connect(1,2,9.5);
        graph.connect(1,3,9.5);
        graph.connect(2,1,9.5);
        graph.connect(3,2,9.5);
        graph.connect(3,4,9.5);
        graph.connect(4,3,9.5);

        dw_graph_algorithms algo = new DWGraph_Algo();
        algo.init(graph);
        System.out.println(algo.isConnected());
    }
}

