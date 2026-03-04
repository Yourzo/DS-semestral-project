package dev.zuray.generators;

import dev.zuray.logging.Logger;

public class DiscreteDistGen implements Distribution<Long> {
    private long lastVal;
    private long a;
    private long c;
    private long m;

    public DiscreteDistGen(long seed, long a, long c, long m) {
        Logger.debug("Initializing Discrete Empirical Distribution Generator, with seed:" + seed);
        if (0 >= m) {
            throw new RuntimeException("m: " + m + " is smaller than 0");
        }
        if (a >= m) {
            Logger.warn("a: " + a + " is equal or higher than m: " + m);
            a = a % m;
        }
        if (c >= m) {
            Logger.warn("c: " + c + " is equal or higher than m: " + m);
            c = c % m;
        }
        if (seed >= m) {
            Logger.warn("seed: " + seed + " is equal or higher than m: " + m);
            seed = seed % m;
        }
        this.lastVal = seed;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public Long getNext() {
        this.lastVal = (a * this.lastVal + c) % m;
        return this.lastVal;
    }

    @Override
    public long getM() {
        return this.m;
    }
}
