package ch.hslu.ad.sw04ex;

public interface IHashTable<T> {
    /**
     * Stores an Object of type T using its hashCode() as Index.
     *
     * @param obj The object to store.
     */
    void put(T obj);

    /**
     * Retrieve an Object by its hashCode Key.
     *
     * @param key Key (hashCode) of Object.
     * @return Object of type T stored at Index key.
     */
    T get(int key);

    /**
     * Retrieve an Object by its Key.
     *
     * @param key Key Object.
     * @return Object of type T stored at Key.
     */
    T get(T key);

    /**
     * Returns the number of keys in this hashtable.
     *
     * @return number of keys in this hashtable.
     */
    int size();
}
