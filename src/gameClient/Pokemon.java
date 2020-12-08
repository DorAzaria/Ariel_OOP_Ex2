package gameClient;
import api.edge_data;
import gameClient.util.Point3D;
import org.json.JSONObject;

public class Pokemon {
	private edge_data edges;
	private double value;
	private int type;
	private Point3D position;
	private double min_dist;
	private int min_ro;
	
	public Pokemon(Point3D p, int t, double v, double s, edge_data e) {
		type = t;
		value = v;
		position = p;
		min_dist = -1;
		min_ro = -1;
		setEdges(e);
	}
	public static Pokemon init_from_json(String json) {
		Pokemon ans = null;
		try {
			JSONObject p = new JSONObject(json);
			int id = p.getInt("id");

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	public String toString() {return "F:{v="+ value +", t="+ type +"}";}
	public edge_data getEdges() {
		return edges;
	}
	public void setEdges(edge_data edges) {
		this.edges = edges;
	}
	public Point3D getLocation() {
		return position;
	}
	public int getType() {return type;}
	public double getValue() {return value;}
	public double getMin_dist() {
		return min_dist;
	}
	public void setMin_dist(double mid_dist) {
		this.min_dist = mid_dist;
	}
	public int getMin_ro() {
		return min_ro;
	}
	public void setMin_ro(int min_ro) {
		this.min_ro = min_ro;
	}
}
