package dev.zuray.generators;

import dev.zuray.exceptions.EmpiricGeneratorValueWrongInternalStateError;

import java.util.ArrayList;
import java.util.Random;

public class EmpiricDistribution<T extends Number> implements Distribution<T> {
    private final Random gen;
    private final ArrayList<Range> ranges;
    @SafeVarargs
    public EmpiricDistribution(RangeData<T> ...data) {
        this.gen = new Random(SeedManager.sm.getNextSeed());
        this.ranges = new ArrayList<>();
        for (RangeData<T> r : data) {
            this.ranges.add(new Range(r.offset, r.range, r.probability));
        }
    }

    public T next() {
        var val = this.gen.nextDouble();
        for (var range : this.ranges) {
            if (val < range.getProbability()) {
                return range.getNext();
            }
            val -= range.getProbability();
        }
        throw new EmpiricGeneratorValueWrongInternalStateError("Wasn't supplied values up to 1");
    }

    public record RangeData<T>(T offset, T range, double probability) {}

    private class Range {
        private final double probability;
        private final Random generator;
        private final T offset;
        private final T range;
        public Range(T offset, T range, double probability) {
            this.generator = new Random(SeedManager.sm.getNextSeed());
            this.offset = offset;
            this.probability = probability;
            this.range = range;
        }

        public T getNext() {
            if (this.offset instanceof Long) {
                return (T) Long.valueOf(this.generator.nextLong(this.range.longValue()) + this.offset.longValue());
            }
            return (T) Double.valueOf((this.generator.nextDouble() * this.range.doubleValue()) + this.offset.doubleValue());
        }

        public double getProbability() {
            return this.probability;
        }
    }
}
