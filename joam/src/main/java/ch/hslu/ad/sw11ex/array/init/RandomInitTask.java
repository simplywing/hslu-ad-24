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
package ch.hslu.ad.sw11ex.array.init;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Codebeispiel zu RecursiveAction für die Initialisierung eines int-Arrays.
 */
public final class RandomInitTask extends RecursiveAction {

    private static final int THRESHOLD = 5;
    private final int[] array;
    private final int min;
    private final int max;
    private final int rdMax;

    /**
     * Erzeugt einen Teil-Array-Init Task.
     *
     * @param array    Interger-Array.
     * @param rndBound maximaler Random-Wert-1.
     */
    public RandomInitTask(final int[] array, final int rndBound) {
        this(array, 0, array.length, rndBound);
    }

    private RandomInitTask(final int[] array, final int min, final int max, final int rdBound) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.rdMax = rdBound;
    }

    @Override
    protected void compute() {
        if ((max - min) <= THRESHOLD) {
            for (int i = min; i < max; i++) {
                array[i] = ThreadLocalRandom.current().nextInt(rdMax);
            }
        } else {
            final int mid = min + (max - min) / 2;
            RandomInitTask left = new RandomInitTask(array, min, mid, rdMax);
            RandomInitTask right = new RandomInitTask(array, mid, max, rdMax);
            invokeAll(left, right);
        }
    }
}
