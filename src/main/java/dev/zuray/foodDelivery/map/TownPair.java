package dev.zuray.foodDelivery.map;

public abstract class TownPair {
    protected boolean givenDir;
    public abstract double getTravelTime(boolean badTiming);

    public TownPair(boolean givenDir) {
        this.givenDir = givenDir;
    }

    public double percent() {
        double slowness = Generators.REDUCTION.getDist().next().doubleValue();
        return ((100 - slowness) / 100);
    }
}
