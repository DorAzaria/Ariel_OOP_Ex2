import api.*;

import java.awt.Color;
import java.util.HashSet;

public class yeezeTest {

    public static void main(String[] args) {
     directed_weighted_graph graph = new DWGraph_DS();
        node_data n1 = new NodeData();
        node_data n2 = new NodeData();
        node_data n3 = new NodeData();
        node_data n4 = new NodeData();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(1,2,0);
        dw_graph_algorithms tester = new DWGraph_Algo();
        tester.init(graph);
        System.out.println(tester.isConnected());



       System.out.println("---------------------------");
       System.out.println("---------------------------");




    }
}

