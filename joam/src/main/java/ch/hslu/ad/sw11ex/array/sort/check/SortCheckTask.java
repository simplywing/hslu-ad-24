/*
 * Copyright 2024 Hochschule Luzern - Informatik.
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
package ch.hslu.ad.sw11ex.array.sort.check;

import java.util.concurrent.RecursiveTask;

/**
 * Codebeispiel zu RecursiveTask für den Check, ob ein int-Array korrekt
 * sortiert wurde.
 */
public final class SortCheckTask extends RecursiveTask<Boolean> {

    private static final int THRESHOLD = 5;
    private final int[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sortier-Check Task.
     *
     * @param array zu überprüfendes Interger-Array.
     */
    public SortCheckTask(int[] array) {
        this(array, 0, array.length);
    }

    private SortCheckTask(int[] array, int min, int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected Boolean compute() {
        boolean result = true;
        if ((max - min) <= THRESHOLD) {
            for (int i = min; i < max - 1; i++) {
                if (array[i] > array[i + 1]) {
                    result = false;
                    break;
                }
            }
        } else {
            final int mid = min + (max - min) / 2;
            final SortCheckTask leftTask = new SortCheckTask(array, min, mid);
            leftTask.fork();
            final SortCheckTask rightTask = new SortCheckTask(array, mid, max);
            result = rightTask.invoke() && leftTask.join();
        }
        return result;
    }

}
