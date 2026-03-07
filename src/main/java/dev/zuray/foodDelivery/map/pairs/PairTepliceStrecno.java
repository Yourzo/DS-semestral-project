package dev.zuray.foodDelivery.map.pairs;

import dev.zuray.foodDelivery.map.Generators;
import dev.zuray.foodDelivery.map.TownPair;

public class PairTepliceStrecno extends TownPair {
    public PairTepliceStrecno(boolean givenDir) {
        super(givenDir);
    }

    @Override
    public double getTravelTime(boolean badTiming) {
        double pathA = 0;
        double pathB = 0;
        double pathC = 0;
        double blueVal = 8 / Generators.BLUE.getDist().next().doubleValue();
        pathA += blueVal;
        pathB += blueVal;
        double green = 2 / Generators.GREEN.getDist().next().doubleValue();
        double blue = 4 / Generators.BLUE.getDist().next().doubleValue();
        if (badTiming) {
            if (this.givenDir) {
                blue = blue * this.percent();
            } else {
                green = green * this.percent();
            }
        }
        pathA += 5 / Generators.BLUE.getDist().next().doubleValue();
        pathB += 5 / Generators.BLACK.getDist().next().doubleValue();
        pathC += green + blue;
        return Math.min(pathA, Math.min(pathB, pathC));
    }
}
