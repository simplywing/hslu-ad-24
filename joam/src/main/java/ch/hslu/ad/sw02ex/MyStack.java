package ch.hslu.ad.sw02ex;

/**
 * Custom generic stack Interface for a very simply stack data structure.
 *
 * @param <T>
 */
public interface MyStack<T> {
    /**
     * Push Object of type T onto the Stack.
     *
     * @param o Object to push to the Stack.
     */
    void push(T o);

    /**
     * Removes the top Object from the Stack.
     *
     * @return Object of type T, removed from the top of the Stack.
     */
    T pop();

    /**
     * Returns the current size of the Stack.
     *
     * @return Size of stack.
     */
    int size();

    /**
     * Test if the Stack is full.
     *
     * @return true if Stack is filled to the brim.
     */
    boolean isFull();

    /**
     * Test if the Stack is empty.
     *
     * @return true if Stack is empty, has no Elements inside.
     */
    boolean isEmpty();

}
