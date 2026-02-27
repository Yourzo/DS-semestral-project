package dev.zuray.simCore;

import dev.zuray.logging.Logger;

public abstract class MonteCarloSimulationCore {
    protected abstract void setupSimulation();
    protected abstract void beforeReplication();
    protected abstract void doReplication();
    protected abstract void afterReplication();
    protected abstract void afterSimulation();
    public void simulate(int replications) {
        Logger.info("Running Monte Carlo Core simulation");
        boolean stopped = false;
        this.setupSimulation();
        int counter = 0;
        while (counter < replications && !stopped) {
            this.beforeReplication();
            this.doReplication();
            this.afterReplication();
            counter++;
        }
        this.afterSimulation();
    }
}
