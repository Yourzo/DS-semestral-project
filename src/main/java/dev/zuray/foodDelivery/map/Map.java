package dev.zuray.foodDelivery.map;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private int cityCount;
    private Place initial;
    private final HashMap<String, Place> cities;

    public Map() {
        this.cities = HashMap.newHashMap(4);
    }

    public void setInitialCity(String initialCity) {
        this.initial = new Place(initialCity);
        this.cities.put(initialCity, this.initial);
    }

    public Place getInitialCity() {
        return this.initial;
    }

    public int getCityCount() {
        return this.cityCount;
    }

    public void setCityCount(int cityCount) {
        this.cityCount = cityCount;
    }

    public void addConnection(Connection c) {
        if (!this.cities.containsKey(c.a.name)) {
            this.cities.put(c.a.name, c.a);
        }
//        this.cities.get(c.a.name).addRoad();
    }

    public class Point {

        public Point() {
        }

    }

    public class Place {
        private final String name;
        private ArrayList<Road> roads;
        public Place(String name) {
            this.name = name;
            this.roads = new ArrayList<>();
        }

        public void addRoad(Road road) {
            this.roads.add(road);
        }
    }

    public record Connection(Place a, Place b) {}

    public class Road {
        private Point from;
        private Point to;
        //todo paths
        public Road(Point from, Point to) {
            this.from = from;
            this.to = to;
        }
    }
}
