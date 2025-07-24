import java.util.*;
/**
 * A generic implementation of the Queue ADT using Java's built-in {@code LinkedList}
 * @author Taskin Saadman
 */
public class Queue<T> implements Iterable<T> {
    private List<T> queue = new LinkedList<T>();

    /**
     * Adds an element to the rear of the queue.
     * 
     * @param value the element to be added to the queue
     */
    public void enqueue(T value) {
        queue.add(value);
    }

    /**
     * Removes and returns the element at the front of the queue.
     * 
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    public T dequeue() throws RuntimeException {
        if (queue.size() == 0) throw new RuntimeException("Queue is empty!");
        T retVal = queue.get(0);
        queue.remove(0);
        return retVal;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * 
     * @return the element at the front of the queue
     * @throws RuntimeException if the queue is empty
     */
    public T peek() throws RuntimeException {
        if (queue.size() == 0) throw new RuntimeException("Queue is empty!");
        return queue.get(0);
    }

    /**
     * Tests if the queue is empty.
     * 
     * @return {@code true} if the queue contains no elements; {@code false} otherwise
     */
    public boolean isEmpty() {
        return queue.size() == 0;
    }


    /**
     * Returns the size of the Queue.
     * 
     * @return size
     */
    public int size() {
        return queue.size();
    }

    /**
     * Returns an iterator over the elements in the queue in FIFO order (front to rear).
     * The iterator starts from the front of the queue and moves toward the rear.
     * 
     * @return an iterator over the elements in the queue
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = 0;
            
            /**
             * Returns {@code true} if the iteration has more elements.
             * 
             * @return {@code true} if there are more elements to iterate over
             */
            public boolean hasNext() {
                return i <= (queue.size() - 1);
            }
            
            /**
             * Returns the next element in the iteration.
             * Elements are returned in FIFO order (front to rear).
             * 
             * @return the next element in the iteration
             */
            public T next() {
                return queue.get(i++);
            }
        };
    }

}