package api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_AlgoTest {
    directed_weighted_graph graph;
    dw_graph_algorithms graph_algorithms;

    @BeforeEach
    void setUp() {
        this.graph = new DWGraph_DS();
        this.graph_algorithms = new DWGraph_Algo();
    }
    @Test
    void init() {
        graph_algorithms.init(graph);
        Assertions.assertEquals(graph, graph_algorithms.getGraph());
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
    void getGraph() {
        node_data new_node = new Node();
        graph.addNode(new_node);
        graph_algorithms.init(graph);
        int expected = 0;
        int actual = graph_algorithms.getGraph().getNode(0).getKey();
        Assertions.assertEquals(expected,actual);
        Assertions.assertEquals(graph,graph_algorithms.getGraph());
    }

    @Test
    void copy() {
        smallGraph();
        graph_algorithms.init(graph);
        directed_weighted_graph copied = graph_algorithms.copy();
        assertEquals(copied,graph);
    }

    @Test
    void isConnected() {
        smallGraph();
        graph.removeNode(5);
        graph_algorithms.init(graph);
        Assertions.assertTrue(graph_algorithms.isConnected());
        graph.removeNode(5);
        Assertions.assertFalse(graph_algorithms.isConnected());
        int expected = 5;
        Assertions.assertEquals(expected,graph_algorithms.getGraph().nodeSize());
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}