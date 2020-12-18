package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {

    Edge testingEdge;
    Edge copiedEdge;
    node_data source;
    node_data destination;

    @BeforeEach
    void setUp() {
        source = new Node();
        destination = new Node();
        testingEdge = new Edge(source,destination,101.5);
        copiedEdge = new Edge(testingEdge);
    }
    @Test
    void getSrc() {
        assertEquals(testingEdge.getSrc(),source.getKey());
        assertEquals(copiedEdge.getSrc(),source.getKey());
    }

    @Test
    void getSource() {
        assertEquals(testingEdge.getSource(),source);
        assertEquals(copiedEdge.getSource(),source);
    }

    @Test
    void getDest() {
        assertEquals(testingEdge.getDest(),destination.getKey());
        assertEquals(copiedEdge.getDest(),destination.getKey());
    }

    @Test
    void getDestination() {
        assertEquals(testingEdge.getDestination(),destination);
        assertEquals(copiedEdge.getDestination(),destination);
    }

    @Test
    void getWeight() {
        assertEquals(testingEdge.getWeight(),101.5);
        assertEquals(copiedEdge.getWeight(),101.5);
    }

    @Test
    void setWeight() {
        copiedEdge.setWeight(101.6);
        assertNotEquals(testingEdge.getWeight(),copiedEdge.getWeight());
        assertEquals(101.6,copiedEdge.getWeight());
    }

    @Test
    void getInfo() {
        assertEquals(testingEdge.getInfo(),"");
        assertEquals(copiedEdge.getInfo(),"");
    }

    @Test
    void setInfo() {
        testingEdge.setInfo("hello!");
        assertNotEquals(testingEdge.getInfo(),copiedEdge.getInfo());
        assertEquals("hello!",testingEdge.getInfo());
    }

    @Test
    void getTag() {
        assertEquals(testingEdge.getTag(), Color.RED.getRGB());
        assertEquals(copiedEdge.getTag(), Color.RED.getRGB());
    }

    @Test
    void setTag() {
        testingEdge.setTag(43);
        assertEquals(testingEdge.getTag(),new Color(43).getRGB());
        assertNotEquals(testingEdge.getTag(),copiedEdge.getTag());
    }
}
