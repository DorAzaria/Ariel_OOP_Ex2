package gameClient;
import api.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents a multi Agents Arena which move on a graph - grabs Pokemons and avoid the Zombies.
 * @author boaz.benmoshe
 *
 */
public class Arena {
	public static final double EPS1 = 0.001, EPS2=EPS1*EPS1, EPS=EPS2;
	private static directed_weighted_graph graph;
	private List<Agent> agents;
	private List<Pokemon> pokemons;
	private List<String> info;
	private static Point3D MIN = new Point3D(0, 100,0);
	private static Point3D MAX = new Point3D(0, 100,0);
	private static game_service game;

	public Arena() {
	}

	public void setPokemons(List<Pokemon> p) {
		if(pokemons != null) {
			for(Pokemon poke : p) {
				int i = p.indexOf(poke);
				if(i != -1 && pokemons.get(i).isTarget()) {
					p.get(i).setTarget();
				}
			}
		}
		pokemons = p;
	}
	public void setAgents(List<Agent> a) {
		agents = a;
	}

	public void setGraph(directed_weighted_graph g) {
		graph = g;
	}

	public List<Agent> getAgents() {return agents;}

	public List<Pokemon> getPokemons() {return pokemons;}

	public directed_weighted_graph getGraph() {return graph;}

	public List<String> getInfo() {return info;}

	public void setGame(game_service g){game =g;}

	public game_service getGame(){return game;}

	/**
	 * This method return a collection of agents objects by taking them from json file
	 	that represent the actual status of them
	 * @param status string that representing status of all the agents
	 * @param graph the graph that the agents will insert
	 * @return collection of all the agents objects
	 */

	public static List<Agent> getAgents(String status, directed_weighted_graph graph) {
		ArrayList<Agent> agents_arraylist = new ArrayList<Agent>();
		try {
			JSONObject agents_status = new JSONObject(status);
			JSONArray current_agents = agents_status.getJSONArray("Agents");
			for(int i = 0 ; i < current_agents.length() ; i++) {
				Agent a = new Agent(graph,0);
				a.update(current_agents.get(i).toString());
				agents_arraylist.add(a);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return agents_arraylist;
	}

	/**
	 * This method return a collection of pokemon's objects by taking them from a json file
	 * @param actual string that represent all the pokemon's
	 * @return collection of all pokemon's objects
	 */
	public static ArrayList<Pokemon> getPokemons (String actual) {
		ArrayList<Pokemon> pokemons_arraylist = new  ArrayList<Pokemon>();
		try {
			JSONObject pokemons_status = new JSONObject(actual);
			JSONArray actual_pokemons = pokemons_status.getJSONArray("Pokemons");

			for(int i = 0; i < actual_pokemons.length() ; i++) {
				JSONObject pokemon = actual_pokemons.getJSONObject(i);
				JSONObject pk = pokemon.getJSONObject("Pokemon");
				int t = pk.getInt("type");
				double v = pk.getDouble("value");
				String p = pk.getString("pos");
				Pokemon f = new Pokemon(new Point3D(p), t, v, 0, null);
				updateEdge(f,graph);
				pokemons_arraylist.add(f);
			}
		}
		catch (JSONException e) {e.printStackTrace();}
		return pokemons_arraylist;
	}

	/**
	 * This method update the edges by status of the pokemon's
	 * @param pk pokemon object
	 * @param graph
	 */
	public static void updateEdge(Pokemon pk, directed_weighted_graph graph) {

		for (node_data runner : graph.getV()) {
			for (edge_data edge : graph.getE(runner.getKey())) {
				boolean flag = isOnEdge(pk.getLocation(), edge, pk.getType(), graph);
				if (flag) {
					pk.setEdges(edge);
				}
			}
		}
	}
	/**
	 * This method checks whether pokemon object is on the edge
	 * @param p the location of the pokemon by coordinate
	 * @param e the edge that the pokemon stays on
	 * @param type the status of the location of the pokemon (if his moving up or down)
	 * @param g the graph
	 * @return true if and only if this pokemon object are in the edge
	 */
	private static boolean isOnEdge(geo_location p, edge_data e, int type, directed_weighted_graph g) {
		int src = g.getNode(e.getSrc()).getKey();
		int dest = g.getNode(e.getDest()).getKey();
		if(type < 0 && dest > src) {
			return false;
		}
		if(type > 0 && src > dest) {
			return false;
		}
		return isOnEdge(p,src, dest, g);
	}
	private static boolean isOnEdge(geo_location p, int s, int d, directed_weighted_graph g) {
		geo_location src = g.getNode(s).getLocation();
		geo_location dest = g.getNode(d).getLocation();
		return isOnEdge(p,src,dest);
	}
	private static boolean isOnEdge(geo_location p, geo_location src, geo_location dest ) {
		boolean ans = false;
		double dist = src.distance(dest);
		double d1 = src.distance(p) + p.distance(dest);
		if(dist>d1-EPS2) {
			ans = true;
		}
		return ans;
	}

	public static Range2Range w2f(directed_weighted_graph g, Range2D frame) {
		Range2D world = GraphRange(g);
		Range2Range ans = new Range2Range(world, frame);
		return ans;
	}

	private static Range2D GraphRange(directed_weighted_graph g) {
		double x0=0,x1=0,y0=0,y1=0;
		boolean first = true;
		for(node_data runner : g.getV()) {
			geo_location p = runner.getLocation();
			if(first) {
				x0=p.x();
				x1=x0;
				y0=p.y();
				y1=y0;
				first = false;
			}
			else {
				if(p.x()<x0) {
					x0=p.x();
				}
				if(p.x()>x1) {
					x1=p.x();
				}
				if(p.y()<y0) {
					y0=p.y();
				}
				if(p.y()>y1) {
					y1=p.y();
				}
			}
		}
		Range xr = new Range(x0,x1);
		Range yr = new Range(y0,y1);
		return new Range2D(xr,yr);
	}
}
