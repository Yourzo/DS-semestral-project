package dev.zuray.validation;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import java.util.Map;
import java.util.TreeMap;

/** This is classic chi squared goodness-of-Fit Test  */
public class DiscreteGeneratorFitTest<T extends Number> {
    private final TreeMap<T, Integer> values;
    private final TreeMap<T, Integer> theoreticalVals;
    private double chiVal;

    public DiscreteGeneratorFitTest(TreeMap<T, Integer> values, TreeMap<T, Integer> theoreticalDist) {
        this.theoreticalVals = theoreticalDist;
        this.values = values;
    }

    /** @return true if H0 is not rejected -> distribution could be according to theoretical and
     * false if H0 was rejected distribution of values is NOT according to theoretical*/
    public boolean runTheTest(double alpha) {
        var degreeOfFreedom = this.theoreticalVals.size() - 1;
        ChiSquaredDistribution dist = new ChiSquaredDistribution(degreeOfFreedom);
        double criticalVal = dist.inverseCumulativeProbability(1 - alpha);
        this.calculateChi();
        return this.chiVal <= criticalVal;
    }

    private void calculateChi() {
        for (Map.Entry<T, Integer> entry: this.theoreticalVals.entrySet()) {
            var value = this.values.get(entry.getKey());
            if (value == null) {
                value = 0;
            }
            this.chiVal += Math.pow((value - entry.getValue()), 2) / entry.getValue();
        }
    }
}
