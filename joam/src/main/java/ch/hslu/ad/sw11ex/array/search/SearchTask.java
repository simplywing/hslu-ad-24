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
package ch.hslu.ad.sw11ex.array.search;

import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Codebeispiel zu CountedCompleter für die Suche eines Elementes in einem
 * int-Array.
 */
public final class SearchTask extends CountedCompleter<Integer> {

    private static final int SEGMENTS = 4;
    private final int key;
    private final int[] array;
    private final int segmentNo;
    private final AtomicInteger result;

    /**
     * Erzeugt einen Array-Such Task.
     *
     * @param key   zu findendes Element.
     * @param array Integer-Array.
     */
    public SearchTask(final int key, final int[] array) {
        this(null, key, array, -1, new AtomicInteger(-1));
    }

    private SearchTask(final CountedCompleter<?> parent, final int key,
                       final int[] array, final int segmentNo,
                       final AtomicInteger result) {
        super(parent);
        this.key = key;
        this.array = array;
        this.segmentNo = segmentNo;
        this.result = result;
    }

    @Override
    public Integer getRawResult() {
        return result.get();
    }

    @Override
    public void compute() {
        if (segmentNo >= 0) {
            int segment = this.array.length / SearchTask.SEGMENTS;
            int min = segmentNo * segment;
            int max = min + segment;
            for (int i = min; i < max; i++) {
                if (i < this.array.length && array[i] == key && result.compareAndSet(-1, i)) {
                    this.quietlyCompleteRoot();
                    break;
                }
            }
        } else {
            this.addToPendingCount(SearchTask.SEGMENTS);
            for (int i = 0; i < SearchTask.SEGMENTS + 1; i++) {
                new SearchTask(this, key, array, i, result).fork();
            }
        }
        this.tryComplete();
    }
}
