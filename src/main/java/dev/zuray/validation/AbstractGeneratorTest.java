package dev.zuray.validation;

import dev.zuray.exceptions.GeneratorValidationException;
import dev.zuray.logging.Logger;

import java.util.TreeMap;

public abstract class AbstractGeneratorTest<T extends Number> {
    private TreeMap<T, Integer> values;
    private long mod;
    private long A;
    private long C;
    private long seed;

    protected abstract T getNext();
    protected abstract T bound();
    protected abstract TreeMap<T, Integer> generateForFit();
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
            T val = this.getNext();
            if (Double.compare(this.bound().doubleValue(), val.doubleValue()) <= 0) {
                throw new GeneratorValidationException("Generated value is more than bound: " + this.bound());
            }
            if (!this.values.containsKey(val)){
                this.values.put(val, 1);
            } else {
                Integer count = this.values.get(val);
                count++;
                this.values.put(val, count);
            }
            if (i % 10_000_000 == 0)  {
                Logger.info("Current step is: " + i);
            }
        }

        Logger.info("Running chi-squared test");
        DiscreteGeneratorFitTest<T> fitTest = new DiscreteGeneratorFitTest<>(this.values, this.generateForFit());
        if (!fitTest.runTheTest(0.1)) {
            throw new GeneratorValidationException("Generated value doesn't match desired distribution");
        }
    }
}
