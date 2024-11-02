/**
 * A generic interface for a stack data structure.
 * 
 * @param <T> the type of elements in the stack
 */
public interface StackInterface<T> {

    /**
     * Pushes an item onto the top of the stack.
     * 
     * @param item the item to be added to the stack
     */
    public void push(T item);

    /**
     * Removes and returns the item from the top of the stack.
     * 
     * @return the item removed from the top of the stack
     */
    public T pop();

    /**
     * Returns (without removing) the item at the top of the stack.
     * 
     * @return the item at the top of the stack
     */
    public T peek();

    /**
     * Checks if the stack is empty.
     * 
     * @return true if the stack contains no elements, false otherwise
     */
    public Boolean isEmpty();
}