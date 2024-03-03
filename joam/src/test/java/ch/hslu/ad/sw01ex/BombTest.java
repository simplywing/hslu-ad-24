package ch.hslu.ad.sw01ex;

import org.junit.jupiter.api.Test;

class BombTest {
    @Test
    void testAck2() {
        var b = new Bomb();
        int result = b.ack(1, 2);
        int calls = b.getMethodCallCount();

        System.out.println(String.format("Result: %s, Method Calls: %s", result, calls));
    }

    @Test
    void testAck3() {
        var b = new Bomb();
        int result = b.ack(2, 2);
        int calls = b.getMethodCallCount();

        System.out.println(String.format("Result: %s, Method Calls: %s", result, calls));
    }
}