package dev.zuray.simCore;

import dev.zuray.generators.ContinuousDistGen;
import dev.zuray.generators.SeedManager;
import dev.zuray.logging.Logger;

public class BuffonNeedleExperiment extends MonteCarloSimulationCore {
    private ContinuousDistGen genAlpha;
    private ContinuousDistGen genY;
    private int needleLen = 8;
    private int spaceD = 10;
    private double a;
    private double res;
    private int count;
    public BuffonNeedleExperiment() {
        this.genAlpha = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 11471, 1593, 534384881);
        this.genY = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 861237, 41123, 484445389);
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
        a = Math.sin(Math.toRadians(this.genAlpha.getNext() * 180)) * needleLen;
        if (this.genY.getNext() * spaceD + a > spaceD) {
            res++;
        }
    }

    @Override
    protected void afterReplication() {
        this.count++;
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
