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
package ch.hslu.ad.sw08ex.prime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 100 grosse Primzahlen finden.
 */
public final class PrimeCheck {

    private static final Logger LOG = LoggerFactory.getLogger(PrimeCheck.class);

    private static final List<Future<BigInteger>> futures = new ArrayList<>();

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        boolean singleThreaded = false;
        int threadCount = singleThreaded ? 1 : Runtime.getRuntime().availableProcessors() + 1;
        int primeCount = 100;

        long startTime = System.currentTimeMillis();
        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            for (int i = 0; i < primeCount; i++) {
                futures.add(executor.submit(new PrimeSearcher()));
            }
            for (Future<BigInteger> future : futures) {
                LOG.info("Prime: {}...", future.get().toString().substring(0, 20));
            }
            long endTime = System.currentTimeMillis();
            LOG.info("Calculated {} Primes in {} Seconds using {} Threads", primeCount, ((double) endTime - startTime) / 1000, threadCount);
        } catch (ExecutionException | InterruptedException e) {
            LOG.error(e.getMessage());
        }
    }

    private static class PrimeSearcher implements Callable<BigInteger> {
        @Override
        public BigInteger call() {
            while (true) {
                BigInteger bi = new BigInteger(1024, new Random());
                if (bi.isProbablePrime(Integer.MAX_VALUE)) {
                    return bi;
                }
            }
        }
    }
}
