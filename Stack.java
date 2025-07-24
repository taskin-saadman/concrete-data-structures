import java.util.*;
/**
 * A generic Stack implementation using Java's built-in {@code ArrayList<T>} collection
 * The right-end of the internal ArrayList represents the top of the stack (LIFO).
 * @author Taskin Saadman
 */
public class Stack<T> implements Iterable<T> {
    private List<T> stack;

    public Stack() {
        stack = new ArrayList<T>();
    }

    public void push(T element) {
        stack.add(element);
    }

    public T pop() throws EmptyStackException {
        if (stack.size() == 0) throw new EmptyStackException();
        T retVal = stack.get(stack.size() - 1);
        stack.remove(stack.size() - 1);
        return retVal;
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }

    public boolean contains(T element) {
        return stack.contains(element);
    }

    public T peek() throws EmptyStackException {
        if (stack.size() == 0) throw new EmptyStackException();
        return stack.get(stack.size() - 1);
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) stack.toArray();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this) sb.append(item).append("\n");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = stack.size() - 1;   //start from last element
            
            public boolean hasNext() {
                return i >= 0;
            }

            public T next() throws NoSuchElementException {
                if (i < 0) throw new NoSuchElementException("Already traversed till the bottom of the Stack.");
                return stack.get(i--);
            }
        };
    }
}