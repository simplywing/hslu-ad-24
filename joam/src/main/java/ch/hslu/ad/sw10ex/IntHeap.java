package ch.hslu.ad.sw10ex;

public interface IntHeap {
    /**
     * Push an int onto the heap.
     *
     * @param num int put on the heap.
     */
    void insert(final int num);

    /**
     * Pop the largest int off the heap.
     *
     * @return the largest int from the top of the heap.
     */
    int remove();

    /**
     * Get the current size of the Heap.
     *
     * @return size of the heap.
     */
    int size();

    /**
     * Returns true if the heap is full.
     *
     * @return true if heap is full, false if there is empty space.
     */
    boolean isFull();

    /**
     * Returns true if the heap has no elements stored inside.
     *
     * @return true if heap is empty.
     */
    boolean isEmpty();
}
