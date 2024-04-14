package ch.hslu.ad.sw08ex.count;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtomicCounterTest {

    @Test
    void testIncrement() {
        AtomicCounter ac = new AtomicCounter();
        ac.increment();
        ac.increment();
        assertEquals(2, ac.get());
    }

    @Test
    void testDecrement() {
        AtomicCounter ac = new AtomicCounter();
        ac.decrement();
        ac.decrement();
        assertEquals(-2, ac.get());
    }
}