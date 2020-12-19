package api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_AlgoTest {
    static dw_graph_algorithms graph_algorithms;
    static directed_weighted_graph graph;

    @BeforeAll
    static void smallGraph() {
        graph_algorithms = new DWGraph_Algo();
        graph = new DWGraph_DS();
        for(int i = 0 ; i < 6 ; i++) {
            node_data new_node = new Node();
            graph.addNode(new_node);
        }
        graph.connect(0,1,1.2);
        graph.connect(1,0,4);
        graph.connect(1,2,4.32);
        graph.connect(1,5,7.11);
        graph.connect(2,0,5.1);
        graph.connect(2,4,4.9);
        graph.connect(4,3,12.4);
        graph.connect(3,2,1.06);
        graph.connect(5,2,6.1);

    }
    @Test
    void init() {
        graph_algorithms.init(graph);
    }

    @Test
    void getGraph() {
    assertEquals(graph,graph_algorithms.getGraph());
    }

    @Test
    void copy() {
        graph_algorithms.init(graph);
        directed_weighted_graph copied = graph_algorithms.copy();
        assertNotSame(graph,copied); // check whether two graphs object do not refer to the same object.
        assertEquals(graph,copied);
    }

    @Test
    void isConnected() {
        graph_algorithms.init(graph);
        boolean actual = graph_algorithms.isConnected();
        assertTrue(actual);
        graph.removeNode(5);
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

    @Test
    void testEquals() {
    }
}