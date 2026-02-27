package dev.zuray;

import dev.zuray.generators.ContinuousDistGen;
import dev.zuray.generators.SeedManager;
import dev.zuray.logging.Logger;
import dev.zuray.simCore.BuffonNeedleExperiment;

public class Main {
    public static void main(String[] args) {
        Logger.info("Starting the program!");
        BuffonNeedleExperiment bne = new BuffonNeedleExperiment();
        bne.simulate(100_000_000);
    }
}