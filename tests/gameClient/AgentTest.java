package gameClient;

import api.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AgentTest {

    static Agent testingAgent;
    static directed_weighted_graph graph;
    static DWGraph_Algo graphAlgo;
    static String json;

    @BeforeEach
    void setUp() {
        graphAlgo = new DWGraph_Algo();
        graphAlgo.load("data/A0");
        graph = graphAlgo.getGraph();
        testingAgent = new Agent(graph,0);
        json = "{\"Agent\":{\"src\":8,\"pos\":\"35.20319591121872,32.1031462,0.0\",\"id\":0,\"dest\":-1,\"value\":5,\"speed\":1}}";
        testingAgent.update(json);
    }

    @Test
    void update() {
        assertEquals(8,testingAgent.getSrcNode());
        assertEquals(35.20319591121872,testingAgent.getLocation().x());
        assertEquals(32.1031462,testingAgent.getLocation().y());
        assertEquals(0.0,testingAgent.getLocation().z());
        assertEquals(0,testingAgent.getID());
        assertEquals(-1,testingAgent.getNextNode());
        assertEquals(5,testingAgent.getPoints());
        assertEquals(1,testingAgent.getSpeed());
    }

    @Test
    void getSrcNode() {
        assertEquals(8,testingAgent.getSrcNode());
    }

    @Test
    void setNextNode() {
        assertEquals(-1,testingAgent.getNextNode());
        testingAgent.setNextNode(2);
        assertNotEquals(2,testingAgent.getNextNode());
        testingAgent.setNextNode(9);
        assertEquals(9,testingAgent.getNextNode());
    }

    @Test
    void setCurrNode() {
        assertEquals(8,testingAgent.getSrcNode());
        testingAgent.setCurrNode(1);
        assertEquals(1,testingAgent.getSrcNode());
    }

    @Test
    void getID() {
        assertEquals(0,testingAgent.getID());
    }

    @Test
    void getLocation() {
        geo_location geo = testingAgent.getLocation();
        geo_location geo2 = graph.getNode(testingAgent.getSrcNode()).getLocation();
        assertEquals(geo.x(),geo2.x());
        assertEquals(geo.y(),geo2.y());
        assertEquals(geo.z(),geo2.z());

    }

    @Test
    void getPoints() {
        assertEquals(5,testingAgent.getPoints());
    }

    @Test
    void getNextNode() {
        assertEquals(-1,testingAgent.getNextNode());
        testingAgent.setNextNode(9);
        assertEquals(9,testingAgent.getNextNode());
    }

    @Test
    void getSpeed() {
        assertEquals(1,testingAgent.getSpeed());
    }

    @Test
    void setSpeed() {
        assertEquals(1,testingAgent.getSpeed());
        testingAgent.setSpeed(5);
        assertEquals(5,testingAgent.getSpeed());
    }
}
