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

    public static void arraysParallelSort(final int[] arr) {
        Arrays.parallelSort(arr);
    }

    public static void shellSort(final int[] arr) {
        int i, j, toInsert;
        final int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (i = gap; i < n; i++) {

                toInsert = arr[i];
                j = i;

                while (j >= gap && arr[j - gap] > toInsert) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }

                arr[j] = toInsert;
            }
        }
    }

    public static void shellSort2(final int[] arr) {
        // Ciura gap sequence
        // original sequence: 1, 4, 10, 23, 57, 132, 301, 701
        // extended with factor 2.25 and rounded to nearest int
        final int[] sequence = {1, 4, 10, 23, 57, 132, 301, 701, 1750, 3938, 8861, 19937, 44858, 100931, 227095, 510964,
                1149669, 2586755, 5820199, 13095448, 29464758, 66295706, 149165339, 335622013, 755149529, 1699086440};

        int i, j, toInsert;
        final int n = arr.length;
        final int nHalf = n / 2;

        for (int g = sequence.length - 1; g >= 0; g--) {
            if (sequence[g] > nHalf) {
                continue;
            }

            for (i = sequence[g]; i < n; i++) {

                toInsert = arr[i];
                j = i;

                while (j >= sequence[g] && arr[j - sequence[g]] > toInsert) {
                    arr[j] = arr[j - sequence[g]];
                    j -= sequence[g];
                }

                arr[j] = toInsert;
            }

        }
    }

    public static void shellSort3(final int[] arr) {
        // Hibbard gap sequence
        // generated with: 2^{k} -1
        final int[] sequence = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071,
                262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455,
                536870911, 1073741823, 2147483647};

        int i, j, toInsert;
        final int n = arr.length;
        final int nHalf = n / 2;

        for (int g = sequence.length - 1; g >= 0; g--) {
            if (sequence[g] > nHalf) {
                continue;
            }

            for (i = sequence[g]; i < n; i++) {

                toInsert = arr[i];
                j = i;

                while (j >= sequence[g] && arr[j - sequence[g]] > toInsert) {
                    arr[j] = arr[j - sequence[g]];
                    j -= sequence[g];
                }

                arr[j] = toInsert;
            }

        }
    }
}
