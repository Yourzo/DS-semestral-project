package dev.zuray.foodDelivery.map.pairs;

import dev.zuray.foodDelivery.map.Generators;
import dev.zuray.foodDelivery.map.TownPair;

public class PairZilinaTeplice extends TownPair {
    public PairZilinaTeplice(boolean givenDir) {
        super(givenDir);
    }

    @Override
    public double getTravelTime(boolean badTiming) {
        double black = 2 / Generators.BLACK.getDist().next().doubleValue();
        double green = 2 / Generators.GREEN.getDist().next().doubleValue();
        if (badTiming) {
            if (this.givenDir) {
                green = green * this.percent();
            } else {
                black = black * this.percent();
            }
        }
        return black + green;
    }
}
