package ch.hslu.ad.sw02ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyStringStackTest {

    @Test
    void testEmptyStack() {
        MyStack<String> s = new MyStringStack(20);
        assertEquals(0, s.size());
    }

    @Test
    void testStackAddSingleElement() {
        MyStack<String> s = new MyStringStack(20);
        s.push("This is a Test");
        assertEquals(1, s.size());
        assertEquals("This is a Test", s.pop());
    }

    @Test
    void testStackIsFull() {
        MyStack<String> s = new MyStringStack(1);
        s.push("Murmur");
        assertTrue(s.isFull());
    }

    @Test
    void testTollSindDatenStrukturen() {
        MyStack<String> s = new MyStringStack(3);
        s.push("toll!");
        s.push("sind");
        s.push("Datenstrukturen");
        assertEquals(3, s.size());
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
        assertTrue(s.isEmpty());
    }
}