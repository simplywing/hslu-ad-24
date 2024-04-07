package ch.hslu.ad.sw06ex.quiz;

import java.util.concurrent.CountDownLatch;

public class TimeAssumption {
    private static final CountDownLatch countSignal = new CountDownLatch(1);
    private static int counter = 0;

    public static void main(String[] args) {

        System.out.println("Counter hat begonnen zu zählen");
        Thread incrementerThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter++;
            }
            countSignal.countDown();
        });

        Thread readerThread = new Thread(() -> {
            try {
                countSignal.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Counter ist fertig mit zählen, der wert ist: " + counter);
        });

        incrementerThread.start();
        readerThread.start();
    }
}
