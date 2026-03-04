package dev.zuray.generators;

public class Range<T extends Number> {
    private double probability;
    private final Distribution<T> generator;
    private final T offset;
    private long range;
    public Range(Distribution<T> generator, T offset, double probability, long range) {
        this.generator = generator;
        this.offset = offset;
        this.probability = probability;
        this.range = range;
    }

    public T getNext() {
        T val = this.generator.getNext();
        if (val instanceof Long && this.offset instanceof Long) {
            return (T) Long.valueOf(val.longValue() + this.offset.longValue());
        }
        return (T) Double.valueOf((val.doubleValue() * range) + this.offset.doubleValue());
    }

    public double getProbability() {
        return this.probability;
    }
}
