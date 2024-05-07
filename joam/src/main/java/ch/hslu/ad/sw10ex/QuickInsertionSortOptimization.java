package ch.hslu.ad.sw10ex;

import ch.hslu.ad.helper.AsciiTable;
import ch.hslu.ad.sw09ex.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.AcceptPendingException;
import java.util.Arrays;
import java.util.function.BiConsumer;

public final class QuickInsertionSortOptimization {

    private static final int TEST_SIZE = 10_000_000;
    private static final int[] THRESHOLDS = {20, 30, 40, 50, 60, 70, 80, 85, 90, 95, 100, 120, 140, 160, 180};
    private static final int TEST_RETRIES = 3;

    private static final Logger LOG = LoggerFactory.getLogger(QuickInsertionSortOptimization.class);

    public static void main(String[] args) {
        AsciiTable table = new AsciiTable();
        table.getColumns().add(new AsciiTable.Column("Threshold"));
        table.getColumns().add(new AsciiTable.Column("Average Time (ms)"));

        for (int threshold : THRESHOLDS) {
            LOG.info("Sorting {} elements with threshold {} ({} retries)", TEST_SIZE, threshold, TEST_RETRIES);
            long[] measurements = new long[TEST_RETRIES];
            for (int i = -1; i < TEST_RETRIES; i++) {
                int[] testData = TestData.getRandomIntArray(TEST_SIZE, 0, Integer.MAX_VALUE);
                long measurement = measureSort(Sort::quickInsertionSort, testData, threshold);

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
