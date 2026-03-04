package dev.zuray.validation;

import dev.zuray.exceptions.GeneratorValidationException;
import dev.zuray.logging.Logger;

import java.util.TreeMap;

public abstract class AbstractGeneratorTest {
    protected final TreeMap<Long, Integer> values;
    private final long mod;
    private final long A;
    private final long C;
    private final long seed;

    protected abstract long getNext();
    protected abstract long maxPossible();
    protected abstract TreeMap<Long, Integer> generateForFit(long maxValue);
    protected abstract void chart();
    protected AbstractGeneratorTest(long a, long c, long seed, long mod) {
        this.A = a;
        this.C = c;
        this.mod = mod;
        this.seed = seed;
        this.values = new TreeMap<>();
    }

    public void validate(int steps) throws GeneratorValidationException {
        if (this.mod <= this.A) {
            throw new GeneratorValidationException("Modulo is less than a-value");
        }

        if (this.mod <= this.C) {
            throw new GeneratorValidationException("Modulo is less than constant");
        }

        if (this.mod <= this.seed) {
            throw new GeneratorValidationException("Modulo is less than seed");
        }
        if (this.mod <= 0) {
            throw new GeneratorValidationException("Modulo is less than 0");
        }
        for (int i = 0; i < steps; i++) {
            long val = this.getNext();
            if (this.maxPossible() < val) {
                throw new GeneratorValidationException("Generated value is more than bound: " + this.maxPossible());
            }
            if (!this.values.containsKey(val / 100)){
                this.values.put(val / 100, 1);
            } else {
                Integer count = this.values.get(val / 100);
                count++;
                this.values.put(val / 100, count);
            }
            if (i % 10_000_000 == 0)  {
                Logger.info("Current step is: " + i);
            }
        }

        Logger.info("Running chi-squared test");
        DiscreteGeneratorFitTest<Long> fitTest = new DiscreteGeneratorFitTest<>(this.values, this.generateForFit(steps));
        if (!fitTest.runTheTest(0.01)) {
            throw new GeneratorValidationException("Generated value doesn't match desired distribution");
        }
        this.chart();
    }
}
