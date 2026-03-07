package dev.zuray.foodDelivery.simulation;

import dev.zuray.generators.EmpiricDistribution;
import dev.zuray.simCore.MonteCarloSimulationCore;

public class FoodDeliverySimulation extends MonteCarloSimulationCore {
    private EmpiricDistribution<Long> blueSection;
    private EmpiricDistribution<Double> blackSection;

    public FoodDeliverySimulation(
            int replications,
            EmpiricDistribution<Long> discreteDist,
            EmpiricDistribution<Double> continuousDist
    ) {
        super(replications);
        this.blackSection= continuousDist;
        this.blueSection = discreteDist;
    }

    @Override
    protected void setupSimulation() {
    }

    @Override
    protected void beforeReplication() {

    }

    @Override
    protected void doReplication() {

    }

    @Override
    protected void afterReplication() {

    }

    @Override
    protected void afterSimulation() {

    }
}
