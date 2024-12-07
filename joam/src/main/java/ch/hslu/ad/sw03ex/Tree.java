package ch.hslu.ad.sw03ex;

public interface Tree<T extends Comparable<T>> {
    /**
     * Add an object to the tree.
     *
     * @param obj the object to add.
     * @return true if the object was added.
     */
    boolean add(T obj);

    /**
     * Search the tree for a given object.
     *
     * @param obj the object to search for.
     * @return true if the object was found in the tree.
     */
    boolean search(T obj);
}