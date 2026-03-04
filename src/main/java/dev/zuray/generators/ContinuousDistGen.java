package dev.zuray.generators;

import dev.zuray.logging.Logger;

public class ContinuousDistGen {
    private double m;
    private DiscreteDistGen gen;
    public ContinuousDistGen(long seed, long a, long c, long m) {
        gen = new DiscreteDistGen(seed, a, c, m);
        Logger.debug("Initializing continuous distribution generator, with seed: " + seed);
        this.m = m;
    }

    public double getNextDouble() {
        return (double) this.gen.getNext() / m;
    }
}
