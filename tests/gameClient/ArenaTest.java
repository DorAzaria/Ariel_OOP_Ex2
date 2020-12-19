package gameClient;

import Server.Game_Server_Ex2;
import api.*;
import gameClient.util.Point3D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Policy;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArenaTest {
    static Arena manageGame;
    static Agent testingAgent;
    static directed_weighted_graph graph;
    static DWGraph_Algo graphAlgo;
    static String json;

    @BeforeEach
    void setUp() {
        manageGame = new Arena();
        graphAlgo = new DWGraph_Algo();
        graphAlgo.load("data/A0");
        graph = graphAlgo.getGraph();

    }

    @Test
    void setPokemons() {
    edge_data e1 = graph.getEdge(0,1);
    Pokemon p1 = new Pokemon(new Point3D(3.3,1.6,0),1,15,e1);
    Pokemon p2 = new Pokemon(new Point3D(3.13,14.6,0),1,15,e1);
    List<Pokemon> p_list = new LinkedList<>();
    p_list.add(p1); p_list.add(p2);
    manageGame.setPokemons(p_list);
    List<Pokemon> actual = manageGame.getPokemons();
    assertEquals(actual,p_list);

    }

    @Test
    void setAgents() {
    Agent a1 = new Agent(graph,0);
    List<Agent> a_list = new LinkedList<>();
    a_list.add(a1);
    manageGame.setAgents(a_list);
    List<Agent> actual = manageGame.getAgents();
    assertEquals(actual,a_list);
    }

    @Test
    void setGraph() {
        manageGame.setGraph(graph);
        assertEquals(graph,manageGame.getGraph());
    }
    @Test
    void getAgents() {
        setAgents();
        List<Agent> actual = manageGame.getAgents();
        int actual_source_node = actual.get(0).getSrcNode();
        int expected = 0;
        assertEquals(actual_source_node,expected);
    }

    @Test
    void getPokemons() {
        setPokemons();
        List<Pokemon> actual = manageGame.getPokemons();
        double actual_weight = actual.get(0).getEdges().getWeight();
        double expected_weight = 1.4004465106761335;
        assertEquals(actual_weight,expected_weight);
    }

    @Test
    void getGraph() {
        manageGame.setGraph(graph);
        assertEquals(graph,manageGame.getGraph());
        int expected_num_of_nodes = graph.getV().size();
        int actual_num_of_nodes = manageGame.getGraph().getV().size();
        assertEquals(expected_num_of_nodes,actual_num_of_nodes);
    }

    @Test
    void setGame() {

    }

    @Test
    void getGame() {
        game_service game = Game_Server_Ex2.getServer(0);
        manageGame.setGame(game);
        assertEquals(game,manageGame.getGame());
    }

    @Test
    void testGetAgents() {
        game_service game = Game_Server_Ex2.getServer(0);
        String agents = game.getAgents();


    }

    @Test
    void testGetPokemons() {

    }

    @Test
    void updateEdge() {
    }

    @Test
    void w2f() {
    }

    @Test
    void valueOfEdge() {
    }

    @Test
    void getBestValue() {
    }

    @Test
    void getPokemonsOnEdge() {
    }

    @Test
    void computeStuckProblem() {
    }
}
