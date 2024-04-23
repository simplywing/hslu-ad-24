package ch.hslu.ad.sw09ex;

import java.util.Arrays;

public final class Sort {

    /**
     * Classic Insertion Sort
     *
     * @param arr Array of integers to be sorted in-place.
     */
    public static void insertionSort(final int[] arr) {
        int toInsert;
        int j;
        for (int i = 0; i < arr.length; i++) {
            toInsert = arr[i];

            j = i;
            // shift right until next element is not larger than what we want to insert.
            // or we reached the left end of the array
            while (j > 0 && arr[j - 1] > toInsert) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = toInsert;
        }
    }

    /**
     * Optimized Insertion Sort
     *
     * @param arr Array of integers to be sorted in-place.
     */
    public static void insertionSort2(final int[] arr) {
        int toInsert;
        int j, k;
        int iZero = arr[0];

        for (int i = 1; i < arr.length; i++) {
            toInsert = arr[i];

            // copy the element to insert to the zero index to eliminate one comparison
            // in the shift right while loop. (dummy)
            arr[0] = toInsert;

            j = i;
            // shift right until next element is not larger than what we want to insert.
            while (arr[j - 1] > toInsert) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = toInsert;
        }

        // Now the array is sorted except at index zero.
        // We now have to insert the index zero element at the correct spot.
        // As long as the next element is smaller than the element of index zero,
        // keep shifting left
        // we can safely overwrite arr[0], because this was the dummy
        k = 0;
        while ((k < arr.length - 1) && (arr[k + 1] < iZero)) {
            arr[k] = arr[k + 1];
            k++;
        }
        arr[k] = iZero;
    }

    public static void selectionSort(final int[] arr) {
        int toSwap;
        int iSmallest;

        for (int i = 0; i < arr.length; i++) {
            toSwap = arr[i];
            iSmallest = i;

            for (int j = i; j < arr.length; j++) {
                if (arr[j] <= arr[iSmallest]) {
                    iSmallest = j;
                }
            }

            arr[i] = arr[iSmallest];
            arr[iSmallest] = toSwap;
        }
    }

    public static void bubbleSort(final int[] arr) {
        int tmp;
        boolean swapped;

        for (int j = 0; j < arr.length; j++) {
            swapped = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    tmp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }

    public static void bubbleSort2(final int[] arr) {
        int tmp;
        boolean swapped;

        while (true) {
            swapped = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i - 1] > arr[i]) {
                    tmp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) {
                return;
            }
        }
    }

    public static void test(final int[] arr) {
        Arrays.parallelSort(arr);
    }
}
