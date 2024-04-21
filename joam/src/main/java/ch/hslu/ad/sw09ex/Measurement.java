package ch.hslu.ad.sw09ex;

import ch.hslu.ad.helper.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Measurement {

    private static final Logger LOG = LoggerFactory.getLogger(Measurement.class);

    public static void main(String[] args) {
        int[] arr0 = TestData.getSeededRandomIntArray(100_000, 0, 10_000, TestData.TEST_SEED);
        int[] arr1 = TestData.getSeededRandomIntArray(100_000, 0, 10_000, TestData.TEST_SEED);
        int[] arr2 = TestData.getSeededRandomIntArray(200_000, 0, 10_000, TestData.TEST_SEED);
        int[] arr3 = TestData.getSeededRandomIntArray(400_000, 0, 10_000, TestData.TEST_SEED);

        AsciiTable table = new AsciiTable();
        table.getColumns().add(new AsciiTable.Column("Iteration"));
        table.getColumns().add(new AsciiTable.Column("Element Count"));
        table.getColumns().add(new AsciiTable.Column("Sorting Time (ms)"));

        long startTime, endTime;

        LOG.info("Insertion Sort Benchmark");

        // Array 0 (Cold)
        LOG.info("Sorting arr0 (cold)...");
        startTime = System.currentTimeMillis();
        Sort.insertionSort(arr0);
        endTime = System.currentTimeMillis();

        AsciiTable.Row row0 = new AsciiTable.Row();
        table.getData().add(row0);
        row0.getValues().add("0 (Cold)");
        row0.getValues().add(String.valueOf(arr0.length));
        row0.getValues().add(String.valueOf(endTime - startTime));

        // Array 1
        LOG.info("Sorting arr1...");
        startTime = System.currentTimeMillis();
        Sort.insertionSort(arr1);
        endTime = System.currentTimeMillis();

        AsciiTable.Row row1 = new AsciiTable.Row();
        table.getData().add(row1);
        row1.getValues().add("1");
        row1.getValues().add(String.valueOf(arr1.length));
        row1.getValues().add(String.valueOf(endTime - startTime));

        // Array 2
        LOG.info("Sorting arr2...");
        startTime = System.currentTimeMillis();
        Sort.insertionSort(arr2);
        endTime = System.currentTimeMillis();

        AsciiTable.Row row2 = new AsciiTable.Row();
        table.getData().add(row2);
        row2.getValues().add("2");
        row2.getValues().add(String.valueOf(arr2.length));
        row2.getValues().add(String.valueOf(endTime - startTime));

        // Array 3
        LOG.info("Sorting arr3...");
        startTime = System.currentTimeMillis();
        Sort.insertionSort(arr3);
        endTime = System.currentTimeMillis();

        AsciiTable.Row row3 = new AsciiTable.Row();
        table.getData().add(row3);
        row3.getValues().add("3");
        row3.getValues().add(String.valueOf(arr3.length));
        row3.getValues().add(String.valueOf(endTime - startTime));

        // Display Results
        System.out.println();
        table.calculateColumnWidth();
        table.render();
    }
}
