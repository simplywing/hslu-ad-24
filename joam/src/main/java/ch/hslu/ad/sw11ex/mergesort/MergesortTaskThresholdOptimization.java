package ch.hslu.ad.sw11ex.mergesort;

import ch.hslu.ad.helper.AsciiTable;
import ch.hslu.ad.sw09ex.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiConsumer;

public final class MergesortTaskThresholdOptimization {
    private static final int TEST_SIZE = 10_000_000;
    private static final int[] THRESHOLDS = {5, 10, 20, 40, 60, 70, 80, 90, 100, 120, 140, 160, 180, 250, 500};
    private static final int[] THRESHOLDS1 = {20, 25, 30, 35, 40, 45, 50, 55, 60};
    private static final int[] THRESHOLDS2 = {35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45};

    private static final int TEST_RETRIES = 10;

    private static final Logger LOG = LoggerFactory.getLogger(MergesortTaskThresholdOptimization.class);

    public static void main(String[] args) {
        AsciiTable table = new AsciiTable();
        table.getColumns().add(new AsciiTable.Column("Threshold"));
        table.getColumns().add(new AsciiTable.Column("Average Time (ms)"));

        try (ForkJoinPool pool = new ForkJoinPool()) {
            for (int threshold : THRESHOLDS) {
                LOG.info("Sorting {} elements with threshold {} ({} retries)", TEST_SIZE, threshold, TEST_RETRIES);
                long[] measurements = new long[TEST_RETRIES];
                for (int i = -1; i < TEST_RETRIES; i++) {

                    int[] testData = TestData.getSeededRandomIntArray(TEST_SIZE, 0, Integer.MAX_VALUE, TestData.TEST_SEED);

//                    long measurement = measureSort((arr, thresh) -> {
//                        var sort = new MergesortTask(arr, thresh);
//                        sort.invoke();
//                    }, testData, threshold);

                    long measurement = measureSort((arr, thresh) -> {
                        var sort = new MergesortTask(arr, thresh);
                        pool.invoke(sort);
                    }, testData, threshold);

                    // Ignore first result (cold)
                    if (i >= 0) {
                        measurements[i] = measurement;
                    }
                }

                double average = Arrays.stream(measurements).average().orElseThrow();
                AsciiTable.Row row = new AsciiTable.Row();
                table.getData().add(row);
                row.getValues().add(String.valueOf(threshold));
                row.getValues().add(String.valueOf(average));
            }
        }

        // Print Results
        System.out.println();
        table.calculateColumnWidth();
        table.render();
    }

    private static long measureSort(BiConsumer<int[], Integer> sortFunction, int[] data, final int threshold) {
        long startTime = System.currentTimeMillis();
        sortFunction.accept(data, threshold);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
