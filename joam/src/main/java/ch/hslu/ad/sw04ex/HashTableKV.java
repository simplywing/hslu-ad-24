package ch.hslu.ad.sw04ex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class HashTableKV<K, V> implements IHashTableKV<K, V> {
    private final List<HashTableElem<K, V>>[] table;

    private int size = 0;

    @SuppressWarnings("unchecked")
    HashTableKV(int size) {
        this.table = (List<HashTableElem<K, V>>[]) new LinkedList[size];
    }

    @Override
    public void put(K key, V value) {
        if (null == key) {
            throw new IllegalArgumentException("Key cannot be null.");
        }

        int index = key.hashCode() % this.table.length;
        if (null == this.table[index]) {
            this.table[index] = new LinkedList<>();
        }

        this.table[index].add(new HashTableElem<>(key, value));
        this.size++;
    }

    @Override
    public V get(K key) {
        if (null == key) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        int index = key.hashCode() % this.table.length;
        if (null == this.table[index]) {
            return null;
        }

        for (HashTableElem<K, V> elem : this.table[index]) {
            if (key.equals(elem.key())) {
                return elem.value();
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
        return "HashTableKV[" +
                "table=" + Arrays.toString(table) +
                ", size=" + size +
                ']';
    }

    private record HashTableElem<K, V>(K key, V value) {
        @Override
        public String toString() {
            return "HashTableElem[" +
                    "key=" + key +
                    ", value=" + value +
                    ']';
        }
    }
}
