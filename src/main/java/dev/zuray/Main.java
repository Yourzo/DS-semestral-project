package dev.zuray;

import dev.zuray.generators.*;
import dev.zuray.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
                var gen1 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 123441, 24141237, 121412);
                var rn1 = new Range<Double>(gen1, 20.0, .2, 20);
                var gen2 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 411312, 671113, 122141);
                var rn2 = new Range<Double>(gen2, 40.0, .4, 10);
                var gen3 = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 771231219, 21124113, 1114142243);
                var rn3 = new Range<Double>(gen3, 0.0, .4, 20);
                ArrayList<Range<Double>> list = new ArrayList<>(Arrays.asList(rn1, rn2, rn3));
                var genMain = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 1231, 2134, 2134212);
                EmpiricDistribution<Double> black = new EmpiricDistribution<>(genMain, list);
                if (black == null) {
                    throw new RuntimeException();
                }
                ArrayList<Double> values = new ArrayList<>();
                for (int i = 0; i < 1_000_000; i++) {
                    values.add(black.getNext());
                }
                double[] doubles = values.stream().mapToDouble(Double::doubleValue).toArray();

                HistogramDataset dtset = new HistogramDataset();
                dtset.addSeries("Generované hodnoty", doubles, 500);
                JFreeChart chart = ChartFactory.createHistogram(
                        "Spojité empirické rozdelenie",
                        "Hodnota",
                        "Frekvencia",
                        dtset
                );
                File file = new File("chart1.png");
                try {
                    ChartUtils.saveChartAsPNG(file, chart, 1080, 720);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ChartPanel chartPanel = new ChartPanel(chart);

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
                var gen1 = new DiscreteDistGen(SeedManager.sm.getNextSeed(), 512412512, 1241252551, Integer.MAX_VALUE);
                var rn1 = new Range<Long>(gen1, 0L, .2, 10);
                var gen2 = new DiscreteDistGen(SeedManager.sm.getNextSeed(), 215612613, 215125211, Integer.MAX_VALUE);
                var rn2 = new Range<Long>(gen2, 10L, .4, 15);
                var gen3 = new DiscreteDistGen(SeedManager.sm.getNextSeed(), 221416313, 1251252167, Integer.MAX_VALUE);
                var rn3 = new Range<Long>(gen3, 25L, .4, 10);
                var list = new ArrayList<>(Arrays.asList(rn1, rn2, rn3));
                var genMain = new ContinuousDistGen(SeedManager.sm.getNextSeed(), 1231451, 215901, 124112447);
                var blue = new EmpiricDistribution<Long>(genMain, list);
                ArrayList<Long> values = new ArrayList<>();
                for (int i = 0; i < 1_000_000; i++) {
                    values.add(blue.getNext());
                }
                double[] measurements = values.stream().mapToDouble(Long::doubleValue).toArray();

                HistogramDataset dtset = new HistogramDataset();
                dtset.addSeries("Generované hodnoty", measurements, 35);
                JFreeChart chart = ChartFactory.createHistogram(
                        "Diskrétne empirické rozdelenie",
                        "Hodnota",
                        "Frekvencia",
                        dtset
                );
                File file = new File("chart2.png");
                XYPlot plot = (XYPlot) chart.getPlot();
                XYBarRenderer render = (XYBarRenderer) plot.getRenderer();
                render.setSeriesPaint(0, Color.BLUE);
                render.setMargin(0.05);
                try {
                    ChartUtils.saveChartAsPNG(file, chart, 1080, 720);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Logger.info("Blue values generated");
            } catch (Throwable t) {
                t.printStackTrace();
            }
        });
        executor.shutdown();
    }
}