package ch.hslu.ad.misc;

/**
 * Bad Implementation of binary search. This works except if you search for the last Element in the array and
 * this element is unique.
 */
public final class BinarySearchBad {
    private final int[] data;

    public BinarySearchBad(int[] data) {
        this.data = data;
    }

    public int search(final int arg) {
        int min = 0;
        int max = data.length - 1;
        int index = (min + max) / 2;
        while ((min <= max) && (this.data[index] != arg)) {
            if (this.data[index] < arg) {
                min = index;
            } else {
                max = index;
            }
            index = (min + max) / 2;
        }
        if (min > max) {
            index = -1;
        }
        return index;
    }
}
