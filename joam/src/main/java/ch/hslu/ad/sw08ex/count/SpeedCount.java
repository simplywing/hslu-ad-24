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
package ch.hslu.ad.sw08ex.count;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Speed-Test für unterschiedlich impementierte Counters.
 */
public final class SpeedCount {

    private static final Logger LOG = LoggerFactory.getLogger(SpeedCount.class);

    /**
     * Privater Konstruktor.
     */
    private SpeedCount() {
    }

    /**
     * Test für einen Counter.
     *
     * @param counter Zählertyp.
     * @param counts  Anzahl Zähl-Vorgänge.
     * @param threads Anzahl Tester-Threads.
     * @return Dauer des Tests in mSec.
     */
    public static long speedTest(Counter counter, int counts, int threads) {
        long startTime = System.currentTimeMillis();

        List<Callable<Integer>> tasks = new ArrayList<>(threads);
        for (int i = 0; i < threads; i++) {
            tasks.add(new CountTask(counter, counts));
        }

        try (final ExecutorService executor = Executors.newCachedThreadPool()) {
            executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return System.currentTimeMillis() - startTime;
    }

    /**
     * Main-Counter-Test.
     *
     * @param args not used.
     */
    public static void main(final String[] args) {
        final int passes = 10;
        final int threads = 20;
        final int counts = 100_000;

        final Counter counterSync = new SynchronizedCounter();
        long sumSync = 0;
        // Do one more pass but ignore the time of the first pass
        for (int i = 0; i < passes + 1; i++) {
            long speedTestTime = speedTest(counterSync, counts, threads);
            if (i != 0) {
                sumSync += speedTestTime;
            }
        }

        final Counter counterAtom = new AtomicCounter();
        long sumAtom = 0;
        // Do one more pass but ignore the time of the first pass
        for (int i = 0; i < passes + 1; i++) {
            long speedTestTime = speedTest(counterAtom, counts, threads);
            if (i != 0) {
                sumAtom += speedTestTime;
            }
        }

        if (counterSync.get() != 0) {
            LOG.info("Sync counter failed");
            return;
        }
        if (counterAtom.get() != 0) {
            LOG.info("Atom counter failed");
            return;
        }

        double syncAvgDuration = sumSync / (double) passes;
        LOG.info("Sync counter average test duration = {} ms", syncAvgDuration);

        double atomAvgDuration = sumAtom / (double) passes;
        LOG.info("Atom counter average test duration = {} ms", atomAvgDuration);

        double atomPercentageFaster = 100 - (100 / syncAvgDuration) * atomAvgDuration;
        LOG.info("Atomic Counter was {} % faster", atomPercentageFaster);
    }
}
