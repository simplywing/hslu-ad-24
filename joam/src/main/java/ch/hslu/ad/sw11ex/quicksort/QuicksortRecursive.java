/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.sw11ex.quicksort;

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public final class QuicksortRecursive {

    /**
     * Public method exposed to client, sorts given array using QuickSort
     * Algorithm in Java.
     *
     * @param array input array.
     */
    public static void quicksort(int[] array) {
        QuicksortRecursive.quicksort(array, 0, array.length - 1);
    }

    /**
     * Recursive quicksort logic.
     *
     * @param arr   input array.
     * @param left  start index of the array.
     * @param right end index of the array.
     */
    public static void quicksort(int[] arr, int left, int right) {
        int up = left;
        int down = right - 1;
        int pivotIndex = ((right - left) / 2) + left;
        swap(arr, pivotIndex, right); //move the pivot out of the way
        int pivot = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < pivot) {
                up++;
            }
            // now we also swap elements of same size (optimization)
            while ((arr[down] > pivot) && (down > up)) {
                down--;
            }
            if (down > up) {
                swap(arr, up, down);
                up++;
                down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        swap(arr, up, right); //put pivot at correct position
        if (left < (up - 1)) {
            quicksort(arr, left, (up - 1));
        }
        if ((up + 1) < right) {
            quicksort(arr, (up + 1), right);
        }
    }

    /**
     * Divides array from pivot, left side contains elements less than Pivot
     * while right side contains elements greater than pivot.
     *
     * @param arr   array to partitioned.
     * @param left  lower bound of the array.
     * @param right upper bound of the array.
     * @return the partition index.
     */
    public static int partition(int[] arr, int left, int right) {
        int up = left;
        int down = right - 1;
        int pivotIndex = ((right - left) / 2) + left;
        swap(arr, pivotIndex, right); //move the pivot out of the way
        int pivot = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < pivot) {
                up++;
            }
            // now we also swap elements of same size (optimization)
            while ((arr[down] > pivot) && (down > up)) {
                down--;
            }
            if (down > up) {
                swap(arr, up, down);
                up++;
                down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        swap(arr, up, right); //put pivot at correct position
        return up;
    }

    private static void swap(final int[] array, final int i, final int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
