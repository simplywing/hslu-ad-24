package ch.hslu.ad.sw01ex;

import ch.hslu.ad.helper.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TimeComplexity {

    private int methodCallCount = 0;

    private final List<TCResult> resultList = new ArrayList<>();

    private record TCResult(int n, int methodCallCount) {
        public float getCallCountIncreaseFactor(final TCResult prev) {
            return (float) this.methodCallCount() / prev.methodCallCount();
        }

        public float getNIncreaseFactor(final TCResult prev) {
            return (float) this.n() / prev.n();
        }
    }

    private static Logger LOG = LoggerFactory.getLogger(TimeComplexity.class);

    public int getMethodCallCount() {
        return methodCallCount;
    }

    private void resetMethodCallCount() {
        this.methodCallCount = 0;
    }

    public int task(final int n) {
        this.resetMethodCallCount();

        task1();
        task1();
        task1();
        task1(); // T ~ 4
        for (int i = 0; i < n; i++) { // äussere Schleife: n-mal
            task2();
            task2();
            task2(); // T ~ n · 3
            for (int j = 0; j < n; j++) { // innerer Schleife: n-mal
                task3();
                task3(); // T ~ n · n· 2
            }
        }

        var myMethodCallCount = this.getMethodCallCount();
        this.resetMethodCallCount();
        return myMethodCallCount;
    }

    /**
     * Runs the "task" method multiple times and collects methodCall statistics.
     *
     * @param iterations specify how many times task() should be run, doubling n every time, starting from 1
     */
    public void taskRunner(final int iterations) {
        int n = 1;
        for (int i = 0; i < iterations; i++) {
            this.resultList.add(new TCResult(n, this.task(n)));
            n = n * 2;
        }
    }

    public void printTable() {

        AsciiTable table = new AsciiTable();
        table.setMaxColumnWidth(50);

        table.getColumns().add(new AsciiTable.Column("iteration"));
        table.getColumns().add(new AsciiTable.Column("n-param"));
        table.getColumns().add(new AsciiTable.Column("methodCallCount"));
        table.getColumns().add(new AsciiTable.Column("n-increaseFactor"));
        table.getColumns().add(new AsciiTable.Column("methodCallIncreaseFactor"));

        for (int i = 0; i < this.resultList.size(); i++) {
            AsciiTable.Row row = new AsciiTable.Row();
            table.getData().add(row);
            row.getValues().add(String.valueOf(i + 1));
            row.getValues().add(String.valueOf(this.resultList.get(i).n()));
            row.getValues().add(String.valueOf(this.resultList.get(i).methodCallCount()));

            if (i > 0) {
                row.getValues().add(String.valueOf(
                        this.resultList.get(i).getNIncreaseFactor(this.resultList.get(i - 1)))
                );
                row.getValues().add(String.valueOf(
                        this.resultList.get(i).getCallCountIncreaseFactor(this.resultList.get(i - 1)))
                );
            }
        }

        table.calculateColumnWidth();
        table.render();
    }

    private void task1() {
        methodCallCount++;
    }

    private void task2() {
        task1();
    }

    private void task3() {
        task1();
    }
}