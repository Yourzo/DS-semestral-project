package dev.zuray.foodDelivery.simulation;

import dev.zuray.foodDelivery.map.TownPair;
import dev.zuray.foodDelivery.map.pairs.*;

import java.util.ArrayList;

public class SimulationFactory {
    public FoodDeliverySimulation firstScenario(int replications, double startTime) {
        var program = new ArrayList<TownPair>();
        program.add(new PairZilinaDivinka(true));
        program.add(new PairDivinkaTeplice(true));
        program.add(new PairTepliceStrecno(true));
        program.add(new PairZilinaStrecno(false));
        return new FoodDeliverySimulation(replications, startTime, program);
    }

    public FoodDeliverySimulation secondScenario(int replications, double startTime) {
        var program = new ArrayList<TownPair>();
        program.add(new PairZilinaDivinka(true));
        program.add(new PairDivinkaStrecno(true));
        program.add(new PairTepliceStrecno(false));
        program.add(new PairZilinaTeplice(false));
        return new FoodDeliverySimulation(replications, startTime, program);
    }

    public FoodDeliverySimulation thirdScenario(int replications, double startTime) {
        var program = new ArrayList<TownPair>();
        program.add(new PairZilinaTeplice(true));
        program.add(new PairDivinkaTeplice(false));
        program.add(new PairDivinkaStrecno(true));
        return new FoodDeliverySimulation(replications, startTime, program);
    }

    public FoodDeliverySimulation fourthScenario(int replications, double startTime) {
        var program = new ArrayList<TownPair>();
        program.add(new PairZilinaTeplice(true));
        program.add(new PairTepliceStrecno(true));
        program.add(new PairDivinkaStrecno(false));
        program.add(new PairZilinaDivinka(false));
        return new FoodDeliverySimulation(replications, startTime, program);
    }

    public FoodDeliverySimulation fifthScenario(int replication, double startTime) {
        var program = new ArrayList<TownPair>();
        program.add(new PairTepliceStrecno(true));
        program.add(new PairTepliceStrecno(false));
        program.add(new PairDivinkaTeplice(false));
        program.add(new PairZilinaDivinka(false));
        return new FoodDeliverySimulation(replication, startTime, program);
    }

    public FoodDeliverySimulation sixthScenario(int replication, double startTime) {
        var program = new ArrayList<TownPair>();
        program.add(new PairZilinaStrecno(true));
        program.add(new PairDivinkaStrecno(false));
        program.add(new PairDivinkaTeplice(true));
        program.add(new PairZilinaTeplice(false));
        return new FoodDeliverySimulation(replication, startTime, program);
    }
}
