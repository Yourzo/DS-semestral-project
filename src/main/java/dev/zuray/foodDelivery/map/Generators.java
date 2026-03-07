package dev.zuray.foodDelivery.map;

import dev.zuray.generators.ContinuousGen;
import dev.zuray.generators.DiscreteGen;
import dev.zuray.generators.Distribution;
import dev.zuray.generators.EmpiricDistribution;

import java.util.function.Supplier;

public enum Generators {
    RED(() -> new DiscreteGen(55, 75)),
    GREEN(() -> new ContinuousGen(50., 80.)),
    BLACK(() -> new EmpiricDistribution<>(
            new EmpiricDistribution.RangeData<>(10., 10., .1),
            new EmpiricDistribution.RangeData<>(20., 12., .5),
            new EmpiricDistribution.RangeData<>(32., 13., .2),
            new EmpiricDistribution.RangeData<>(45., 30., .15),
            new EmpiricDistribution.RangeData<>(75., 10., .05)
    )),
    BLUE(() -> new EmpiricDistribution<>(
            new EmpiricDistribution.RangeData<>(15L,14L, .2),
            new EmpiricDistribution.RangeData<>(29L, 16L, .4),
            new EmpiricDistribution.RangeData<>(45L, 20L, .4)
    )),
    REDUCTION(() -> new ContinuousGen(10., 25.));
    private final ThreadLocal<Distribution<?>> dist;
    Generators(Supplier<Distribution<?>> dist) {
        this.dist = ThreadLocal.withInitial(dist);
    }

    public Distribution<?> getDist() {
        return this.dist.get();
    }

    public void reset() {
        this.dist.remove();
    }

    public static void resetAll() {
        for (Generators gen : values()) {
            gen.reset();
        }
    }
}
