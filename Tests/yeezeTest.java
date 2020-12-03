import api.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class yeezeTest {

    public static void main(String[] args) throws FileNotFoundException {
     directed_weighted_graph graph = new DWGraph_DS();
        node_data n1 = new Nodes();
        node_data n2 = new Nodes();
        node_data n3 = new Nodes();
        node_data n4 = new Nodes();
        graph.addNode(n1);
        graph.addNode(n2);
        graph.addNode(n3);
        graph.addNode(n4);
        graph.connect(0,1,11.4);
        graph.connect(1,0,1.3);
        graph.connect(0,3,2.6);
        graph.connect(3,1,17.5);
        graph.connect(2,1,3.16);
        graph.connect(3,2,4.8);
        dw_graph_algorithms tester = new DWGraph_Algo();
        tester.init(graph);
       System.out.println(tester.shortestPathDist(1,0));

   }
}

