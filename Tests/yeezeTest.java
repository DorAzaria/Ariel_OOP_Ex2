import api.*;

import java.awt.Color;
public class yeezeTest {

    public static void main(String[] args) {
        DWGraph_DS graph = new DWGraph_DS();
        for (int i = 0; i < 4; i++) {
            graph.addNode((new NodeData()));
        }
        graph.connect(1, 2, 3.2);
        graph.connect(1, 3, 4.1);
        graph.connect(3, 2, 12.1);
        graph.connect(3, 4, 1.18);
        graph.connect(4, 3, 2.18);

        graph.removeNode(4);
        graph.getE(3).stream().forEach(x-> System.out.println(x));
    }
}

