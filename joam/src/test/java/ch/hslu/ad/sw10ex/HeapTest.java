package ch.hslu.ad.sw10ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    @Test
    void testInsertHeap() {
        IntHeap heap = new Heap(10);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
        assertEquals(1, heap.remove());
    }

    @Test
    void testInsertHeapNegative() {
        IntHeap heap = new Heap(10);
        heap.insert(-12);
        heap.insert(-2);
        heap.insert(-19);
        heap.insert(-2);
        assertEquals(-19, heap.remove());
    }

    @Test
    void testHeapSize() {
        IntHeap heap = new Heap(3);
        heap.insert(Integer.MAX_VALUE);
        heap.insert(Integer.MIN_VALUE);
        heap.insert(0);
        assertEquals(3, heap.size());
    }

    @Test
    void testHeapIsFull() {
        IntHeap heap = new Heap(3);
        heap.insert(Integer.MAX_VALUE);
        heap.insert(Integer.MIN_VALUE);
        heap.insert(0);
        assertTrue(heap.isFull());
    }

    @Test
    void testHeapIsEmpty() {
        IntHeap heap = new Heap(3);
        assertTrue(heap.isEmpty());
    }
}