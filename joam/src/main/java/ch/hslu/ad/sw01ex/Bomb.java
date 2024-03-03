package ch.hslu.ad.sw01ex;

/**
 * Ackermann Function
 */
public final class Bomb {
    private int methodCallCount = 0;

    Bomb() {
    }

    public int getMethodCallCount() {
        return methodCallCount;
    }

    public int ack(final int n, final int m) {
        this.methodCallCount++;
        if (n == 0) return m + 1;
        if (m == 0) return ack(n - 1, 1);
        else return ack(n - 1, ack(n, m - 1));
    }
}
