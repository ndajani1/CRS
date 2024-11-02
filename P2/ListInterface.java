/**
 * A generic interface for a list that supports basic operations such as 
 * adding, removing, and retrieving elements at specified positions.
 * 
 * @param <T> the type of elements in the list
 */
public interface ListInterface<T> {
    
    /**
     * Replaces the element at the specified index with the given item.
     * 
     * @param item the new item to be set at the specified index
     * @param index the index of the element to replace
     * @return the previous item at the specified index
     */
    public T set(T item, int index); 
    
    /**
     * Retrieves the element at the specified index.
     * 
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    public T get(int index);
    
    /**
     * Adds an item to the list at the specified index.
     * Shifts any subsequent elements to the right (increases their indices).
     * 
     * @param item the item to add
     * @param index the index at which to add the item
     */
    public void add(T item, int index);
    
    /**
     * Removes the element at the specified index and returns it.
     * Shifts any subsequent elements to the left (decreases their indices).
     * 
     * @param index the index of the element to remove
     * @return the element that was removed from the list
     */
    public T remove(int index);
    
    /**
     * Checks if the list contains the specified item.
     * 
     * @param item the item to check for
     * @return true if the list contains the item, false otherwise
     */
    public boolean contains(T item);
    
    /**
     * Checks if the list is empty.
     * 
     * @return true if the list contains no elements, false otherwise
     */
    public boolean isEmpty();
    
    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list
     */
    public int getSize();
}