package dev.zuray.foodDelivery.map.pairs;

import dev.zuray.foodDelivery.map.Generators;
import dev.zuray.foodDelivery.map.TownPair;

public class PairDivinkaStrecno extends TownPair {
    public PairDivinkaStrecno(boolean givenDir) {
        super(givenDir);
    }

    @Override
    public double getTravelTime(boolean badTiming) {
        double red = 2 / Generators.RED.getDist().next().doubleValue();
        double blue = 4 / Generators.BLUE.getDist().next().doubleValue();
        if (badTiming) {
            if (this.givenDir) {
                blue = blue * this.percent();
            } else {
                red = red * this.percent();
            }
        }
        return blue + red;
    }
}
