package dev.zuray.generators;

import dev.zuray.logging.Logger;

public class ContinuousDistGen extends DiscreteDistGen {
    private DiscreteDistGen discrete;
    private double m;
    public ContinuousDistGen(long seed, long a, long c, long m) {
        super(seed, a, c, m);
        Logger.debug("Initializing continuous distribution generator, with seed: " + seed);
        this.discrete = new DiscreteDistGen(seed, a, c, m);
        this.m = m;
    }

    public double getNextDouble() {
        return (double) this.discrete.getNext() / m;
    }

    @Override
    public long getNext() {
        throw new UnsupportedOperationException("This class doesn't support this!");
    }
}
