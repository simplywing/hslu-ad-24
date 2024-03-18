package ch.hslu.ad.sw04ex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class HashTable<T> implements IHashTable<T> {

    private final List<T>[] table;

    private int size = 0;

    @SuppressWarnings("unchecked")
    HashTable(int size) {
        this.table = (List<T>[]) new LinkedList[size];
    }

    @Override
    public void put(T obj) {
        if (null == obj) {
            throw new IllegalArgumentException("Null cannot be added to the HashTable");
        }
        int index = obj.hashCode() % this.table.length;
        if (null == this.table[index]) {
            this.table[index] = new LinkedList<>();
        }

        this.table[index].add(obj);
        this.size++;
    }

    @Override
    public T get(int key) {
        int index = key % this.table.length;
        if (null == this.table[index]) {
            return null;
        }
        //returning the first Element since we cannot use equals()
        return this.table[index].getFirst();
    }

    @Override
    public T get(T key) {
        if (null == key) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = key.hashCode() % this.table.length;
        if (null == this.table[index]) {
            return null;
        }

        for (T elem : this.table[index]) {
            if (key.equals(elem)) {
                return elem;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        return "HashTable[" +
                "table=" + Arrays.toString(table) +
                ']';
    }
}
