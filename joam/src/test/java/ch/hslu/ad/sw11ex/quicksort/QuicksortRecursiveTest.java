package ch.hslu.ad.sw11ex.quicksort;

import ch.hslu.ad.sw09ex.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuicksortRecursiveTest {
    public static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    void testQuicksort() {
        int[] arr = TestData.getSeededRandomIntArray(10_000, 0, Integer.MAX_VALUE, TestData.TEST_SEED);
        QuicksortRecursive.quicksort(arr);
        assertSorted(arr);
    }
}