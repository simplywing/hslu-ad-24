package ch.hslu.ad.sw06ex.signal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SemaphoreTest {

    @Test
    void testSemaphoreNoArgumentsNoLimit() {
        Semaphore mySema = new Semaphore();
        assertEquals(0, mySema.getLimit());
        assertEquals(0, mySema.pending());
    }

    @Test
    void testSemaphoreWithLimit() throws InterruptedException {
        //create Semaphore and acquire it immediately.
        Semaphore mySema = new Semaphore(1, 1);
        assertEquals(1, mySema.getLimit());
        mySema.acquire();

        //start a Thread to acquire the semaphore
        Thread.startVirtualThread(new SemaphoreAcquirer(mySema, 1));
        Thread.sleep(1000);

        //now one thread should be pending/waiting
        assertEquals(1, mySema.pending());

        //release the semaphore to give it to the virtual thread
        mySema.release();
        Thread.sleep(1000);

        //verify that the virtual thread acquired the semaphore and no thread is waiting.
        assertEquals(0, mySema.pending());
    }

    @Test
    void testSemaphoreReleaseException() {
        Semaphore mySema = new Semaphore(1, 1);
        assertThrows(IllegalArgumentException.class, mySema::release);
    }

    @Test
    void testSemaphoreReleaseNoLimit() {
        Semaphore mySema = new Semaphore(1, 0);
        assertDoesNotThrow(() -> {
            mySema.release();
            mySema.release();
            mySema.release();
        });
    }

    @Test
    void testSemaphoreMultipleReleaseMultipleAcquire() {
        Semaphore mySema = new Semaphore(0, 3);
        assertDoesNotThrow(() -> {
            mySema.release(3);
            mySema.acquire(3);
        });
    }

    @Test
    void testSemaphoreMultipleAcquireOverLimit() {
        Semaphore mySema = new Semaphore(3, 3);
        assertThrows(IllegalArgumentException.class, () -> {
            mySema.acquire(4);
        });
    }

    @Test
    void testSemaphoreMultipleAcquireWait() throws InterruptedException {
        Semaphore mySema = new Semaphore(3, 3);
        mySema.acquire();

        Thread.startVirtualThread(new SemaphoreAcquirer(mySema, 3));
        Thread.sleep(1000);
        assertEquals(3, mySema.pending());

        mySema.release();
        Thread.sleep(1000);
        assertEquals(0, mySema.pending());
    }

    private static record SemaphoreAcquirer(Semaphore testSemaphore, int permits) implements Runnable {
        @Override
        public void run() {
            try {
                this.testSemaphore.acquire(permits);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}