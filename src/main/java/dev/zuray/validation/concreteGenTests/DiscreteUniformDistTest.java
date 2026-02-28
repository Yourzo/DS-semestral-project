package dev.zuray.validation.concreteGenTests;

import dev.zuray.generators.DiscreteDistGen;
import dev.zuray.validation.AbstractGeneratorTest;

import java.util.TreeMap;

public class DiscreteUniformDistTest extends AbstractGeneratorTest<Long> {
    private DiscreteDistGen generator;
    private Long bound;
    public DiscreteUniformDistTest(DiscreteDistGen generator, long a, long c, long seed, long mod) {
        super(a, c, seed, mod);
        this.generator = generator;
    }

    public DiscreteUniformDistTest() {
        super(47, 13, 384, 53438);
        long seed = 384;
        long a = 47;
        long c = 13;
        long m = 53438;
        this.generator = new DiscreteDistGen(seed, a, c, m);
        this.bound = m;
    }

    @Override
    protected Long getNext() {
        return this.generator.getNext();
    }

    @Override
    protected Long bound() {
        return this.bound;
    }

    @Override
    protected TreeMap<Long, Integer> generateForFit() {
        var vals = new TreeMap<Long, Integer>();
        for (long i = 0; i < this.bound; i++) {
            vals.put(i, 1);
        }
        return vals;
    }
}
