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
package ch.hslu.ad.sw11ex.array;

import ch.hslu.ad.sw11ex.array.init.RandomInitTask;
import ch.hslu.ad.sw11ex.array.sum.SumTask;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Codebeispiel für die Verwendung von verschiedenen Recursive-Klassen mit einem
 * Fork-Join-Pool.
 */
public final class DemoArraySum {

    private static final Logger LOG = LoggerFactory.getLogger(DemoArraySum.class);

    /**
     * Privater Konstruktor.
     */
    private DemoArraySum() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int size = 42;
        final int[] array = new int[size];
        try (final ForkJoinPool pool = new ForkJoinPool()) {
            final RandomInitTask initTask = new RandomInitTask(array, 100);
            pool.invoke(initTask);
            LOG.info("Random init. : {}", Arrays.toString(array));
            SumTask sumTask = new SumTask(array);
            long result = pool.invoke(sumTask);
            LOG.info("Arraysum     : {}", result);
        } finally {
            // Executor shutdown
        }
    }
}
