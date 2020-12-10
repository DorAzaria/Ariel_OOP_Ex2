package gameClient;
import Server.Game_Server_Ex2;
import api.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gameClient.gui.ourFrame;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Ex2 implements Runnable{
    private static ourFrame Frame;
    private static Arena ManageGame;
    private static long playerID;
    private static int  num_level;
    private  static directed_weighted_graph graph;
    private  static dw_graph_algorithms graph_algo = new DWGraph_Algo();
    static long dt;
    static Thread client = new Thread(new Ex2());

    public static void main(String[] a){
        login();
        client.start();
    }

    @Override
    public void run() {
        game_service game = Game_Server_Ex2.getServer(num_level); // you have [0,23] games
        loadGraph(game.getGraph());
        init(game);
        game.startGame();
        Frame.setTitle("Ex2 - OOP: Pokemons! ,  Game Number: " + num_level);
        int ind = 0;
        dt = 100;
        while(game.isRunning()) {
            moveAgants(game);
            try {
                if(ind % 1 == 0) {
                    Frame.repaint();}
                Thread.sleep(dt);
                ind++;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        String res = game.toString();
        System.out.println(res);
        System.exit(0);
    }

    /**
     * Moves each of the agents along the edge,
     * in case the agent is on a node the next destination (next edge) is chosen (randomly).
     * @param game
     * @param
     */
    private static void moveAgants(game_service game) {
        String lg = game.move();
        List <Agent> log = Arena.getAgents(lg, graph);
        ManageGame.setAgents(log);
        String fs =  game.getPokemons();
        List<Pokemon> ffs = Arena.getPokemons(fs);
        ManageGame.setPokemons(ffs);
        graph_algo.init(graph);

        for (Agent ag : log) {
            int id = ag.getID();
            int dest = ag.getNextNode();
            double v = ag.getKey();
            if(dest == -1) {
                int desti = Target(ag,ffs);
                    if(desti != -1) {
                        game.chooseNextEdge(ag.getID(),desti);
                        System.out.println("Agent: " + id + ", val: " + v + "   turned to node: " + dest);
                    }else {
                        game.chooseNextEdge(ag.getID(),nextNode(graph,ag.getSrcNode()));
                        System.out.println("Agent: " + id + ", val: " + v + "   turned to node: " + dest);
                    }
                }
          }

    }
    private static int nextNode(directed_weighted_graph g, int src) {
        int ans = -1;
        Collection<edge_data> ee = g.getE(src);
        Iterator<edge_data> itr = ee.iterator();
        int s = ee.size();
        int r = (int)(Math.random()*s);
        int i=0;
        while(i<r) {itr.next();i++;}
        ans = itr.next().getDest();
        return ans;
    }

    private static int Target(Agent bond , List<Pokemon> pokemons) {
        double minDest = Double.MAX_VALUE;
        Pokemon target = null;
        for (Pokemon p : pokemons) {
            Arena.updateEdge(p,graph);
                double minTemp = graph_algo.shortestPathDist(bond.getSrcNode(), p.getEdges().getDest());
                if (minDest > minTemp && minTemp != -1) {
                    minDest = minTemp;
                    target = p;
                }
        }
        ArrayList<node_data> path = null;
        if(target != null) {
            System.out.println("/////");
            System.out.println("pokemon source: " + target.getEdges().getSrc() + " dest: "  + target.getEdges().getDest());
            System.out.println("agent source: " + bond.getSrcNode());

            if(bond.getSrcNode() == target.getEdges().getDest()) {
                bond.setCurrentPokemon(target);
                return target.getEdges().getSrc();
            }
            else {
                path = new ArrayList<>(graph_algo.shortestPath(bond.getSrcNode(), target.getEdges().getDest()));
            }

        }
        if(path == null) {
            return -1;
        }
        return path.get(1).getKey();
    }

    private void loadGraph(String str) {
        try {
            GsonBuilder builder = new GsonBuilder()
                    .registerTypeAdapter(directed_weighted_graph.class, new graphDeserialization());
            Gson gson = builder.create();
            graph = gson.fromJson(str, directed_weighted_graph.class);
        }
        catch (Exception f) {
            f.printStackTrace();
        }
    }
    private void init(game_service game) {
        String fs = game.getPokemons();
        ManageGame = new Arena();
        ManageGame.setGraph(graph);
        ManageGame.setPokemons(Arena.getPokemons(fs));
        ManageGame.setGame(game);
        /// frame init
        Frame = new ourFrame();
        Frame.setSize(1000, 700);
        Frame.update(ManageGame);
        Frame.setVisible(true);
        String info = game.toString();
        try {
            // info about the game scenario
            JSONObject line = new JSONObject(info);
            JSONObject ttt = line.getJSONObject("GameServer");
            int num_of_agents = ttt.getInt("agents");
            System.out.println(info);
            System.out.println(game.getPokemons());
            ArrayList <Pokemon> pokemons = Arena.getPokemons(game.getPokemons());
            for (Pokemon pk : pokemons) {
                Arena.updateEdge(pk, graph);
            }
                for(int a = 0; a <= num_of_agents; a++) {
                int ind = a % pokemons.size();
                Pokemon c = pokemons.get(ind);
                int pos_on_graph = c.getEdges().getDest();
                if(c.getType() < 0 ) {
                    pos_on_graph = c.getEdges().getSrc();
                }
                game.addAgent(pos_on_graph);
            }
        }
        catch (JSONException e) {e.printStackTrace();}
    }
    private static void login(){
        ourFrame frame = new ourFrame();
        frame.setBounds(200, 0, 500, 500);
        try {
            String id= JOptionPane.showInputDialog(frame, "Please insert ID");

            String level = JOptionPane.showInputDialog(frame, "Please insert level number [0-23]");

            playerID = Long.parseLong(id);
            num_level = Integer.parseInt(level);

            if (num_level > 23 || num_level < 0 )
                throw new RuntimeException();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input.\nPlaying default game", "Error",
                    JOptionPane.ERROR_MESSAGE);
            num_level = 0;
        }
    }
}