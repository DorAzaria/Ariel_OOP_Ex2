package gameClient;

import api.Edge;
import api.Node;
import gameClient.util.Point3D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    Pokemon testingPokemon;
    Point3D point;
    Node source, destination;
    Edge edge;

    @BeforeEach
    void setUp() {
        point = new Point3D(11.2,22.3,44.5);
        source = new Node(0);
        destination = new Node(1);
        edge = new Edge(source,destination,101.5);
        testingPokemon = new Pokemon(point,1,15,edge);
    }

    @Test
    void getEdges() {
        assertEquals(testingPokemon.getEdges(),edge);
        assertNotEquals(testingPokemon.getEdges(),new Edge(source,destination,39.1));
    }

    @Test
    void setEdges() {
        assertEquals(testingPokemon.getEdges(),edge);
        testingPokemon.setEdges(new Edge(source,destination,39.1));
        assertNotEquals(testingPokemon.getEdges(),edge);
    }

    @Test
    void getLocation() {
        assertEquals(testingPokemon.getLocation(),point);
        assertNotEquals(testingPokemon.getLocation(),new Point3D(9.3,2.7,10.3));
    }

    @Test
    void getType() {
        assertEquals(testingPokemon.getType(),1);
        Pokemon p1 = new Pokemon(point,-1,89.3,edge);
        assertEquals(p1.getType(),-1);
    }

    @Test
    void getValue() {
        assertEquals(testingPokemon.getValue(),15);
        Pokemon p1 = new Pokemon(point,-1,89.3,edge);
        assertEquals(p1.getValue(),89.3);
    }

    @Test
    void getDest() {
        assertEquals(edge.getDest(),testingPokemon.getDest());
        edge = new Edge(source,new Node(5),10.9);
        assertNotEquals(edge.getDest(),testingPokemon.getDest());
    }

    @Test
    void setDistance() {
        assertNotNull(testingPokemon.getDistance());
        testingPokemon.setDistance(9.8);
        assertEquals(9.8,testingPokemon.getDistance());
    }

    @Test
    void getDistance() {
        assertNotNull(testingPokemon.getDistance());
        assertEquals(0.0,testingPokemon.getDistance());
    }
}
