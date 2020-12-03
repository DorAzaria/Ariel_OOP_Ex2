package api;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.security.KeyStore;
import java.util.Map;

public class graphDeserialization implements JsonDeserializer<directed_weighted_graph> {
    @Override
    public directed_weighted_graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        directed_weighted_graph graph = new DWGraph_DS();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonArray nodes = jsonObject.get("Nodes").getAsJsonArray();
        JsonArray edges = jsonObject.get("Edges").getAsJsonArray();
        for (JsonElement v : nodes) {
            JsonElement val = v.getAsJsonObject();
            String loc = val.getAsJsonObject().get("pos").getAsString();
            double[] geolocation = separateString(loc);
            double x = geolocation[0];
            double y = geolocation[1];
            double z = geolocation[2];
            node_data node = new Nodes();
            System.out.println(node.getKey() +"~~~~~");
            geo_location location = new Nodes.GeoLocation(x, y, z);
            node.setLocation(location);
            graph.addNode(node);

        }
        for (JsonElement e : edges) {
            JsonElement val = e.getAsJsonObject();
            int source = val.getAsJsonObject().get("src").getAsInt();
            int destination = val.getAsJsonObject().get("dest").getAsInt();
            double weight = val.getAsJsonObject().get("w").getAsDouble();
            graph.connect(source,destination,weight);
        }
            return graph;
    }

    public double[] separateString(String location) {
        double arr[] = new double[3]; // this value is because the point (x,y,z)
        String str = "";
        int c = 0;
        for(int i = 0; i < location.length() ; i++ ){
            if(location.charAt(i) != ',') {
                str += location.charAt(i);
            }
            else {
                double val = Double.parseDouble(str);
                arr[c] = val;
                c++;
                str = "";
            }

        }
        return arr;
    }
}

