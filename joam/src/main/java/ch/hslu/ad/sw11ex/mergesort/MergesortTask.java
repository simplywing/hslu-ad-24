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
package ch.hslu.ad.sw11ex.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Codebeispiel zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public final class MergesortTask extends RecursiveAction {

    private static final int DEFAULT_THRESHOLD = 50;
    private final int[] array;
    private final int min;
    private final int max;
    private final int threshold;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Interger-Array.
     */
    public MergesortTask(final int[] array) {
        this(array, DEFAULT_THRESHOLD);
    }

    public MergesortTask(final int[] array, final int threshold) {
        this(array, 0, array.length - 1, threshold);
    }

    private MergesortTask(final int[] array, final int min, final int max, final int threshold) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (max - min <= this.threshold) {
            InsertionSort.exec(array, min, max);
        } else {
            final int mid = min + (max - min) / 2;
            invokeAll(new MergesortTask(array, min, mid, this.threshold), new MergesortTask(array, mid + 1, max, this.threshold));
            merge(min, mid, max);
        }
    }

//    public void merge(final int min, final int mid, final int max) {
//        // vordere Hälfte von array in Hilfsarray buf kopieren
//        int[] buf = Arrays.copyOfRange(array, min, mid + 1);
//        int i = 0;
//        int j = min;
//        int k = mid + 1;
//        while (i < buf.length) {
//            // jeweils das nächstgrösste Element zurückkopieren
//            // bei k == max, Rest von buf zurückkopieren, falls vorhanden
//            if (k == max || buf[i] < array[k]) {
//                array[j] = buf[i];
//                i++;
//            } else {
//                array[j] = array[k];
//                k++;
//            }
//            j++;
//        }
//    }

    private void merge(int min, int mid, int max) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - min + 1;
        int n2 = max - mid;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        System.arraycopy(this.array, min, L, 0, n1);
        System.arraycopy(this.array, mid + 1, R, 0, n2);

        // Initial indices of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = min;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                this.array[k] = L[i];
                i++;
            } else {
                this.array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            this.array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            this.array[k] = R[j];
            j++;
            k++;
        }
    }
}
