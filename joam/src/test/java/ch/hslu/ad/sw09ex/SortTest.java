package ch.hslu.ad.sw09ex;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    public static void assertSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            assertTrue(array[i] >= array[i - 1]);
        }
    }

    @Test
    void testInsertionSort() {
        int[] data = {10, 8, 4, 2, 0, 11, 4, 2, 1, 6, 0, 3};

        Sort.insertionSort(data);

        System.out.println(Arrays.toString(data));
        assertSorted(data);
    }

    @Test
    void testInsertionSortRandomData() {
        int[] data = TestData.getRandomIntArray(30, 0, 1000);

        Sort.insertionSort(data);

        System.out.println(Arrays.toString(data));
        assertSorted(data);
    }

    @Test
    void testInsertionSortPseudoRandomData() {
        int[] data = TestData.getSeededRandomIntArray(30, 0, 1000, TestData.TEST_SEED);

        Sort.insertionSort(data);

        System.out.println(Arrays.toString(data));
        assertSorted(data);
    }
}