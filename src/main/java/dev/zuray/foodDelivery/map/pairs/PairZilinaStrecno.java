package dev.zuray.foodDelivery.map.pairs;

import dev.zuray.foodDelivery.map.Generators;
import dev.zuray.foodDelivery.map.TownPair;

public class PairZilinaStrecno extends TownPair {
    public PairZilinaStrecno(boolean givenDir) {
        super(givenDir);
    }

    @Override
    public double getTravelTime(boolean badTiming) {
        double pathA = 4 / Generators.RED.getDist().next().doubleValue();
        double pathB = 3 / Generators.BLACK.getDist().next().doubleValue();
        double pathC = 0;
        pathA += 3 / Generators.RED.getDist().next().doubleValue();
        pathB += 4 / Generators.GREEN.getDist().next().doubleValue();
        double black = 2 / Generators.BLACK.getDist().next().doubleValue();
        double blue = 4 / Generators.BLUE.getDist().next().doubleValue();
        if (badTiming) {
            if (this.givenDir) {
                blue = blue * this.percent();
            } else {
                black = black * this.percent();
            }
        }
        pathC += black + blue;
        return Math.min(pathA, Math.min(pathB, pathC));
    }
}
