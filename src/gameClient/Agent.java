package gameClient;

import api.directed_weighted_graph;
import api.edge_data;
import api.geo_location;
import api.node_data;
import gameClient.util.Point3D;
import org.json.JSONObject;

public class Agent {

		private int key;
		private geo_location position;
		private double speed;
		private edge_data current_edge;
		private node_data current_node;
		private directed_weighted_graph graph;
		private Pokemon currentPokemon;
		private long _sg_dt;
		private double value;

		public Agent(directed_weighted_graph g, int start_node) {
			graph = g;
			setMoney(0);
			this.current_node = graph.getNode(start_node);
			position = current_node.getLocation();
			key = -1;
			setSpeed(0);
		}
		public void update(String json) {
			JSONObject line;
			try {
				line = new JSONObject(json);
				JSONObject ttt = line.getJSONObject("Agent");
				int id = ttt.getInt("id");
				if(id==this.getID() || this.getID() == -1) {
					if(this.getID() == -1) {
						key = id;}
					double speed = ttt.getDouble("speed");
					String p = ttt.getString("pos");
					Point3D pp = new Point3D(p);
					int src = ttt.getInt("src");
					int dest = ttt.getInt("dest");
					double value = ttt.getDouble("value");
					this.position = pp;
					this.setCurrNode(src);
					this.setSpeed(speed);
					this.setNextNode(dest);
					this.setMoney(value);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		//@Override
		public int getSrcNode() {return current_node.getKey();}
		public String toJSON() {
			int d = this.getNextNode();
			String ans = "{\"Agent\":{"
					+ "\"id\":"+this.key +","
					+ "\"value\":"+this.value +","
					+ "\"src\":"+this.current_node.getKey()+","
					+ "\"dest\":"+d+","
					+ "\"speed\":"+this.getSpeed()+","
					+ "\"pos\":\""+ position.toString()+"\""
					+ "}"
					+ "}";
			return ans;	
		}
		private void setMoney(double v) {
			value = v;
		}
	
		public boolean setNextNode(int dest) {
			boolean ans = false;
			int src = this.current_node.getKey();
			current_edge = graph.getEdge(src, dest);
			if(current_edge !=null) {
				ans=true;
			}
			else {
				current_edge = null;}
			return ans;
		}
		public void setCurrNode(int src) {
			this.current_node = graph.getNode(src);
		}
		public boolean isMoving() {
			return this.current_edge !=null;
		}
		public String toString() {
			return toJSON();
		}
		public String toString1() {
			String ans=""+this.getID()+","+ position +", "+isMoving()+","+this.getKey();
			return ans;
		}
		public int getID() {
			// TODO Auto-generated method stub
			return this.key;
		}
	
		public geo_location getLocation() {
			// TODO Auto-generated method stub
			return position;
		}

		
		public double getKey() {
			// TODO Auto-generated method stub
			return this.value;
		}



		public int getNextNode() {
			int ans = -2;
			if(this.current_edge ==null) {
				ans = -1;}
			else {
				ans = this.current_edge.getDest();
			}
			return ans;
		}

		public double getSpeed() {
			return this.speed;
		}

		public void setSpeed(double v) {
			this.speed = v;
		}
		public Pokemon getCurrentPokemon() {
			return currentPokemon;
		}
		public void setCurrentPokemon(Pokemon curr_pokemon) {
			currentPokemon = curr_pokemon;
			curr_pokemon.setTarget();
		};
		public edge_data getCurrent_edge() {
			return current_edge;
		}
		public long get_sg_dt() {
			return _sg_dt;
		}
		public void set_sg_dt(long _sg_dt) {
			this._sg_dt = _sg_dt;
		}
	}
