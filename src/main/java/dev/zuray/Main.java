package dev.zuray;

import dev.zuray.generators.*;
import dev.zuray.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
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
                EmpiricDistribution<Double> black = new EmpiricDistribution<>(
                        new EmpiricDistribution.RangeData<>(20., 20., .2),
                        new EmpiricDistribution.RangeData<>(40., 10., .4),
                        new EmpiricDistribution.RangeData<>(0., 20., .4)
                );
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
                var blue = new EmpiricDistribution<Long>(
                        new EmpiricDistribution.RangeData<>(0L, 10L, .2),
                        new EmpiricDistribution.RangeData<>(10L, 15L, .4),
                        new EmpiricDistribution.RangeData<>(25L, 10L, .4)
                );
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