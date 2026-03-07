package dev.zuray.foodDelivery.map;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class MapLoader {
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static Map readJsonFromFile(String fileName) {
        Map res = new Map();
        try (BufferedReader rd = new BufferedReader(new FileReader(fileName))) {
            String jsonText = readAll(rd);
            JSONObject obj = new JSONObject(jsonText);
            res.setCityCount((Integer) obj.get("cityCount"));
            res.setInitialCity(obj.getString("initial"));
            JSONArray roads = obj.getJSONArray("roads");
            for (int i = 0; i < roads.length(); i++) {
                var road = roads.getJSONObject(i);
                res.addConnection(new Map.Connection(
                        res.new Place(road.getString("c1")), res.new Place(road.getString("c2"))
                ));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
}
