import api.*;

import java.awt.Color;
import java.io.IOException;
import java.util.HashSet;

public class yeezeTest {

    public static void main(String[] args) throws IOException {
     directed_weighted_graph graph = new DWGraph_DS();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(1,2,11.4);
        graph.connect(2,1,1.3);
        graph.connect(1,4,2.6);
        graph.connect(4,2,17.5);
        graph.connect(3,2,3.16);
        graph.connect(4,3,4.8);
        dw_graph_algorithms tester = new DWGraph_Algo();
        tester.init(graph);
        tester.save("Boaz-ben-zona.json");



    }
}

