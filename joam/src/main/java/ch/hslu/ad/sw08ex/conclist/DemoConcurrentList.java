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
package ch.hslu.ad.sw08ex.conclist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Demonstration einer synchronisierten List mit n Producer und m Consumer.
 */
public final class DemoConcurrentList {

    private static final Logger LOG = LoggerFactory.getLogger(DemoConcurrentList.class);

    /**
     * Privater Konstruktor.
     */
    private DemoConcurrentList() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException                    wenn das Warten unterbrochen wird.
     * @throws java.util.concurrent.ExecutionException bei Execution-Fehler.
     */
    public static void main(final String[] args) throws InterruptedException, ExecutionException {
        final List<Integer> list = new LinkedList<>();
        var syncList = Collections.synchronizedList(list);

        final List<Future<Long>> futures = new ArrayList<>();
        try (final ExecutorService executor = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 3; i++) {
                futures.add(executor.submit(new Producer(syncList, 1000)));
            }
            Iterator<Future<Long>> iterator = futures.iterator();
            long totProd = 0;
            while (iterator.hasNext()) {
                long sum = iterator.next().get();
                totProd += sum;
                LOG.info("prod sum = {}", sum);
            }
            LOG.info("prod tot = {}", totProd);
            long totCons = executor.submit(new Consumer(syncList)).get();
            LOG.info("cons tot = {}", totCons);
        } finally {
            // Executor shutdown
        }
    }
}
