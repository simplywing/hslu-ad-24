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

import ch.hslu.ad.sw11ex.mergesort.InsertionSort;

import java.util.concurrent.RecursiveAction;

/**
 * Codevorlage zu RecursiveAction für die Sortierung eines int-Arrays.
 */
public final class QuicksortTask extends RecursiveAction {

    private static final int DEFAULT_THRESHOLD = 42;
    private final int[] array;
    private final int threshold;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sortier Task.
     *
     * @param array Integer-Array.
     */
    public QuicksortTask(int[] array) {
        this(array, QuicksortTask.DEFAULT_THRESHOLD);
    }

    public QuicksortTask(int[] array, final int threshold) {
        this(array, 0, array.length - 1, threshold);
    }

    private QuicksortTask(final int[] array, final int min, final int max, final int threshold) {
        this.array = array;
        this.threshold = threshold;
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        int left = this.min;
        int right = this.max;

        QuicksortTask t1 = null;
        QuicksortTask t2;

        if (right - left > this.threshold) {
            int pivotIndex = QuicksortRecursive.partition(this.array, left, right);

            if (left < (pivotIndex - 1)) {
                t1 = new QuicksortTask(this.array, left, (pivotIndex - 1), this.threshold);
                t1.fork();
            }

            if ((pivotIndex + 1) < right) {
                t2 = new QuicksortTask(this.array, (pivotIndex + 1), right, this.threshold);
                t2.invoke();
            }

            if (null != t1) {
                t1.join();
            }
        } else {
            InsertionSort.exec(this.array, left, right);
        }
    }
}
