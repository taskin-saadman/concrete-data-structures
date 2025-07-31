import java.util.ArrayList;
/**
 * A generic custom implementation of the MaxHeap Priority Queue using {@code java.util.ArrayList}.
 * Implements {@code java.lang.Comparable} to compare generic elements using natural order.
 * @author Taskin Saadman
 */
public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;

    /**
     * Constructs an empty heap using an ArrayList
     * @see java.util.ArrayList
     */
    public MaxHeap() {
        heap = new ArrayList<T>();
    }

    /**
     * Returns the no. of elements stored in the heap
     * @return the size of the heap
     */
    public int size() {
        return heap.size();
    }

    /**
     * Returns a boolean based on whether the heap is empty or not
     * @return true is heap is empty, otherwise false
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Returns a boolean based on whether a specific element exists in the heap
     * @param element the element to be searched for
     * @return true if found, otherwise false
     */
    public boolean contains(T element) {
        return heap.contains(element);
    }

    /**
     * Returns the root value but does not pop it from the ArrayList
     * @return the value at the node
     * @throws RuntimeException if heap is empty
     */
    public T peek() throws RuntimeException {
        if (heap.size() == 0) throw new RuntimeException("Heap is empty!");
        return heap.get(0);
    }

    /**
     * Pops the value at the root and returns it.
     * Runs heapify down procedure until heap property satisfied.
     * @return the element at the root of the MaxHeap
     * @throws RuntimeException if heap is empty
     */
    public T poll() throws RuntimeException {
        if (heap.size() == 0) throw new RuntimeException("Heap is empty!");
        if (heap.size() == 1) return heap.remove(0);

        T polled = heap.get(0);  //return later
        this.swap(0, this.size() - 1);  //swap with last value
        heap.remove(this.size() - 1);   //remove old root
        bubbleDown(0);  //start bubble down from root
        return polled;
    }

    /**
     * Inserts a new element to the end of the internal array, then 
     * utilizes the bubble up method to satisfy heap property.
     * @param value the value to be inserted into the heap
     */
    public void insert(T value) {
        heap.add(value);
        if (heap.size() == 1) return; //no need to bubble up for heap with only 1 element
        bubbleUp(heap.size() - 1);
    }

    /**
     * Removes all elements from the heap, making it empty.
     * After calling this method, the heap will have a size of 0.
     * This operation runs in O(1) time as it simply clears the underlying ArrayList.
     */
    public void clear() {
        heap.clear();
    }

    /**
     * Returns the index of the parent of the current node
     * @param index current node's index
     * @return the index of the parent
     * @throws IndexOutOfBoundsException when wrong index or root's index is entered
     */
    private int getParent(int index) throws IndexOutOfBoundsException {
        //need to check because returned index may exist in the array
        if (index == 0) throw new IndexOutOfBoundsException("Root Node Can't Have Parents");
        if (index < 0 || index >= heap.size()) throw new IndexOutOfBoundsException();
        return (index - 1) / 2;   //floor division
    }

    /**
     * Checks if the node at the specified index has a parent.
     * Formula: i - 1 / 2 (floor division)
     * 
     * @param i the index of the node to check for a parent
     * @return true if the node has a parent, false otherwise
     */
    private boolean hasParent(int i) {
        return i != 0 && (i - 1) / 2 >= 0;
    }

    /**
     * Returns the index of the left child of the current node
     * @param index current node's index
     * @return the index of the left child
     * @throws RuntimeException when left child does not exist
     */
    private int getLeftChild(int index) {
        if (2 * index + 1 > heap.size() - 1) throw new RuntimeException("No left child");
        return 2 * index + 1;
    }

    /**
     * Returns the index of the right child of the current node
     * @param index current node's index
     * @return the index of the right child
     * @throws RuntimeException when right child does not exist
     */
    private int getRightChild(int index) {
        if (2 * index + 2 > heap.size() - 1) throw new RuntimeException("No right child");
        return 2 * index + 2;
    }

    /**
     * Swaps the elements at index i and j
     * @param i the first element's index
     * @param j the second element's index
     */
    private void swap(int i, int j) {
        if (i == j) return;
        T temp = heap.get(j);
        heap.set(j, heap.get(i));
        heap.set(i, temp);
    }

    /**
     * Bubble up logic when an element is inserted
     * @param i the index of the node to which a child is added
     * @throws IndexOutOfBoundsException when wrong index entered
     */
    private void bubbleUp(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= heap.size()) throw new IndexOutOfBoundsException();

        //continue swapping with parent until heap property is satisfied or we reach root
        while (this.hasParent(i)) {
            int parentIndex = (i - 1) / 2;
            
            //if heap property is satisfied, break
            if (heap.get(parentIndex).compareTo(heap.get(i)) >= 0) {
                break;
            }
                        
            //swap with parent and move up
            swap(parentIndex, i);
            i = parentIndex;
        }
    }

    /**
     * Bubble down logic when poll() is called
     * @param i the index of current node being checked for heapify down logic
     * @see poll()
     */
    private void bubbleDown(int i) {
       while(true) {
           int largestIndex = i;
           int leftChild = 2 * i + 1;
           int rightChild = 2 * i + 2;

           //compare with left child
           if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(largestIndex)) > 0) {
               largestIndex = leftChild;
           }

           //compare with right child
           if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(largestIndex)) > 0) {
               largestIndex = rightChild;
           }

           //if heap property is satisfied, break
           if (largestIndex == i) break;

           //swap with the larger child and continue
           swap(i, largestIndex);
           i = largestIndex;
       }
    }

}