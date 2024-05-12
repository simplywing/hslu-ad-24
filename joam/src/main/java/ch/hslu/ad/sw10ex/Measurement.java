package ch.hslu.ad.sw10ex;

import ch.hslu.ad.helper.AsciiTable;
import ch.hslu.ad.sw09ex.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class Measurement {

    private static final Logger LOG = LoggerFactory.getLogger(Measurement.class);

    public static void main(String[] args) {
        // Configure sorting data sizes
        final int[] testSizes = {5_000_000, 5_000_000, 10_000_000, 10_000_000};

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
            row.getValues().add(String.format("%d", testSizes[i]));

            // Measure all sorting methods
            for (SortMethod sortMethod : testSortingMethods) {
                if (!sortMethod.enabled()) {
                    continue;
                }

                int[] randArr = TestData.getSeededRandomIntArray(testSizes[i], 0, Integer.MAX_VALUE, TestData.TEST_SEED);
                int[] boundedRandArr = TestData.getSeededRandomIntArray(testSizes[i], 0, 25, TestData.TEST_SEED);
                int[] ascArr = TestData.getAscendingIntArray(testSizes[i]);
                int[] descArr = TestData.getDescendingIntArray(testSizes[i]);

                LOG.info("Sorting random {} elements{} with {}...", testSizes[i], (i == 0 ? " (Cold) " : ""), sortMethod.name());
                long randomTime = measureSort(sortMethod.method(), randArr);
                LOG.info("Sorting random (bounded 0-25) {} elements{} with {}...", testSizes[i], (i == 0 ? " (Cold) " : ""), sortMethod.name());
                long randomBoundedTime = measureSort(sortMethod.method(), boundedRandArr);
                LOG.info("Sorting ascending {} elements{} with {}...", testSizes[i], (i == 0 ? " (Cold) " : ""), sortMethod.name());
                long ascTime = measureSort(sortMethod.method(), ascArr);
                LOG.info("Sorting descending {} elements{} with {}...", testSizes[i], (i == 0 ? " (Cold) " : ""), sortMethod.name());
                long descTime = measureSort(sortMethod.method(), descArr);

                // Add measurement of this sorting method to Table
                row.getValues().add(String.format("%s, %s, %s, %s", randomTime, randomBoundedTime, ascTime, descTime));
            }
        }

        // Display Results
        System.out.println();
        System.out.println(" Measurements (ms): random, random bounded 0-25, sorted asc, sorted desc");
        System.out.println();
        resultTable.calculateColumnWidth();
        resultTable.render();
    }

    private static long measureSort(Consumer<int[]> sortFunction, int[] data) {
        long startTime = System.currentTimeMillis();
        sortFunction.accept(data);
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    private static List<SortMethod> getSortMethods() {
        final List<SortMethod> testSortingMethods = new ArrayList<>();
        testSortingMethods.add(new SortMethod(ch.hslu.ad.sw09ex.Sort::insertionSort, "Insertion Sort", false));
        testSortingMethods.add(new SortMethod(ch.hslu.ad.sw09ex.Sort::shellSort2, "Shell Sort 2", true));
        testSortingMethods.add(new SortMethod(Sort::quickSort, "Quick Sort", false));
        testSortingMethods.add(new SortMethod(Sort::quickSort2, "Quick Sort 2", true));
        testSortingMethods.add(new SortMethod(Sort::quickInsertionSort, "Quick Insertion Sort", true));
        testSortingMethods.add(new SortMethod(Sort::heapSort, "Heap Sort", true));
        testSortingMethods.add(new SortMethod(Sort::arraysSort, "Arrays Sort", true));

        return testSortingMethods;
    }

    private record SortMethod(Consumer<int[]> method, String name, boolean enabled) {
    }

}

