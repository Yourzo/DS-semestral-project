package dev.zuray.foodDelivery.simulation;

import dev.zuray.foodDelivery.map.Generators;
import dev.zuray.foodDelivery.map.TownPair;
import dev.zuray.generators.EmpiricDistribution;
import dev.zuray.simCore.MonteCarloSimulationCore;

import java.util.ArrayList;
import java.util.Iterator;

public class FoodDeliverySimulation extends MonteCarloSimulationCore {
    private ArrayList<TownPair> pairs;
    private double currentTime;
    private ArrayList<Double> result;

    public FoodDeliverySimulation(
            int replications,
            double startTime,
            ArrayList<TownPair> pairs
    ) {
        super(replications);
        this.currentTime = startTime;
        this.result = new ArrayList<>();
        this.pairs = pairs;
    }

    @Override
    protected void setupSimulation() {

    }

    @Override
    protected void beforeReplication() {
        this.currentTime = 0;
    }

    @Override
    protected void doReplication() {
        for (TownPair town : this.pairs) {
            this.currentTime += town.getTravelTime(this.currentTime > 6.5);
        }
    }

    @Override
    protected void afterReplication() {
        this.result.add(this.currentTime);
    }

    @Override
    protected void afterSimulation() {
        Generators.resetAll();
    }
}
