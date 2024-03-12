package ch.hslu.ad.sw02ex;

import java.util.Arrays;

public final class MyQueue {
    private char[] buffer;
    private int size = 0;
    private int head = 0;

    private int tail = 0;

    public MyQueue(final int size) {
        this.buffer = new char[size];
    }

    public int size() {
        return this.size;
    }

    public boolean isFull() {
        return this.size >= this.buffer.length;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    public void offer(char c) {
        if (this.isFull()) {
            throw new IllegalStateException("Queue is full, unable to offer!");
        }
        this.buffer[this.head % this.buffer.length] = c;
        this.head++;
        this.size++;
    }

    public char poll() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty, nothing to poll here!");
        }
        char ret = this.buffer[this.tail % this.buffer.length];
        this.buffer[this.tail % this.buffer.length] = ' ';
        this.tail++;
        this.size--;
        return ret;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "buffer=" + Arrays.toString(buffer) +
                ", size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}
