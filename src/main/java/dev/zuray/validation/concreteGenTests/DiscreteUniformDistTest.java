package dev.zuray.validation.concreteGenTests;

import dev.zuray.generators.DiscreteDistGen;
import dev.zuray.generators.SeedManager;
import dev.zuray.validation.AbstractGeneratorTest;

import java.util.TreeMap;

public class DiscreteUniformDistTest extends AbstractGeneratorTest {
    private final DiscreteDistGen generator;
    private Long maxPossible;
    public DiscreteUniformDistTest(DiscreteDistGen generator, long a, long c, long seed, long mod) {
        super(a, c, seed, mod);
        this.generator = generator;
    }

    public DiscreteUniformDistTest() {
        super(118L, 15L, 117L, 1_000_000);
        long seed = SeedManager.sm.getNextSeed();
        long a = 15123;
        long c = 177217;
        long m = 1_000_000;
        this.generator = new DiscreteDistGen(seed, a, c, m);
        this.maxPossible = m;
    }

    @Override
    protected long getNext() {
        return this.generator.getNext();
    }

    @Override
    protected long maxPossible() {
        return this.maxPossible;
    }

    @Override
    protected TreeMap<Long, Integer> generateForFit(long maxVal) {
        var vals = new TreeMap<Long, Integer>();
        for (long i = 0; i < maxVal / 100; i++) {
            vals.put(i, 100);
        }
        return vals;
    }
}
