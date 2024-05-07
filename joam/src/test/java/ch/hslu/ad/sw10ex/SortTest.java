package ch.hslu.ad.sw10ex;

import ch.hslu.ad.sw09ex.TestData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    public static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    public static void assertSorted(char[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    void testQuickSort() {
        final int[] data = TestData.getRandomIntArray(500_000, 0, Integer.MAX_VALUE);
        Sort.quickSort(data);
        assertSorted(data);
    }

    @Test
    void testQuickSortSortedArray() {
        final int[] data = TestData.getAscendingIntArray(500_000);
        Sort.quickSort(data);
        assertSorted(data);
    }

    @Test
    void testQuickSort2() {
        final int[] data = TestData.getRandomIntArray(500_000, 0, Integer.MAX_VALUE);
        Sort.quickSort2(data);
        assertSorted(data);
    }

    @Test
    void testHeapSort() {
        final int[] data = TestData.getRandomIntArray(500_000, 0, Integer.MAX_VALUE);
        Sort.heapSort(data);
        assertSorted(data);
    }

    @Test
    void testQuickInsertionSort() {
        final int[] data = TestData.getRandomIntArray(500_000, 0, Integer.MAX_VALUE);
        Sort.quickInsertionSort(data);
        assertSorted(data);
    }
}