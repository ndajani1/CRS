/**
 * A custom HashSet implementation that uses separate chaining with LinkedLists to resolve collisions.
 * 
 * @param <V> the type of elements stored in this set
 */
public class HashSet<V> {

    /**
     * The hash table storing the elements.
     */
    private LinkedList<V>[] hashTable;
    
    /**
     * The number of elements in the set.
     */
    private int size;
    
    /**
     * The current capacity of the hash table.
     */
    private int capacity;
    
    /**
     * The maximum load factor for resizing.
     */
    private static final double LOAD_FACTOR = 1.0;

    /**
     * Constructs a HashSet with a specified initial capacity.
     * 
     * @param cap the initial capacity of the hash table
     * @throws IllegalArgumentException if the initial capacity is less than or equal to 0
     */
    @SuppressWarnings("unchecked")
    public HashSet(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = cap;
        this.size = 0;
        this.hashTable = new LinkedList[cap];

        for (int i = 0; i < cap; i++) {
            hashTable[i] = new LinkedList<>(); 
        }
    }

    /**
     * Adds a value to the hash set if it is not already present.
     * Automatically resizes the hash table when the load factor exceeds 1.0.
     * 
     * @param value the value to add
     * @throws IllegalArgumentException if the value is null
     */
    public void put(V value) {
        if (value == null) {
            throw new IllegalArgumentException("Null values are not allowed");
        }

        int index = Math.abs(value.hashCode()) % capacity;
        LinkedList<V> chain = hashTable[index];

        for (int i = 0; i < chain.getSize(); i++) {
            if (chain.get(i).equals(value)) {
                return; 
            }
        }

        chain.add(value, chain.getSize());
        size++;

        if ((double) size / capacity > LOAD_FACTOR) {
            resize(); 
        }
    }

    /**
     * Retrieves the value from the hash set if it is present.
     * 
     * @param value the value to search for
     * @return the value if found, or null if the value is not present in the set
     */
    public V get(V value) {
        if (value == null) {
            return null;
        }

        int index = Math.abs(value.hashCode()) % capacity;
        LinkedList<V> chain = hashTable[index];

        for (int i = 0; i < chain.getSize(); i++) {
            V currentValue = chain.get(i);
            if (currentValue.equals(value)) {
                return currentValue;
            }
        }

        return null;
    }

    /**
     * Resizes the hash table by doubling its capacity.
     * Rehashes all existing values into the new hash table.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        LinkedList<V>[] newHashTable = new LinkedList[newCapacity];

        for (int i = 0; i < newCapacity; i++) {
            newHashTable[i] = new LinkedList<>();
        }

        for (int i = 0; i < capacity; i++) {
            LinkedList<V> chain = hashTable[i];
            for (int j = 0; j < chain.getSize(); j++) {
                V value = chain.get(j);
                int newIndex = Math.abs(value.hashCode()) % newCapacity;
                newHashTable[newIndex].add(value, newHashTable[newIndex].getSize());
            }
        }

        hashTable = newHashTable;
        capacity = newCapacity;
    }

    /**
     * Retrieves all the values stored in the hash set.
     * 
     * @return a LinkedList containing all the values in the hash set
     */
    protected LinkedList<V> getAllValues() {
        LinkedList<V> allValues = new LinkedList<>();

        for (int i = 0; i < capacity; i++) {
            LinkedList<V> chain = hashTable[i];
            for (int j = 0; j < chain.getSize(); j++) {
                allValues.add(chain.get(j), allValues.getSize());
            }
        }

        return allValues;
    }
}