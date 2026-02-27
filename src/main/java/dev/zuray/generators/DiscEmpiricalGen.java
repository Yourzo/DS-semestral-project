package dev.zuray.generators;

import dev.zuray.logging.Logger;

import java.util.Random;

public class DiscEmpiricalGen {
    private long lastVal;
    private long a;
    private long c;
    private long m;

    public DiscEmpiricalGen(long seed, long a, long c, long m) {
        Logger.debug("Initializing Discrete Empirical Distribution Generator, with seed:" + seed);
        this.lastVal = seed % m; //here is modulated, to be smaller than m
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public long getNext() {
        this.lastVal = (a * this.lastVal + c) % m;
        return this.lastVal;
    }
}
