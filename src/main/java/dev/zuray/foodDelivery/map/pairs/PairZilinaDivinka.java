package dev.zuray.foodDelivery.map.pairs;

import dev.zuray.foodDelivery.map.Generators;
import dev.zuray.foodDelivery.map.TownPair;

public class PairZilinaDivinka extends TownPair {
    public PairZilinaDivinka(boolean givenDir) {
        super(givenDir);
    }

    @Override
    public double getTravelTime(boolean badTiming) {
        double pathA = 4 / Generators.GREEN.getDist().next().doubleValue();
        double pathB = 4 / Generators.RED.getDist().next().doubleValue();
        double pathC = 0;
        double black = 2 / Generators.BLACK.getDist().next().doubleValue();
        double red = 2 / Generators.RED.getDist().next().doubleValue();
        if (badTiming) {
            if (this.givenDir) {
                red = red * this.percent();
            } else {
                black = black * this.percent();
            }
        }
        pathC += black + red;
        return Math.min(pathB, Math.min (pathA, pathC));
    }
}
