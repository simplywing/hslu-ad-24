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

public final class InsertionSort {

    /**
     * Privater Konstruktor.
     */
    private InsertionSort() {
    }

    /**
     * Sortiert das int-Array aufsteigend. Zur Anwendung kommt der
     * Sortieralgorithmus "direktes Einfügen".
     *
     * @param array Interger-Array.
     * @param min   der Index des ersten zu sortierenden Elements
     *              (einschliesslich).
     * @param max   der Index des letzten exklusiven Elements, das sortiert werden
     *              soll.
     */
    public static void exec(final int[] array, final int min, final int max) {
        int elt;
        int j;

        for (int i = min; i < max; i++) {
            elt = array[i];      // next elt for insert
            j = i;               // array[min]..array[j-1] already sorted
            while ((j > min) && (array[j - 1] > elt)) {
                array[j] = array[j - 1]; // shift right
                j--;             // go further left
            }
            array[j] = elt;      // insert elt
        }                        // array[min]...array[max-1] sorted
    }

}
