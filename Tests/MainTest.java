import Server.Game_Server_Ex2;
import api.*;
import gameClient.*;
import gameClient.gui.ourFrame;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MainTest {
    private static ourFrame _win, log;
    private static Arena _ar;
    private static long playerID;
    private static int  num_level;
    private static int  j;

    public static void main(String[] args) {
        num_level = 0;
        j = 0;
        while(num_level < 24) {
            if(num_level == j) {
                num_level++;
                test();
            }
        }

    }

    public static void test() {
        game_service game = Game_Server_Ex2.getServer(num_level); // you have [0,23] games
        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
        init(game);
        game.startGame();
        _win.setTitle("Ex2 - OOP: Pokemons! ");
        int ind=0;
        long dt=100;

        while(game.isRunning()) {
            moveAgants(game, gg);
            try {
                if(ind%2==0) {_win.repaint();}
                Thread.sleep(dt);
                ind++;
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        String res = game.toString();
        System.out.println(res);
        _win.setVisible(false);
        j++;
    }
    /**
     * Moves each of the agents along the edge,
     * in case the agent is on a node the next destination (next edge) is chosen (randomly).
     * @param game
     * @param gg
     * @param
     */
    private static void moveAgants(game_service game, directed_weighted_graph gg) {
        String lg = game.move();
        List<Agent> log = Arena.getAgents(lg, gg);
        _ar.setAgents(log);
        //ArrayList<OOP_Point3D> rs = new ArrayList<OOP_Point3D>();
        String fs =  game.getPokemons();
        List<Pokemon> ffs = Arena.getPokemons(fs);
        _ar.setPokemons(ffs);
        for (Agent ag : log) {
            int id = ag.getID();
            int dest = ag.getNextNode();
            int src = ag.getSrcNode();
            double v = ag.getKey();
            if (dest == -1) {
                dest = nextNode(gg, src);
                game.chooseNextEdge(ag.getID(), dest);
            }
        }
    }
    /**
     * a very simple random walk implementation!
     * @param g
     * @param src
     * @return
     */
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
    private static void init(game_service game) {
        String fs = game.getPokemons();
        directed_weighted_graph gg = game.getJava_Graph_Not_to_be_used();
        _ar = new Arena();
        _ar.setGraph(gg);
        _ar.setPokemons(Arena.getPokemons(fs));
        _ar.setGame(game);
        _win = new ourFrame("test Ex2");
        _win.setSize(1000, 700);
        _win.update(_ar);
        _win.show();
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject ttt = line.getJSONObject("GameServer");
            int rs = ttt.getInt("agents");
            int src_node = 0;  // arbitrary node, you should start at one of the pokemon
            ArrayList<Pokemon> cl_fs = Arena.getPokemons(game.getPokemons());
            for (Pokemon cl_f : cl_fs) {
                Arena.updateEdge(cl_f, gg);
            }
            for(int a = 0;a<rs;a++) {
                int ind = a%cl_fs.size();
                Pokemon c = cl_fs.get(ind);
                int nn = c.getEdges().getDest();
                if(c.getType()<0 ) {nn = c.getEdges().getSrc();}
                game.addAgent(nn);
            }
        }
        catch (JSONException e) {e.printStackTrace();}
    }

}

