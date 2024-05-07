package ch.hslu.ad.sw10ex;

public final class Heap implements IntHeap {
    private final int[] data;
    private final int maxsize;
    private int size;

    public Heap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        this.data = new int[this.maxsize];
    }

    @Override
    public void insert(int element) {

        if (this.isFull()) {
            return;
        }

        int current = size++;
        data[current] = element;

        while (data[current] < data[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    @Override
    public int remove() {
        int popped = data[0];
        data[0] = data[--size];
        reorganize(0);
        return popped;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isFull() {
        return this.size > this.maxsize - 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size < 1;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= (this.size / 2);
    }

    private void swap(int first, int second) {
        int tmp = this.data[first];
        this.data[first] = this.data[second];
        this.data[second] = tmp;
    }

    private void reorganize(int pos) {
        if (!isLeaf(pos)) {
            int swapPos;
            int leftChildIndex = leftChild(pos);
            int rightChildIndex = rightChild(pos);

            if (rightChildIndex <= this.size) {
                swapPos = this.data[leftChildIndex] < this.data[rightChildIndex] ? leftChildIndex : rightChildIndex;
            } else {
                swapPos = leftChildIndex;
            }

            if (this.data[pos] > this.data[leftChildIndex] || this.data[pos] > this.data[rightChildIndex]) {
                swap(pos, swapPos);
                reorganize(swapPos);
            }
        }
    }
}