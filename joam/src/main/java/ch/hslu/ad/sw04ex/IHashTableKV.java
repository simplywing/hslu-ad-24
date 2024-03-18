package ch.hslu.ad.sw04ex;

public interface IHashTableKV<K, V> {
    /**
     * Stores an Object of type V using Object of type K as Index.
     *
     * @param key   The key.
     * @param value The object to store.
     */
    void put(K key, V value);

    /**
     * Retrieve an Object by its Key.
     *
     * @param key Key Object.
     * @return Object of type V stored at Key.
     */
    V get(K key);

    /**
     * Returns the number of keys in this hashtable.
     *
     * @return number of keys in this hashtable.
     */
    int size();
}
