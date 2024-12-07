package ch.hslu.ad.misc;

import java.util.Collections;
import java.util.concurrent.*;

/**
 * Swap two int variables without a helper variable.
 */
public final class DemoSwap {
    public static void main(String[] args) {
        int x = 21;
        int y = 7;

        System.out.format("x: %s, y: %s\n", x, y);

        x = x + y;

        System.out.format("x: %s, y: %s\n", x, y);

        y = x - y;

        System.out.format("x: %s, y: %s\n", x, y);

        x = x - y;

        System.out.format("x: %s, y: %s", x, y);

        try (ExecutorService service = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<Integer> t = service.submit(new Callable<>() {
                @Override
                public Integer call() throws Exception {
                    return 0;
                }
            });

            System.out.println();
            System.out.println("Result from virtualThreadPool:");
            System.out.println(t.get());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
