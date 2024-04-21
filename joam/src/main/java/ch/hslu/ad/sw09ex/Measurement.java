package ch.hslu.ad.sw09ex;

import ch.hslu.ad.helper.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Measurement {

    private static final Logger LOG = LoggerFactory.getLogger(Measurement.class);

    public static void main(String[] args) {
        final int[] testSizes = {100_000, 100_000, 100_000, 200_000, 400_000};

        AsciiTable table = new AsciiTable();
        table.getColumns().add(new AsciiTable.Column("Iteration"));
        table.getColumns().add(new AsciiTable.Column("Element Count"));
        table.getColumns().add(new AsciiTable.Column("Sorting Time (ms)"));

        long startTime, endTime;

        LOG.info("Insertion Sort Benchmark");

        for (int i = 0; i < testSizes.length; i++) {
            int[] arr = TestData.getSeededRandomIntArray(testSizes[i], 0, 10_000, TestData.TEST_SEED);

            LOG.info("Sorting {} elements{}...", testSizes[i], (i == 0 ? " (Cold) " : ""));
            startTime = System.currentTimeMillis();
            Sort.insertionSort(arr);
            endTime = System.currentTimeMillis();

            AsciiTable.Row row = new AsciiTable.Row();
            table.getData().add(row);
            row.getValues().add(i + (i == 0 ? " (Cold) " : ""));
            row.getValues().add(String.valueOf(arr.length));
            row.getValues().add(String.valueOf(endTime - startTime));
        }

        // Display Results
        System.out.println();
        table.calculateColumnWidth();
        table.render();
    }
}
