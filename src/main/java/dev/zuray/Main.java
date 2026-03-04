package dev.zuray;

import dev.zuray.exceptions.GeneratorValidationException;
import dev.zuray.generators.*;
import dev.zuray.logging.Logger;
import dev.zuray.simCore.BuffonNeedleExperiment;
import dev.zuray.validation.concreteGenTests.DiscreteUniformDistTest;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.management.relation.RelationNotFoundException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.submit(() -> {
//                    DiscreteUniformDistTest test = new DiscreteUniformDistTest();
//                    Logger.info("Starting the discreteUniformDist TEST!!!!");
//                    try {
//                        test.validate(10_000);
//                        Logger.info("Generator validated!");
//                    } catch (GeneratorValidationException e) {
//                        Logger.fatal(e.getMessage());
//                    }
//                });
//        executor.submit(() -> {
//            Logger.info("Starting the program!");
//            BuffonNeedleExperiment bne = new BuffonNeedleExperiment();
//            bne.simulate(1_000_000);
//        });
        executor.submit(() -> {
            try {
                var gen1 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 123, 24147, 121412);
                var rn1 = new Range<Double>(gen1, 10.0, .1, 10);
                var gen2 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 411, 673, 122141);
                var rn2 = new Range<Double>(gen2, 20.0, .5, 12);
                var gen3 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 779, 213, 11243);
                var rn3 = new Range<Double>(gen3, 32.0, .2, 13);
                var gen4 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 1231, 221, 12411);
                var rn4 = new Range<Double>(gen4, 45.0, .15, 30);
                var gen5 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 19, 991, 112410);
                var rn5 = new Range<Double>(gen5, 75.0, .05, 10);
                ArrayList<Range<Double>> list = new ArrayList<>(Arrays.asList(rn1, rn2, rn3, rn4, rn5));
                var genMain = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 1231, 2134, 2134212);
                EmpiricDistribution<Double> black = new EmpiricDistribution<>(genMain, list);
                if (black == null) {
                    throw new RuntimeException();
                }
                ArrayList<Double> values = new ArrayList<>();
                for (int i = 0; i < 100_000; i++) {
                    values.add(black.getNext());
                }
                double[] doubles = values.stream().mapToDouble(Double::doubleValue).toArray();

                HistogramDataset dtset = new HistogramDataset();
                dtset.addSeries("Cierne hodnoty", doubles, 500);
                JFreeChart chart = ChartFactory.createHistogram(
                        "Histogram Title",
                        "Value",
                        "Frequency",
                        dtset
                );
                ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

                JFrame frame = new JFrame("Histogram of Random Numbers");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(chartPanel);
                frame.pack();
                frame.setVisible(true);

                Logger.info("Black values generated");
            }catch (Throwable t) {
                t.printStackTrace();
            }
        });
        executor.submit(() -> {
            try {
                var gen1 = new DiscreteDistGen(SeedManager.sm.getNextSeed(), 123, 3141, 14);
                var rn1 = new Range<Long>(gen1, 15L, .2, -1);
                var gen2 = new DiscreteDistGen(SeedManager.sm.getNextSeed(), 2131, 2141, 16);
                var rn2 = new Range<Long>(gen2, 29L, .4, -1);
                var gen3 = new DiscreteDistGen(SeedManager.sm.getNextSeed(), 2131, 991, 20);
                var rn3 = new Range<Long>(gen3, 45L, .4, -1);
                var list = new ArrayList<>(Arrays.asList(rn1, rn2, rn3));
                var genMain = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 1231451, 215901, 124112447);
                var blue = new EmpiricDistribution<Long>(genMain, list);
                ArrayList<Long> values = new ArrayList<>();
                for (int i = 0; i < 100_000; i++) {
                    values.add(blue.getNext());
                }
                Logger.info("Blue values generated");
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
        executor.shutdown();
    }
}