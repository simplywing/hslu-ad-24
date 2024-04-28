package ch.hslu.ad.sw09ex;

import ch.hslu.ad.helper.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class Measurement {

    private static final Logger LOG = LoggerFactory.getLogger(Measurement.class);

    public static long measureSort(Consumer<int[]> sortFunction, int[] data) {
        long startTime = System.currentTimeMillis();
        sortFunction.accept(data);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) {
        // Configure sorting data sizes
//        final int[] testSizes = {100_000, 100_000, 100_000, 200_000, 400_000};
//        final int[] testSizes = {100_000, 100_000};
        final int[] testSizes = {50_000, 50_000, 100_000, 200_000};

        // Configure sorting methods to measure
        final List<SortMethod> testSortingMethods = getSortMethods();

        // Create Ascii Table to show results
        AsciiTable resultTable = new AsciiTable();
        resultTable.getColumns().add(new AsciiTable.Column("Iteration"));
        resultTable.getColumns().add(new AsciiTable.Column("Element Count"));
        for (SortMethod sortMethod : testSortingMethods) {
            if (sortMethod.enabled()) {
                resultTable.getColumns().add(new AsciiTable.Column(sortMethod.name()));
            }
        }

        for (int i = 0; i < testSizes.length; i++) {

            // Add the Table row
            AsciiTable.Row row = new AsciiTable.Row();
            resultTable.getData().add(row);
            row.getValues().add(i + (i == 0 ? " (Cold) " : ""));
            row.getValues().add(String.valueOf(testSizes[i]));

            // Measure all sorting methods
            for (SortMethod sortMethod : testSortingMethods) {
                if (!sortMethod.enabled()) {
                    continue;
                }
                int[] randArr = TestData.getSeededRandomIntArray(testSizes[i], 0, 10_000, TestData.TEST_SEED);
                int[] ascArr = TestData.getAscendingIntArray(testSizes[i]);
                int[] descArr = TestData.getDescendingIntArray(testSizes[i]);

                LOG.info("Sorting random {} elements{} with {}...", testSizes[i], (i == 0 ? " (Cold) " : ""), sortMethod.name());
                long randomTime = measureSort(sortMethod.method(), randArr);
                LOG.info("Sorting ascending {} elements{} with {}...", testSizes[i], (i == 0 ? " (Cold) " : ""), sortMethod.name());
                long ascTime = measureSort(sortMethod.method(), ascArr);
                LOG.info("Sorting descending {} elements{} with {}...", testSizes[i], (i == 0 ? " (Cold) " : ""), sortMethod.name());
                long descTime = measureSort(sortMethod.method(), descArr);

                // Add measurement of this sorting method to Table
                row.getValues().add(String.format("%s, %s, %s", randomTime, ascTime, descTime));
            }
        }

        // Display Results
        System.out.println();
        System.out.println(" Measurements (ms): random, sorted asc, sorted desc");
        System.out.println();
        resultTable.calculateColumnWidth();
        resultTable.render();
    }

    private static List<SortMethod> getSortMethods() {
        final List<SortMethod> testSortingMethods = new ArrayList<>();
        testSortingMethods.add(new SortMethod(Sort::insertionSort, "Insertion Sort", true));
        testSortingMethods.add(new SortMethod(Sort::insertionSort2, "Opt. Insertion Sort", false));
        testSortingMethods.add(new SortMethod(Sort::selectionSort, "Selection Sort", true));
        testSortingMethods.add(new SortMethod(Sort::bubbleSort, "Bubble Sort", true));
        testSortingMethods.add(new SortMethod(Sort::bubbleSort, "Bubble Sort 2", false));
        testSortingMethods.add(new SortMethod(Sort::test, "Parallel Sort", false));
        return testSortingMethods;
    }

    private record SortMethod(Consumer<int[]> method, String name, boolean enabled) {
    }

}

