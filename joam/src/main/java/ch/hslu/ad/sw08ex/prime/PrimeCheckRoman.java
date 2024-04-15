package ch.hslu.ad.sw08ex.prime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;

public class PrimeCheckRoman {

    private static final Logger LOG = LoggerFactory.getLogger(PrimeCheck.class);
    private static final int THREAD_COUNT = 7;
    private static final int PRIME_COUNT = 100;
    private static final int CHECK_COUNT = 1000000;

    /**
     * Privater Konstruktor.
     */
    private PrimeCheckRoman() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        var blockingQueue = new ArrayBlockingQueue<BigInteger>(PRIME_COUNT);

        try (var executor = Executors.newFixedThreadPool(THREAD_COUNT)) {
            for (int i = 0; i < CHECK_COUNT; i++) {
                executor.execute(() -> {
                    BigInteger bi = new BigInteger(1024, new Random());
                    if (bi.isProbablePrime(Integer.MAX_VALUE)) {
                        try {
                            blockingQueue.put(bi);
                        } catch (InterruptedException e) {
                            LOG.error(e.getMessage());
                        }
                    }
                });
            }

            int n = 0;
            while (n < PRIME_COUNT) {
                try {
                    var prime = blockingQueue.take();
                    LOG.info("{} : {}...", n, prime.toString().substring(0, 20));
                    n++;
                } catch (InterruptedException e) {
                    LOG.error(e.getMessage());
                }
            }
            executor.shutdownNow();
        } finally {

        }


        long endTime = System.currentTimeMillis();

        LOG.info("Duration {} ms", endTime - startTime);
    }

}
