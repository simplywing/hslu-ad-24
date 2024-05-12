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
package ch.hslu.ad.sw11ex.array.sum;

import java.util.concurrent.RecursiveTask;

/**
 * Codebeispiel zu RecursiveTask für die Summierung eines int-Arrays.
 */
@SuppressWarnings("serial")
public final class SumTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 4;
    private final int[] array;
    private final int min;
    private final int max;

    /**
     * Erzeugt einen Array-Sumierer Task.
     *
     * @param array Interger-Array.
     */
    public SumTask(final int[] array) {
        this(array, 0, array.length);
    }

    private SumTask(final int[] array, final int min, final int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if ((max - min) <= THRESHOLD) {
            for (int i = min; i < max; i++) {
                sum += array[i];
            }
        } else {
            final int mid = min + (max - min) / 2;
            final SumTask taskLeft = new SumTask(array, min, mid);
            taskLeft.fork();
            final SumTask taskRight = new SumTask(array, mid, max);
            sum = taskRight.invoke() + taskLeft.join();
        }
        return sum;
    }
}
