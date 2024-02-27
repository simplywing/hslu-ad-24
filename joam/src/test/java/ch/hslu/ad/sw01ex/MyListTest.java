package ch.hslu.ad.sw01ex;

import ch.hslu.demo.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    @Test
    void testAdd() {
        MyList<Point> list = new MyList<>();
        list.add(new Point(1, 1));
        list.add(new Point(2, 2));

        assertEquals(2, list.size());
    }

    @Test
    void testAddAndGet() {
        MyList<Point> list = new MyList<>();
        list.add(new Point(1, 1));
        list.add(new Point(2, 2));
        list.add(new Point(3, 3));
        list.add(new Point(4, 4));

        assertEquals(2, list.get(1).getX());
        assertEquals(3, list.get(2).getX());
    }

    @Test
    void testClear() {
        MyList<Point> list = new MyList<>();
        list.add(new Point(1, 1));
        list.add(new Point(2, 2));
        list.add(new Point(3, 3));
        list.clear();

        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    void testGetAllElements() {
        MyList<Point> list = new MyList<>();
        list.add(new Point(1, 1));
        list.add(new Point(2, 2));
        list.add(new Point(3, 3));
        list.add(new Point(4, 4));


        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    void testLastAccessCache() {
        MyList<Point> list = new MyList<>();
        list.add(new Point(1, 1));
        list.add(new Point(2, 2));
        list.add(new Point(3, 3));
        list.add(new Point(4, 4));
        list.add(new Point(5, 5));
        list.add(new Point(6, 6));
        list.add(new Point(7, 7));

        System.out.println(list.get(4));
        System.out.println(list.get(4));
    }

    @Test
    void testIterator() {
        MyList<Point> list = new MyList<>();
        list.add(new Point(1, 1));
        list.add(new Point(2, 2));
        list.add(new Point(3, 3));

        int elemCount = 0;
        for (Point p : list) {
            System.out.println(p);
            elemCount++;
        }

        assertEquals(3, elemCount);
    }

}