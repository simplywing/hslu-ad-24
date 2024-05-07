package ch.hslu.ad.sw09ex;

import java.util.Random;

/**
 * Generate arrays with data for sorting tests.
 */
public final class TestData {

    public static final long TEST_SEED = 9376453012934234L;

    /**
     * Generates an array with random Integers based on Math.random().
     *
     * @param size     size of the array
     * @param minValue smallest possible int
     * @param maxValue largest possible int
     * @return int[] with random data
     */
    public static int[] getRandomIntArray(final int size, final int minValue, final int maxValue) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be smaller than maxValue");
        }
        if (size < 1) {
            throw new IllegalArgumentException("size must be 1 or higher");
        }

        int[] arr = new int[size];
        final int range = maxValue - minValue;
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((Math.random() * (range + 1)) + minValue);
        }

        return arr;
    }

    /**
     * Generates an array with random Characters from a-z based on Java.util.random.
     *
     * @param size size of the array
     * @return char[] with random data
     */
    public static char[] getRandomCharArray(final int size) {
        return getRandomCharArray(size, "abcdefghijklmnopqrstuvwxyz", (long) (Math.random() * Long.MAX_VALUE));
    }

    /**
     * Generates an array with random Characters from alphabet based on java.util.random.
     *
     * @param size     size of the array
     * @param alphabet alphabet of characters used to generate random data
     * @return char[] with random data
     */
    public static char[] getRandomCharArray(final int size, final String alphabet) {
        return getRandomCharArray(size, alphabet, (long) (Math.random() * Long.MAX_VALUE));
    }

    /**
     * Generates an array with seeded random Characters from alphabet based on java.util.random.
     *
     * @param size size of the array
     * @param seed seed used to instance java.util.random
     * @return char[] with random data
     */
    public static char[] getRandomCharArray(final int size, final long seed) {
        return getRandomCharArray(size, "abcdefghijklmnopqrstuvwxyz", seed);
    }

    /**
     * Generates an array with seeded random Characters from alphabet based on java.util.random.
     *
     * @param size     size of the array
     * @param alphabet alphabet of characters used to generate random data
     * @param seed     seed used to instance java.util.random
     * @return char[] with random data
     */
    public static char[] getRandomCharArray(final int size, final String alphabet, final long seed) {
        if (size < 1) {
            throw new IllegalArgumentException("size must be 1 or higher");
        }

        Random r = new Random(seed);
        char[] arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = alphabet.charAt(r.nextInt(alphabet.length()));
        }

        return arr;
    }

    /**
     * Generates an array with pseudo random Integers based on a fixed seed.
     * The same input parameters will always result in the same data being generated.
     *
     * @param size     size of the array
     * @param minValue smallest possible int
     * @param maxValue largest possible int
     * @param seed     random number generator seed
     * @return int[] with pseudo random data
     */
    public static int[] getSeededRandomIntArray(final int size, final int minValue, final int maxValue, final long seed) {
        if (minValue >= maxValue) {
            throw new IllegalArgumentException("minValue must be smaller than maxValue");
        }
        if (size < 1) {
            throw new IllegalArgumentException("size must be 1 or higher");
        }

        Random random = new Random(seed);

        int[] arr = new int[size];
        final int range = maxValue - minValue;
        for (int i = 0; i < size; i++) {
            arr[i] = (int) ((random.nextDouble() * (range + 1)) + minValue);
        }

        return arr;
    }

    public static int[] getAscendingIntArray(final int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size must be 1 or higher");
        }

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }

        return arr;
    }

    public static int[] getDescendingIntArray(final int size) {
        if (size < 1) {
            throw new IllegalArgumentException("size must be 1 or higher");
        }

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }

        return arr;
    }

}
