/**
 * A generic implementation of a singly linked list.
 * 
 * @param <T> the type of elements in this list
 */
public class LinkedList<T> implements ListInterface<T> {

    /**
     * Represents a node in the linked list.
     * Each node stores data and a reference to the next node in the list.
     */
    private class Node {
        /**
         * The data stored in this node.
         */
        T data;

        /**
         * A reference to the next node in the list.
         */
        Node next;

        /**
         * Constructs a new node with the specified data.
         * 
         * @param data the data to store in this node
         */
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Represents the head node of the linked list.
     */
    private Node head;

    /**
     * The number of elements in the list.
     */
    private int size;

    /**
     * Represents the tail node of the linked list.
     */
    private Node tail;

    /**
     * Constructs an empty linked list.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Replaces the element at the specified index in the list with the specified item.
     * 
     * @param item the new item to set at the specified index
     * @param index the index of the element to replace
     * @return the previous item at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public T set(T item, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
    
        T oldData = current.data;
        current.data = item; 
    
        return oldData;
    }

    /**
     * Returns the element at the specified index in the list.
     * 
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
    
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
    
        return current.data;
    }

    /**
     * Adds an item to the list at the specified index.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     * 
     * @param item the item to add
     * @param index the index at which to add the item
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    @Override
    public void add(T item, int index) {

        Node newNode = new Node(item);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } 
        else if (index == size) {
            tail.next = newNode;
            tail = newNode;
        } 
        else {
            Node current = head;
            Node previous = null;
            for (int i = 0; i < index; i++) {
                previous = current;
                current = current.next;
            }
            newNode.next = current;
            previous.next = newNode;
        }

        size++;
    }

    /**
     * Removes and returns the element at the specified index in the list.
     * 
     * @param index the index of the element to remove
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public T remove(int index) {

        Node removedNode;

        if (index == 0) {
            removedNode = head;
            head = head.next;
            if (size == 1) {
                tail = null;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedNode = current.next;
            current.next = current.next.next;

            if (index == size - 1) {
                tail = current;
            }
        }

        size--;
        return removedNode.data;
    }

    /**
     * Checks if the list contains the specified item.
     * 
     * @param item the item to check for
     * @return true if the list contains the specified item, false otherwise
     */
    @Override
    public boolean contains(T item) {
        Node current = head;

        while (current != null) {
            if (current.data.equals(item)) {
                return true;
            }
            current = current.next;
        }
    
        return false;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list
     */
    @Override
    public int getSize() {
        return size;
    }

}