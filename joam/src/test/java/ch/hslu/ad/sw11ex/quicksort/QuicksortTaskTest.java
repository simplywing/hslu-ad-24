package ch.hslu.ad.sw11ex.quicksort;

import ch.hslu.ad.sw09ex.TestData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuicksortTaskTest {
    public static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    void testQuickSortTaskCompute() {
        int[] arr = TestData.getSeededRandomIntArray(100_000, 0, Integer.MAX_VALUE, TestData.TEST_SEED);
        QuicksortTask task = new QuicksortTask(arr, 50);
        task.invoke();
        assertSorted(arr);
    }
}