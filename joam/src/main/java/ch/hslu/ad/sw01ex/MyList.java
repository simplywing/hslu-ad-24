package ch.hslu.ad.sw01ex;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Very simple and incomplete implementation of a generic doubly linked list.
 * This list has a simple access cache to get a previously accessed element faster.
 *
 * @param <T>
 */
public final class MyList<T> implements Iterable<T> {
    private MyListElem<T> head = null;
    private MyListElem<T> tail = null;
    private int size = 0;

    private MyListElem<T> lastAccessCache = null;
    private int lastAccessIndex = -1;

    public MyList() {
    }

    private MyListElem<T> getHead() {
        return head;
    }

    private void setHead(MyListElem<T> head) {
        this.head = head;
    }

    private MyListElem<T> getTail() {
        return tail;
    }

    private void setTail(MyListElem<T> tail) {
        this.tail = tail;
    }

    public void add(T obj) {
        MyListElem<T> newElem = new MyListElem<>(obj);

        if (this.size == 0) {
            this.setHead(newElem);
            this.setTail(newElem);
            this.size = 1;
        } else {
            this.getTail().setNext(newElem); //former last element points to new element
            newElem.setPrev(this.getTail()); //new element's previous is the former last element
            this.setTail(newElem); //the new element is the new tail
            this.size++;
        }
    }

    public T get(int index) {

        this.checkIndexInBounds(index);

        if (index == this.lastAccessIndex) {
            return lastAccessCache.getContent();
        }

        int cacheIndex = index;

        MyListElem<T> result;
        if (index < this.size() / 2) { //index is in the first half, start from head
            result = this.getHead();
            while (index > 0) {
                result = result.getNext();
                index--;
            }
        } else { //index is in the second half, start from tail
            result = this.getTail();
            while (index < this.size() - 1) {
                result = result.getPrev();
                index++;
            }
        }

        // cache reference to the last accessed Element and it's index.
        this.lastAccessCache = result;
        this.lastAccessIndex = cacheIndex;

        return result.getContent();
    }

    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;

        this.lastAccessCache = null;
        this.lastAccessIndex = -1;
    }

    public int size() {
        return this.size;
    }

    private void checkIndexInBounds(int index) {
        if (index > this.size() - 1) {
            throw new IndexOutOfBoundsException("index must be smaller than or equal to list's size() -1");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("index must be greater than or equal to 0");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator<>(this.getHead());
    }

    private static class MyListElem<T> {

        public T content;
        private MyListElem<T> prev = null;
        private MyListElem<T> next = null;

        public MyListElem(T obj) {
            this.content = obj;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public MyListElem<T> getPrev() {
            return this.prev;
        }

        public void setPrev(MyListElem<T> prev) {
            this.prev = prev;
        }

        public MyListElem<T> getNext() {
            return this.next;
        }

        public void setNext(MyListElem<T> next) {
            this.next = next;
        }
    }

    private static class MyListIterator<T> implements Iterator<T> {

        private MyListElem<T> elem;

        public MyListIterator(MyListElem<T> head) {
            this.elem = head;
        }

        @Override
        public boolean hasNext() {
            return !(null == this.elem);
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            MyListElem<T> out = this.elem;
            this.elem = out.getNext();
            return out.getContent();
        }

    }
}
