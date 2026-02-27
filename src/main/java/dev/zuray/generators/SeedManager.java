package dev.zuray.generators;

import dev.zuray.logging.Logger;

import java.util.Random;

public class SeedManager {
    private final Random random;
    public SeedManager() {
        Logger.debug("Initializing random seed generator, main seed not specified");
        Random zeroRand = new Random();
        long seed = zeroRand.nextLong();
        Logger.info("Main seed is: " + seed);
        this.random = new Random(seed);
    }

    public SeedManager(long seed) {
        Logger.debug("Initializing random seed generator, main seed: " + seed);
        this.random = new Random(seed);
    }

    public long getNextSeed() {
        return Math.abs(this.random.nextLong());
    }
}
