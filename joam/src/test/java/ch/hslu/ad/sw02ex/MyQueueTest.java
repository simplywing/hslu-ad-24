package ch.hslu.ad.sw02ex;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    private static Logger LOG = LoggerFactory.getLogger(MyQueueTest.class);

    @Test
    void testOffer() {
        var q = new MyQueue(10);
        LOG.info(q.toString());
        q.offer('a');
        LOG.info(q.toString());
        q.offer('b');
        LOG.info(q.toString());
        q.offer('c');
        LOG.info(q.toString());
        q.offer('d');
        LOG.info(q.toString());
        q.offer('e');
        LOG.info(q.toString());
        q.offer('f');
        LOG.info(q.toString());
        q.offer('g');
        LOG.info(q.toString());
        assertEquals(7, q.size());
    }

    @Test
    void pollSingleElement() {
        var q = new MyQueue(3);
        q.offer('a');
        q.offer('b');
        assertEquals('a', q.poll());
    }

    @Test
    void pollFromEmptyQueue() {
        var q = new MyQueue(10);
        assertThrows(IllegalStateException.class, q::poll);
    }

    @Test
    void offerToFullQueue() {
        var q = new MyQueue(2);
        q.offer('a');
        q.offer('b');
        assertThrows(IllegalStateException.class, () -> q.offer('c'));
    }

    @Test
    void testOfferAndPollWrapAround() {
        var q = new MyQueue(4);
        LOG.info(q.toString());
        q.offer('a');
        LOG.info(q.toString());
        q.offer('b');
        LOG.info(q.toString());
        q.offer('c');
        LOG.info(q.toString());
        q.offer('d');
        LOG.info(q.toString());
        q.poll();
        LOG.info(q.toString());
        q.poll();
        LOG.info(q.toString());
        q.offer('e');
        LOG.info(q.toString());
        q.offer('f');
        LOG.info(q.toString());
        q.poll();
        LOG.info(q.toString());
        q.poll();
        LOG.info(q.toString());
        q.offer('g');
        LOG.info(q.toString());
        q.offer('h');
        LOG.info(q.toString());
        assertEquals(4, q.size());
        assertEquals('e', q.poll());
        LOG.info(q.toString());
        assertEquals('f', q.poll());
        LOG.info(q.toString());
        assertEquals('g', q.poll());
        LOG.info(q.toString());
        assertEquals('h', q.poll());
        LOG.info(q.toString());
        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }
}