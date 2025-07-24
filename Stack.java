import java.util.*;
/**
 * A generic Stack implementation using Java's built-in {@code ArrayList<T>} collection
 * The right-end of the internal ArrayList represents the top of the stack (LIFO).
 * @author Taskin Saadman
 */
public class Stack<T> implements Iterable<T> {
    private List<T> stack;

    /**
     * Constructs an empty stack.
     */
    public Stack() {
        stack = new ArrayList<T>();
    }

    /**
     * Pushes an element onto the top of the stack.
     * 
     * @param element the element to be pushed onto the stack
     */
    public void push(T element) {
        stack.add(element);
    }

    /**
     * Removes and returns the element at the top of the stack.
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() throws EmptyStackException {
        if (stack.size() == 0) throw new EmptyStackException();
        T retVal = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return retVal;
    }

    /**
     * Returns the number of elements in the stack.
     * 
     * @return the number of elements in the stack
     */
    public int size() {
        return stack.size();
    }

    /**
     * Tests if the stack is empty.
     * 
     * @return {@code true} if the stack contains no elements; {@code false} otherwise
     */
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    /**
     * Tests if the specified element is contained in the stack.
     * 
     * @param element the element to search for
     * @return {@code true} if the element is found in the stack; {@code false} otherwise
     */
    public boolean contains(T element) {
        return stack.contains(element);
    }

    /**
     * Returns the element at the top of the stack without removing it.
     * 
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() throws EmptyStackException {
        if (stack.size() == 0) throw new EmptyStackException();
        return stack.get(stack.size() - 1);
    }

    /**
     * Returns an array containing all elements in the stack.
     * The order of elements in the array matches the internal ArrayList representation.
     * 
     * @return an array containing all elements in the stack
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) stack.toArray();
    }

    /**
     * Returns a string representation of the stack with each element on a new line.
     * Elements are displayed from top to bottom (LIFO order).
     * 
     * @return a string representation of the stack
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this) sb.append(item).append("\n");
        return sb.toString();
    }

    /**
     * Returns an iterator over the elements in the stack in LIFO order (top to bottom).
     * The iterator starts from the top of the stack and moves toward the bottom.
     * 
     * @return an iterator over the elements in the stack
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = stack.size() - 1;   //start from last element
            
            /**
             * Returns {@code true} if the iteration has more elements.
             * 
             * @return {@code true} if there are more elements to iterate over
             */
            public boolean hasNext() {
                return i >= 0;
            }

            /**
             * Returns the next element in the iteration.
             * Elements are returned in LIFO order (top to bottom).
             * 
             * @return the next element in the iteration
             * @throws NoSuchElementException if there are no more elements to iterate over
             */
            public T next() throws NoSuchElementException {
                if (i < 0) throw new NoSuchElementException("Already traversed till the bottom of the Stack.");
                return stack.get(i--);
            }
        };
    }
}