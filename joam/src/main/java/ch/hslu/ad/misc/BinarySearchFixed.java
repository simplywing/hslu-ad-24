package ch.hslu.ad.misc;

/**
 * Better Implementation of binary search.
 */
public final class BinarySearchFixed {
    private final int[] data;

    public BinarySearchFixed(int[] data) {
        this.data = data;
    }

    public int search(final int arg) {
        int min = 0;
        int max = data.length - 1;
        int index = (min + max) / 2;
        while ((min <= max) && (this.data[index] != arg)) {
            if (this.data[index] < arg) {
                min = index + 1;
            } else {
                max = index - 1;
            }
            index = (min + max) / 2;
        }
        if (min > max) {
            index = -1;
        }
        return index;
    }
}
