package ch.hslu.ad.sw04ex;

import ch.hslu.demo.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HashTableTest {

    @Test
    void testPutAndGetByHashCode() {
        var MyHashTable = new HashTable<Point>(10);
        var myPoint = new Point(15, 10);
        var hashCode = myPoint.hashCode();
        MyHashTable.put(myPoint);
        MyHashTable.put(new Point(20, 16));
        MyHashTable.put(new Point(22, -2));
        System.out.println(MyHashTable);
        assertEquals(myPoint, MyHashTable.get(hashCode));
    }

    @Test
    void testPutAndGetByKeyObject() {
        var MyHashTable = new HashTable<Point>(10);
        var myPoint = new Point(15, 10);
        MyHashTable.put(myPoint);
        MyHashTable.put(new Point(-12, 16));
        MyHashTable.put(new Point(22, -2));
        MyHashTable.put(new Point(22, -2));
        System.out.println(MyHashTable);
        assertEquals(myPoint, MyHashTable.get(myPoint));
    }

    @Test
    void testAddNull() {
        var MyHashTable = new HashTable<Point>(10);
        assertThrows(IllegalArgumentException.class, () -> MyHashTable.put(null));
    }

    @Test
    void testGetNull() {
        var MyHashTable = new HashTable<Point>(10);
        assertThrows(IllegalArgumentException.class, () -> MyHashTable.get(null));
    }

    @Test
    void testHashTableSizeEmpty() {
        var MyHashTable = new HashTable<Point>(10);
        assertEquals(0, MyHashTable.size());
    }

    @Test
    void testHashTableSize() {
        var MyHashTable = new HashTable<Point>(10);
        MyHashTable.put(new Point(0, 0));
        MyHashTable.put(new Point(0, 0));
        MyHashTable.put(new Point(1, 2));
        assertEquals(3, MyHashTable.size());
    }

}