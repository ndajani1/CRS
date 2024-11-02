/**
 * A generic stack implementation using a LinkedList as the underlying data structure.
 * 
 * @param <T> the type of elements in the stack
 */
public class Stack<T> implements StackInterface<T> {

    /**
     * The underlying linked list that stores the stack elements.
     */
    private LinkedList<T> list;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        list = new LinkedList<>(); 
    }

    /**
     * Pushes an item onto the top of the stack.
     * 
     * @param item the item to be added to the stack
     */
    @Override
    public void push(T item) {
        list.add(item, 0);
    }

    /**
     * Removes and returns the item from the top of the stack.
     * 
     * @return the item removed from the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public T pop() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }
        return list.remove(0);
    }

    /**
     * Returns (without removing) the item at the top of the stack.
     * 
     * @return the item at the top of the stack
     * @throws IllegalStateException if the stack is empty
     */
    @Override
    public T peek() {
        if (list.isEmpty()) {
            throw new IllegalStateException("Stack is empty.");
        }

        return list.get(0);
    }

    /**
     * Checks if the stack is empty.
     * 
     * @return true if the stack contains no elements, false otherwise
     */
    @Override
    public Boolean isEmpty() {
        return list.isEmpty(); 
    }
}

