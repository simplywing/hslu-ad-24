package ch.hslu.ad.sw02ex;

import ch.hslu.demo.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListSLTest {

    @Test
    void testListSize() {
        var l = new MyListSL<Point>();
        l.add(new Point(1, 2));
        l.add(new Point(1, 2));
        l.add(new Point(1, 5));
        assertEquals(3, l.size());
    }

    @Test
    void testListSizeEmpty() {
        var l = new MyListSL<Point>();
        assertEquals(0, l.size());
    }

    @Test
    void testListContains() {
        var l = new MyListSL<Point>();
        l.add(new Point(1, 2));
        l.add(new Point(1, 5));
        assertTrue(l.contains(new Point(1, 2)));
    }

    @Test
    void testListNotContains() {
        var l = new MyListSL<Point>();
        l.add(new Point(56, 9));
        l.add(new Point(1, 5));
        assertFalse(l.contains(new Point(1, 2)));
    }

    @Test
    void testListPopElem() {
        var l = new MyListSL<Point>();
        l.add(new Point(56, 9));
        l.add(new Point(1, 5));

        var popped = l.pop();

        assertEquals(new Point(56, 9), popped);
        assertEquals(1, l.size());
    }

    @Test
    void testListRemoveElemFirst() {
        var l = new MyListSL<Point>();
        l.add(new Point(1, 2));
        l.add(new Point(6, 2));
        l.add(new Point(1, 5));

        var res = l.remove(new Point(1, 2));

        assertTrue(res);
        assertEquals(2, l.size());
        assertEquals(new Point(6, 2), l.pop());
        assertEquals(new Point(1, 5), l.pop());

    }

    @Test
    void testListRemoveElemMiddle() {
        var l = new MyListSL<Point>();
        l.add(new Point(1, 2));
        l.add(new Point(6, 2));
        l.add(new Point(1, 5));

        var res = l.remove(new Point(6, 2));

        assertTrue(res);
        assertEquals(2, l.size());
        assertEquals(new Point(1, 2), l.pop());
        assertEquals(new Point(1, 5), l.pop());
    }

    @Test
    void testListRemoveElemLast() {
        var l = new MyListSL<Point>();
        l.add(new Point(1, 2));
        l.add(new Point(6, 2));
        l.add(new Point(1, 5));

        var res = l.remove(new Point(1, 5));

        assertTrue(res);
        assertEquals(2, l.size());
        assertEquals(new Point(1, 2), l.pop());
        assertEquals(new Point(6, 2), l.pop());
    }

    @Test
    void testListRemoveNotContainingElem() {
        var l = new MyListSL<Point>();

        l.add(new Point(1, 2));
        l.add(new Point(6, 2));
        l.add(new Point(1, 5));
        var res = l.remove(new Point(1, 57));

        assertFalse(res);
        assertEquals(3, l.size());
        assertEquals(new Point(1, 2), l.pop());
        assertEquals(new Point(6, 2), l.pop());
        assertEquals(new Point(1, 5), l.pop());
    }

    @Test
    void testListPushElem() {
        var l = new MyListSL<Point>();
        l.push(new Point(3, 5));
        l.push(new Point(7, 5));

        assertEquals(2, l.size());
        assertEquals(new Point(7, 5), l.pop());
        assertEquals(new Point(3, 5), l.pop());
        assertEquals(0, l.size());
    }
}