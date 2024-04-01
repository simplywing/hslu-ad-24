package ch.hslu.ad.sw02ex;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class MyListSL<T> implements Iterable<T> {

    private int size = 0;

    private Node<T> head = null;

    public void add(T data) {
        if (null == this.head) {
            this.head = new Node<>(data);
        } else {
            Node<T> currentNode = this.head;
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(new Node<>(data));
        }
        size++;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(T obj) {
        for (T elem : this) {
            if (elem.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    public T pop() {
        Node<T> popped = this.head;
        this.head = popped.getNext();
        this.size--;
        return popped.getData();
    }

    public void push(T data) {
        Node<T> prevHead = this.head;
        this.head = new Node<>(data);
        this.head.setNext(prevHead);
        this.size++;
    }

    /**
     * Removes the Element obj from the list by iterating through the List and finding a match.
     *
     * @param obj
     * @return
     */
    public boolean remove(T obj) {
        Node<T> prevNode = null;
        Node<T> currentNode = this.head;
        while (currentNode != null) {
            if (currentNode.getData().equals(obj)) {
                Node<T> nextNode = currentNode.getNext();
                if (null == prevNode) {
                    this.head = nextNode;
                } else {
                    prevNode.setNext(nextNode);
                }
                this.size--;
                return true;
            }
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
        return false;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new MyIterator(this.head);
    }

    private static class Node<T> {
        private Node<T> next = null;
        private T data;

        private Node(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

    private class MyIterator implements Iterator<T> {

        private Node<T> next;

        public MyIterator(Node<T> next) {
            this.next = next;
        }

        @Override
        public boolean hasNext() {
            return null != this.next;
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                Node<T> ret = this.next;
                this.next = ret.getNext();
                return ret.getData();
            }
        }
    }
}
