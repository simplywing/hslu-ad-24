package ch.hslu.ad.sw01ex;

import org.junit.jupiter.api.Test;

class TimeComplexityTest {
    @Test
    void testTask() {
        var t = new TimeComplexity();
        System.out.println(t.task(20));
    }

    @Test
    void testTaskTableResults() {
        var tc = new TimeComplexity();

        System.out.println("n-param\t\tmethodCallCount");
        System.out.println("-------\t\t---------------");

        for (int i = 1; i <= 512; i = i * 2) {
            var callCount = tc.task(i);
            System.out.printf("%s\t\t\t%s%n", i, callCount);
        }
    }

    @Test
    void testTestRunner() {
        var tc = new TimeComplexity();
        tc.taskRunner(19);
        tc.printTable();
    }
}