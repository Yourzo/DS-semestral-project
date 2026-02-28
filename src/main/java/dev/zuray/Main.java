package dev.zuray;

import dev.zuray.exceptions.GeneratorValidationException;
import dev.zuray.logging.Logger;
import dev.zuray.simCore.BuffonNeedleExperiment;
import dev.zuray.validation.concreteGenTests.DiscreteUniformDistTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
                    DiscreteUniformDistTest test = new DiscreteUniformDistTest();
                    Logger.info("Starting the discreteUniformDist TEST!!!!");
                    try {
                        test.validate(53438);
                    } catch (GeneratorValidationException e) {
                        Logger.fatal(e.getMessage());
                    }
                });
        executor.submit(() -> {
            Logger.info("Starting the program!");
            BuffonNeedleExperiment bne = new BuffonNeedleExperiment();
            bne.simulate(1_000_000_000);
        });
        executor.shutdown();
    }
}