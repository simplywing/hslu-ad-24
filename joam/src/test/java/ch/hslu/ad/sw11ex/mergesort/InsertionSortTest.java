package ch.hslu.ad.sw11ex.mergesort;

import ch.hslu.ad.sw09ex.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {
    public static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    void testInsertionSortExec() {
        int[] arr = TestData.getSeededRandomIntArray(10_000, 0, Integer.MAX_VALUE, TestData.TEST_SEED);
        InsertionSort.exec(arr, 0, arr.length - 1);
        assertSorted(arr);
    }
}