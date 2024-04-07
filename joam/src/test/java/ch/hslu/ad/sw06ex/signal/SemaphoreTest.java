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
        Thread.startVirtualThread(new SemaphoreAcquirer(mySema));
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

    private static class SemaphoreAcquirer implements Runnable {
        private final Semaphore testSemaphore;

        SemaphoreAcquirer(Semaphore sema) {
            this.testSemaphore = sema;
        }

        @Override
        public void run() {
            try {
                testSemaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}