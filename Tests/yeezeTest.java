import api.*;

import java.awt.Color;
public class yeezeTest {

    public static void main(String[] args) {
        DWGraph_DS graph = new DWGraph_DS();
        for (int i = 0; i < 4; i++) {
            graph.addNode((new NodeData()));
        }
        for(int i = 1 ; i <= 4 ; i++) {
            for(int j = 1 ; j <= 4 ; j++) {
                graph.connect(i,j,(double)i*j*1.3); // strongly connection
            }
        }
        dw_graph_algorithms algo = new DWGraph_Algo();
        algo.init(graph);
        System.out.println(algo.isConnected());
        directed_weighted_graph graph2 = algo.copy();
        System.out.println(graph2.nodeSize() + " " + graph.nodeSize());
        System.out.println(graph2.getMC() + " " + graph.getMC());
        System.out.println(graph2.edgeSize() + " " + graph.edgeSize());

    }
}

