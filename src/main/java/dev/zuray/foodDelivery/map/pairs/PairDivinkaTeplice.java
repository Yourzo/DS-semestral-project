package dev.zuray.foodDelivery.map.pairs;

import dev.zuray.foodDelivery.map.Generators;
import dev.zuray.foodDelivery.map.TownPair;

public class PairDivinkaTeplice extends TownPair {
    public PairDivinkaTeplice(boolean givenDir) {
        super(givenDir);
    }

    @Override
    public double getTravelTime(boolean badTiming) {
        double pathA = 0;
        double pathB = 0;
        double pathC = 0;
        double blackPath = Generators.BLACK.getDist().next().doubleValue();
        pathA += blackPath;
        pathB += blackPath;
        pathA += 3 / Generators.RED.getDist().next().doubleValue() * 3;
        pathB += 1 / Generators.BLUE.getDist().next().doubleValue();
        pathB += 2 / Generators.RED.getDist().next().doubleValue();
        double bluePath = 1 / Generators.BLUE.getDist().next().doubleValue();
        double red = 2 / Generators.RED.getDist().next().doubleValue();
        double green = 2 / Generators.GREEN.getDist().next().doubleValue();
        if (badTiming) {
            if (this.givenDir) {
                green = green * this.percent();
            } else {
                red = red * this.percent();
            }
        }
        pathA += bluePath;
        pathB += blackPath;
        pathC += red + green;
        return Math.min(pathA, Math.min(pathB, pathC));
    }
}
