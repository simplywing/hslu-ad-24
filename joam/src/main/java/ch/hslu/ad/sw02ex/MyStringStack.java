package ch.hslu.ad.sw02ex;

public final class MyStringStack implements MyStack<String> {

    private String[] arr;
    private int head = -1;

    public MyStringStack(int size) {
        this.arr = new String[size];
    }

    @Override
    public void push(String o) {
        if (null == o) {
            throw new IllegalArgumentException("String to be added to Stack cannot be null");
        }
        if (this.isFull()) {
            throw new IndexOutOfBoundsException("Stack is full. Unable to add Element");
        }
        this.head++;
        this.arr[head] = o;
    }

    @Override
    public String pop() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Array is empty, nothing to pop here!");
        }
        String ret = this.arr[head];
        this.arr[head] = null;
        this.head--;
        return ret;
    }

    @Override
    public int size() {
        return head + 1;
    }

    @Override
    public boolean isFull() {
        return this.head >= this.arr.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return this.head <= -1;
    }
}
