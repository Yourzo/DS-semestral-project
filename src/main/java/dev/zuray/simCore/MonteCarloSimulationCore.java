package dev.zuray.simCore;

public abstract class MonteCarloSimulationCore {
    public abstract void setupSimulation();
    public abstract void beforeReplication();
    public abstract void doReplication();
    public abstract void afterReplication();
    public abstract void afterSimulation();
    public void simulate(int replications) {
        boolean stopped = false;
        this.beforeReplication();
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
