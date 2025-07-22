import java.util.Arrays;
import java.util.Iterator;
/**
 * A generic dynamic array implementation that automatically resizes as elements are added or removed.
 * Its functionality is similar to that of the {@code ArrayList} class.
 * 
 * @param <T> The type of elements stored in the dynamic array
 * @author Taskin Saadman
 * @see java.util.ArrayList
 * @see Iterable
 */
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {

    private T[] array;     //internal static array storing data
    private int size;     //no. of elements inside


    /**
     * Creates a dynamic array with an initial capacity of 10.
     */
    public DynamicArray() {
        array = (T[]) new Object[10];   //typecasting warning suppressed
        size = 0;
    }


    /**
     * Returns the number of elements currently stored in the dynamic array.
     * 
     * @return the current size of the array
     */
    public int size() {
        return size;
    }


    /**
     * Returns the element in the specified index
     * 
     * @param index the specified index
     * @return element the element at the specified index
     * @throws IndexOutOfBoundsException
     */
    public T get(int index) throws IndexOutOfBoundsException { 
        if (!(index >= 0 && index < size)) throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        return array[index];
    }


    /**
     * Set an element at a specified index
     * 
     * @param index the specified index
     * @param element the element to be set
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public void set(int index, T element) {
        if (!(index >= 0 && index < size)) throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");

        if (element == null) throw new NullPointerException("Dynamic array can't contain empty reference types");
        
        array[index] = element;
    }


    /**
     * Adds an element to the end of the dynamic array.
     * If the array is full, it doubles the capacity before adding.
     * 
     * @param element The element to add
     */
    public void add(T element) {
        if (size == array.length) array = Arrays.copyOf(array, array.length * 2);   //if no more capacity, double the array
        array[size++] = element;    //increment size after adding new element
    }


    /**
     * Inserts an element to a specified index and shifts rest of the
     * elements to the right of the dynamic array.
     * 
     * @param index The position where the element should be inserted
     * @param element The element to add
     * @throws IndexOutOfBoundsException if index is negative or greater than size
     * @throw NullPointerException if {@code null} was passed as element to be inserted
     */
    public void insert(int index, T element) {
        //insertion is allowed at the very end as well
        if (!(index >= 0 && index <= size)) throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");

        if (element == null) throw new NullPointerException("Dynamic array can't contain empty reference types");

        if (size == array.length) array = Arrays.copyOf(array, array.length * 2);

        for(int i = size - 1; i >= index; i--) {    //shift everything to the right from specified index
            array[i + 1] = array[i];
        }

        array[index] = element;     //insert element and increment size
        size++;
    }


    /**
     * Removes element at specified index and left shift the rest of the array.
     * 
     * @param index index from which element is to be popped
     * @return element the element which was popped
     * @throws IndexOutOfBoundsException if out of bounds index was passed
     */
    public T pop(int index) throws IndexOutOfBoundsException {
        if (!(index >= 0 && index < size)) throw new IndexOutOfBoundsException();

        T element = array[index];

        for(int i = index; i < size; i++) {     //left-shift
            array[i] = array[i+1];
        }

        size--;     //decrement size and return popped element
        return element;
    }


    /**
     * Removes the last element of the dynamic array and returns it
     * @return element the last element of the dynamic array
     */
    public T pop() {
        T element = array[size - 1];
        array[size - 1] = null;
        size--;
        return element;
    }

    /**
     * Checks whether an element exists inside the dynamic array.
     * @param element element to be found
     * @return boolean true or false
     * @throws NullPointerException if {@code null} was passed as argument
     */
    public boolean contains(T element) throws NullPointerException {
        if (element == null) throw new NullPointerException("Dynamic array cannot contain empty reference types!");

        for(int i = 0; i < size; i++) {
            if (array[i].equals(element)) return true;
        }

        return false;
    }


    /**
     * Substitute old internal static array with new and reset size.
     */
    
    public void clear() {
        array = (T[]) new Object[10];
        size = 0;
    }

    /**
     * Returns a string representation of the {@code DynamicArray} object.
     * @return String
     */
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array,size));
    }


    /**
     * Method overridden from Iterable<T> interface.
     * Useful to iterate through the dynamic array using for-each loop.
     * @return an iterator over elements of type {@code T}
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            public boolean hasNext() {
                return current < size;
            }

            public T next() {
                return array[current++];
            }
        };
    }


}