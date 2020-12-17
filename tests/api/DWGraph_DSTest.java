package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.util.HashSet;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class DWGraph_DSTest {
    directed_weighted_graph graph;
    dw_graph_algorithms graph_algo;

    @BeforeEach
    public void initial() {
       graph = new DWGraph_DS();
    }

    public void smallGraph() {
        for (int i = 0 ; i < 6 ; i++) {
            Node new_node = new Node();
            graph.addNode(new_node);
        }
        graph.connect(0,1,1.2);
        graph.connect(1,0,4);
        graph.connect(1,2,4.32);
        graph.connect(1,5,7.11);
        graph.connect(5,2,6.1);
        graph.connect(2,0,5.1);
        graph.connect(2,4,4.9);
        graph.connect(4,3,12.4);
        graph.connect(3,2,1.06);
    }

    @Test
    void getNode() {
        smallGraph();
        int actual = graph.getNode(4).getKey();
        int expected = 4;
        assertEquals(expected,actual);
        assertNull(graph.getNode(9));
    }

    @Test
    void getEdge() {
        smallGraph();
        double actual = graph.getEdge(0,1).getWeight();
        double expected = 1.2;
        assertEquals(expected,actual);
        assertNull(graph.getEdge(-1,0));
    }

    @Test
    void addNode() {
    smallGraph();
    node_data n6 = new Node();
    graph.addNode(n6);
    int actual = graph.getV().size();
    int expected = 7;
    assertEquals(actual,expected);
    node_data null_obj = null;
    graph.addNode(null_obj);
    assertEquals(actual,expected);
    }

    @Test
    void hasNode() {
      boolean actual = ((DWGraph_DS)graph).hasNode(9);
      Assertions.assertFalse(actual);
    }

    @Test
    void connect() {
        node_data n1 = new Node();
        node_data n2 = new Node();
        graph.addNode(n1);
        graph.addNode(n2);
        double negative_weight = (Math.random()*10) * -1;
        graph.connect(0,1,negative_weight);
        Assertions.assertNull(graph.getEdge(0,1));
        double positive_weight = (Math.random()*10);
        graph.connect(0,1,positive_weight);
        graph.connect(0,1,positive_weight);
        int expected = 3;
        Assertions.assertEquals(expected,graph.getMC());
    }

    @Test
    void getV() {
        int amount_of_nodes = 34;
        while(graph.nodeSize() < amount_of_nodes) {
            node_data new_node = new Node();
            graph.addNode(new_node);
        }
        Assertions.assertEquals(graph.nodeSize(),graph.getV().size());
    }

    @Test
    void getE() {
        smallGraph();
        HashSet<edge_data> edges = new HashSet<>(graph.getE(0));
        int actual_num_of_edges = 1;
        assertEquals(actual_num_of_edges,edges.size());
        graph.removeNode(0);
        HashSet<edge_data> empty = new HashSet<>(graph.getE(0));
        boolean flag = empty.isEmpty();
        assertTrue(flag);
    }

    @Test
    void removeNode() {
        smallGraph();
        graph.removeNode(0);
        int actual_size_of_nodes = graph.getV().size();
        int expected = 5;
        assertEquals(expected,actual_size_of_nodes);
        int actual_num_of_edges = graph.edgeSize();
        int expected_edges = 7;
        assertEquals(expected_edges,actual_num_of_edges);
    }

    @Test
    void removeEdge() {
        node_data n0 = new Node();
        node_data n1 = new Node();
        graph.addNode(n0);
        graph.addNode(n1);
        graph.removeEdge(0,1);
        int expected = 0;
        Assertions.assertEquals(expected,graph.edgeSize());
        graph.removeNode(0);
        graph.connect(0,1,23.1);
        int actual_num_of_nodes = 1;
        int expected_nodes = graph.getV().size();
        Assertions.assertEquals(actual_num_of_nodes,expected_nodes);
    }

    @Test
    void nodeSize() {
        smallGraph();
        int actual_num_of_nodes = graph.nodeSize();
        int expected = 6;
        Assertions.assertEquals(expected,actual_num_of_nodes);
    }

    @Test
    void edgeSize() {
        node_data n0 = new Node();
        node_data n1 = new Node();
        graph.addNode(n0);
        graph.addNode(n1);
        double positive_weight = (Math.random()*10);
        double negative_weight = ((Math.random()*10)* -1);
        graph.connect(0,1,positive_weight);
        graph.connect(0,0,positive_weight);
        graph.connect(0,1,positive_weight);
        graph.connect(0,-3,negative_weight);
        int expected = 1;
        Assertions.assertEquals(expected,graph.edgeSize());
    }

    @Test
    void getMC() {
        smallGraph();
        int before = graph.getMC();
        graph.connect(0,1,4.26);
        graph.connect(0,1,4.26);
        graph.connect(0,1,4.26);
        graph.connect(0,1,4.26);
        int after = graph.getMC() - 1;
        assertEquals(before,after);
    }
}