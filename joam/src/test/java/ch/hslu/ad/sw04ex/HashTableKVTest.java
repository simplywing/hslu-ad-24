package ch.hslu.ad.sw04ex;

import ch.hslu.demo.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashTableKVTest {

    @Test
    void testPutAndGetByKeyObject() {
        var MyHashTable = new HashTableKV<String, Point>(10);
        MyHashTable.put("first", new Point(30, 31));
        MyHashTable.put("second", new Point(15, -3));
        MyHashTable.put("third", new Point(8, 42));
        System.out.println(MyHashTable);
        assertEquals(new Point(15, -3), MyHashTable.get("second"));
    }

    @Test
    void testPutAndGetByKeyObjectWithCollision() {
        var MyHashTable = new HashTableKV<String, Point>(1);
        MyHashTable.put("first", new Point(30, 31));
        MyHashTable.put("second", new Point(15, -3));
        MyHashTable.put("third", new Point(8, 42));
        System.out.println(MyHashTable);
        assertEquals(new Point(15, -3), MyHashTable.get("second"));
    }
}