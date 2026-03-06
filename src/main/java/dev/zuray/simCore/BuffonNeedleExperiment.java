package dev.zuray.simCore;

import dev.zuray.generators.SeedManager;
import dev.zuray.logging.Logger;

import java.util.Random;

public class BuffonNeedleExperiment extends MonteCarloSimulationCore {
    private Random genAlpha;
    private Random genY;
    private int needleLen = 8;
    private int spaceD = 10;
    private double a;
    private double res;
    private int count;
    public BuffonNeedleExperiment(int replications) {
        super(replications);
        this.genAlpha = new Random(SeedManager.sm.getNextSeed());
        this.genY = new Random(SeedManager.sm.getNextSeed());
    }

    @Override
    protected void setupSimulation() {
        Logger.debug("Starting Buffon needle experiment");
    }

    @Override
    protected void beforeReplication() {

    }

    @Override
    protected void doReplication() {
        a = Math.sin(Math.toRadians(this.genAlpha.nextDouble() * 180)) * needleLen;
        if (this.genY.nextDouble() * spaceD + a > spaceD) {
            res++;
        }
    }

    @Override
    protected void afterReplication() {
        this.count++;
        if (this.count % 100_000 == 0) {
            Logger.info("Current step: " + this.count);
        }
    }

    @Override
    protected void afterSimulation() {
        double top = (2 * needleLen);
        double p = (res / this.count);
        double bottom = (p * this.spaceD);
        double pi = top / bottom;
        Logger.info("Solution of the Buffon experiment was: " + pi);
    }

    
}
