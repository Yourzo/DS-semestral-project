package dev.zuray.generators;

import dev.zuray.logging.Logger;

public class ContinuousDistGen implements Distribution<Double> {
    private long m;
    private DiscreteDistGen gen;
    public ContinuousDistGen(long seed, long a, long c, long m) {
        gen = new DiscreteDistGen(seed, a, c, m);
        Logger.debug("Initializing continuous distribution generator, with seed: " + seed);
        this.m = m;
    }

    public Double getNext() {
        return (double) this.gen.getNext() / (double) m;
    }

    @Override
    public long getM() {
        return this.m;
    }
}
