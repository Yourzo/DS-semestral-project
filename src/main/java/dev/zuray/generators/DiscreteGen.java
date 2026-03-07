package dev.zuray.generators;

import java.util.Random;

public class DiscreteGen implements Distribution<Integer> {
    private final Random gen;
    private final int lower;
    private final int upper;
    public DiscreteGen(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        this.gen = new Random(SeedManager.sm.getNextSeed());
    }

    public Integer next() {
        return this.gen.nextInt(this.upper - this.lower) + this.lower;
    }
}
