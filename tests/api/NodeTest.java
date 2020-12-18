package api;

import api.Node.GeoLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    node_data testingNode;

    @BeforeEach
    void setUp() {
        testingNode = new Node();
    }

    @Test
    void getKey() {
        assertEquals(0,testingNode.getKey());
        node_data anotherNode = new Node(50,new GeoLocation(0,0,0));
        assertEquals(50,anotherNode.getKey());
        node_data copiedNode = new Node(anotherNode);
        assertEquals(50,copiedNode.getKey());
    }

    @Test
    void getLocation() {
        geo_location location = testingNode.getLocation();
        double x = location.x();
        double y = location.y();
        double z = location.z();
        assertEquals(x,0.0);
        assertEquals(y,0.0);
        assertEquals(z,0.0);
        node_data anotherNode = new Node(51,new GeoLocation(7.9,90.3,4.7));
        node_data copiedNode = new Node(anotherNode);
        assertEquals(anotherNode.getLocation(),copiedNode.getLocation());
    }

    @Test
    void setLocation() {
        node_data myNode = new Node();
        myNode.setLocation(new GeoLocation(9.8,4.5,20.1));
        assertEquals(myNode.getLocation(),new GeoLocation(9.8,4.5,20.1));
    }

    @Test
    void getWeight() {
    }

    @Test
    void setWeight() {
    }

    @Test
    void getInfo() {
    }

    @Test
    void setInfo() {
    }

    @Test
    void getTag() {
    }

    @Test
    void setTag() {
    }

}
