package gameClient;
import api.edge_data;
import gameClient.util.Point3D;
import org.json.JSONObject;

public class Pokemon {
	private edge_data edges;
	private double value;
	private int type;
	private Point3D position;
	private double distance;


	public Pokemon(Point3D p, int t, double v, double s, edge_data e) {
		type = t;
		value = v;
		position = p;
		setEdges(e);
	}


	public edge_data getEdges() {
		return edges;
	}

	public void setEdges(edge_data edges) {
		this.edges = edges;
	}

	public Point3D getLocation() {
		return position;
	}

	public int getType() {
		return type;
	}

	public double getValue() {
		return value;
	}


	public int getSource() {
		return edges.getSrc();
	}
	public int getDest(){
		return edges.getDest();
	}
	public void setDistance(double dis) {
		distance = dis;
	}
	public Double getDistance(){
		return distance;
	}
}
