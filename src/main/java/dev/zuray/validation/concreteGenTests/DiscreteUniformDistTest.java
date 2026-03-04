package dev.zuray.validation.concreteGenTests;

import dev.zuray.generators.DiscreteDistGen;
import dev.zuray.generators.SeedManager;
import dev.zuray.validation.AbstractGeneratorTest;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DiscreteUniformDistTest extends AbstractGeneratorTest {
    private final DiscreteDistGen generator;
    private Long maxPossible;
    public DiscreteUniformDistTest(DiscreteDistGen generator, long a, long c, long seed, long mod) {
        super(a, c, seed, mod);
        this.generator = generator;
    }

    public DiscreteUniformDistTest() {
        super(118L, 15L, 117L, 10_000);
        long seed = SeedManager.sm.getNextSeed();
        long a = 15123;
        long c = 177217;
        long m = 10_000;
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

    @Override
    protected void chart() {
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Random Discrete Number generator to 1 000 000")
                .xAxisTitle("Generated values")
                .yAxisTitle("Rate")
                .build();

        chart.getStyler().setYAxisMin(0.0);
        chart.getStyler().setYAxisMax(200.0);

        List<Long> xData = new ArrayList<>();
        List<Integer> yData = new ArrayList<>();
        int index = 0;
        for (Map.Entry<Long, Integer> entry : this.values.entrySet()) {
            if (index % 1 == 0) {
                xData.add(entry.getKey());
                yData.add(entry.getValue());
            }
            index++;
        }

        chart.addSeries("Random discrete generator data", xData, yData);
        new SwingWrapper<>(chart).displayChart();
    }
}
