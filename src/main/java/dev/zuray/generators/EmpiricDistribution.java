package dev.zuray.generators;

import dev.zuray.exceptions.EmpiricGeneratorValueWrongInternalStateError;

import java.util.ArrayList;

public class EmpiricDistribution<T extends Number> {
    private ContinuousDistGen gen;
    private ArrayList<Range<T>> ranges;
    public EmpiricDistribution(ContinuousDistGen gen, ArrayList<Range<T>> ranges) {
        this.gen = gen;
        this.ranges = ranges;
    }

    public T getNext() {
        var val = this.gen.getNext();
        for (var range : this.ranges) {
            if (val < range.getProbability()) {
                return range.getNext();
            }
            val -= range.getProbability();
        }
        throw new EmpiricGeneratorValueWrongInternalStateError("Wasn't supplied values up to 1");
    }
}
