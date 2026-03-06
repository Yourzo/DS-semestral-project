package dev.zuray.simCore;

import dev.zuray.logging.Logger;

public abstract class MonteCarloSimulationCore extends Thread {
    private final int replications;
    private final Object lock = new Object();
    private volatile boolean paused = false;
    protected abstract void setupSimulation();
    protected abstract void beforeReplication();
    protected abstract void doReplication();
    protected abstract void afterReplication();
    protected abstract void afterSimulation();

    public MonteCarloSimulationCore(int replications) {
        this.replications = replications;
    }

    public void pause() {
        Logger.debug("Paused");
        this.paused = true;
    }

    public void resume() {
        Logger.debug("Unpaused");
        synchronized (this.lock) {
            this.paused = false;
            this.lock.notify();
        }
    }

    @Override
    public void run() {
        Logger.info("Running Monte Carlo Core simulation");
        boolean stopped = false;
        this.setupSimulation();
        int counter = 0;
        while (counter < this.replications) {
            synchronized (this.lock) {
                try {
                    while (this.paused) {
                        this.lock.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.beforeReplication();
            this.doReplication();
            this.afterReplication();
            counter++;
        }
        this.afterSimulation();
    }
}
