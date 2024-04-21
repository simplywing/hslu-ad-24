package ch.hslu.ad.sw09ex;

public final class Sort {

    /**
     * Optimized Insertion Sort
     *
     * @param arr Array of integers to be sorted in-place.
     */
    public static void insertionSort(final int[] arr) {
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
}
