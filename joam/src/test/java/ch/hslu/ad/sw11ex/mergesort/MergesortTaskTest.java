package ch.hslu.ad.sw11ex.mergesort;

import ch.hslu.ad.sw09ex.TestData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergesortTaskTest {
    public static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    void testMergesortTaskCompute() {
        int[] arr = TestData.getSeededRandomIntArray(10_000, 0, Integer.MAX_VALUE, TestData.TEST_SEED);
        MergesortTask task = new MergesortTask(arr, 50);
        task.invoke();
        assertSorted(arr);
    }

}