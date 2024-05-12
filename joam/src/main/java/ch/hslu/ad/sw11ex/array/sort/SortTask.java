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
package ch.hslu.ad.sw11ex.array.sort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

/**
 * Codebeispiel zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public final class SortTask extends RecursiveAction {

    private static final int THRESHOLD = 5;
    private final int[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Integer-Array.
     */
    public SortTask(final int[] array) {
        this(array, 0, array.length);
    }

    private SortTask(final int[] array, final int min, final int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        if (max - min < THRESHOLD) {
            sortSequentially(min, max);
        } else {
            final int mid = min + (max - min) / 2;
            invokeAll(new SortTask(array, min, mid), new SortTask(array, mid, max));
            merge(min, mid, max);
        }
    }

    private void sortSequentially(final int min, final int max) {
        //Arrays.sort(array, min, max);
        //Arrays.parallelSort(array, min, max);
        InsertionSort.exec(array, min, max);
    }

    private void merge(final int min, int mid, int max) {
        // vordere Hälfte von array in Hilfsarray buf kopieren
        int[] buf = Arrays.copyOfRange(array, min, mid);
        int i = 0;
        int j = min;
        int k = mid;
        while (i < buf.length) {
            // jeweils das nächstgrösste Element zurückkopieren
            // bei k == max, Rest von buf zurückkopieren, falls vorhanden
            if (k == max || buf[i] < array[k]) {
                array[j] = buf[i];
                i++;
            } else {
                array[j] = array[k];
                k++;
            }
            j++;
        }
    }
}
