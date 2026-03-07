package dev.zuray.generators;

import java.util.Random;

public class ContinuousGen implements Distribution<Double> {
    private final Random gen;
    private final double lower;
    private final double upper;
    public ContinuousGen(double lower, double upper) {
        this.lower = lower;
        this.upper = upper;
        this.gen = new Random(SeedManager.sm.getNextSeed());
    }

    public Double next() {
        return this.gen.nextDouble() * (this.upper - this.lower) + this.lower;
    }
}
