import java.util.Iterator;
/**
 * A generic implementation of a Singly Linked List in Java.
 * Contains a private inner generic class {@code Node} containing properties each node.
 * @author Taskin Saadman
 */
public class SinglyLinkedList<T> implements Iterable<T> {
    private Node head;
    private int size;
    private Node tail;  //to keep track of the tail for optimized appendings O(1)

    /**
     * Constructs the {@code SinglyLinkedList} with size 0 and head pointing to {@code null}
     */
    public SinglyLinkedList() {
        head = null;    //SinglyLinkedList starts empty
        size = 0;
    }


    /**
     * Checks whether a SinglyLinkedList is empty or not
     * @return boolean true or false is returned
     */
    public boolean isEmpty() {
        return size == 0 && head == null;
    }


    /**
     * Appends a new value to the {@code SinglyLinkedList} and increments its size.
     * @param data the value to be added
     */
    public void add(T data) {
        if (head == null) { 
            this.head = new Node(data);    //adding an element to an empty List
            tail = head;      //tail is same as head if list has only 1 element
            size++;
            return;
        }

        tail.setNext(new Node(data));   //update current tail's next pointer
        tail = tail.getNext();         //update current tail
        size++;    
    }


    /**
     * Add an element to the beginning of the SinglyLinkedList
     * @param T the data to be added
     */
    public void addFirst(T data) {
        if (size == 0) { this.add(data); return; }  //same functionality as add()
        
        Node newHead = new Node(data);   newHead.setNext(head);

        if (size == 1) tail = head;     //if list had only 1 element previously, we need to update the tail based on old head

        head = newHead;   size++;
    }


    /**
     * Clears the entire instance.
     * Resets {@code head} to {@code null} and {@code size} to {@code 0}.
     */
    public void clear() {
        head = null; size = 0;
    }


    /**
     * Remove the head node and return its data
     * @return T the data inside head node
     * @throws NoSuchElementException if the list is empty
     */
    public T removeHead() throws NoSuchElementException {
        if (size == 0) throw new NoSuchElementException("Can't remove head from empty SinglyLinkedList");
        if (size == 1) tail = null; //if list had single element, head was equal to tail. so update it.
        T data = head.getData();
        head = head.getNext();  //update head
        size--;
        return data;
    }


    /**
     * Method to be overriden from Iterable<T> interface to make the collection traversible
     * through a for-each loop.
     * @return Iterator<T> an iterator object
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node currentNode = head;     //start from head node

            public boolean hasNext() { return currentNode != null; }    //since tail points to null

            public T next() throws NoSuchElementException {
                T retVal = currentNode.getData();
                if (currentNode == null) throw new NoSuchElementException();    //full list is already traversed
                currentNode = currentNode.getNext();    //advancing the iterator
                return retVal;    
            }
        }        
    }


    /**
     * Get the value at a specified index.
     * @param index the specified index.
     * @return T the value at that index.
     * @throws IndexOutOfBoundsException if out of bounds index was passed into the function.
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index should be between " + 0 + " & " + (size - 1));

        //Omega(1) best-case complexity if index refers to head or tail
        if (index == 0) return head.getData();
        if (index == size - 1) return tail.getData();

        Node current = head.getNext();  //otherwise it's somewhere in the middle [O(n) complexity]

        //traverse through the whole thing from Node position 1 upto the one in `index`
        for (int i = 1; i <= index; i++) current = current.getNext();
        return current.getData();
    }


    /**
     * Sets the value of a Node in a specific index.
     * Traverses the entire SinglyLinkedList until
     * @param index the specific index.
     * @param data the data to be set in that Node.
     * @throws IndexOutOfBoundsException if out of bounds index was passed into the function.
     */
    public void set(int index, T data) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index should be between " + 0 + " & " + (size - 1));


        
    }


    /**
     * Inserts a new Node at a specific index, shifting everything else to the right.
     * @param index index where insertion is to be done
     * @param value value of the new node to be added
     * @throws IndexOutOfBoundsException if out of bounds index is entered
     * @see 
     */
    public void insert(int index, T data) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("index should be between " + 0 + " & " + (size - 1));
        //if index is at last note, implement add()
        if(index == size) this.add(data);

        Node current = head;
        //reach upto the Node previous to the one in index
        for (int i = 0; i < index; i++) current = current.getNext();
        Node temp = current.getNext();  //store the previous next Node in the list
        current.setNext(new Node(data));
        current.getNext();
        current.setNext(temp.getData());
    }



    /**
     * Returns {@code true} or {@code false} if a value exists in the instance.
     * Traverses through the whole list until passed argument is found.
     * @param data the value to be found
     * @return boolean true or false
     */
    public boolean contains(T data) {
        for (T value : this) if (value.equals(data)) return true;
        return false;
    }


    /**
     * Returns the head node's data
     * @return data
     */
    public T getHead() {return head.getData();}


    /**
     * String representation of the {@code SinglyLinkedList} instance.
     * @return the {@code String} representation
     */
    public String toString() {
        if (head == null && tail == null) return "[ ]";

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;

        while (current != tail) {
            sb.append(current.getData() + " -> ");
            current = current.getNext();
        }

        sb.append(tail.getData() + "]");
        return sb.toString();
    }



    /**
     * Private inner class defining Node behavior.
     * Each node has a value and a reference to the next value.
     * Generic Type Parameter {@code T} is inherited from outer class.
     */
    private class Node {
        T data;
        Node next;

        /**
         * Constructs a node with {@code data} passed as an argument and next pointer as {@code null}
         */
        Node(T data) {
            this.data = data;
            this.next = null;   //next is null by default
        }


        /**
         * Set the pointer to the next node for current {@code Node} instance.
         * @param next the next node which is being pointed by the current node.
         */
        void setNext(Node next) {
            this.next = next;
        }


        /**
         * Get the next {@code Node} in the List
         * @return returns the next node's reference
         */
        Node getNext() {
            return this.next;
        }


        /**
         * Get the data stored in the current {@code Node} instance
         * @return returns current instance's data
         */
        T getData() {
            return this.data;
        }


        /**
         * Sets the {@code data} field of current {@code Node} instance
         * @param data the new data entered
         */
        void setData(T data) {
            this.data = data;
        }

    }

}