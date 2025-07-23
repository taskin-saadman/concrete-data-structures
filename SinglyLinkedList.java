import java.util.Iterator;
/**
 * A generic implementation of a Singly Linked List in Java.
 * Contains a private inner generic class {@code Node} containing properties each node.
 * @author Taskin Saadman
 */
public class SinglyLinkedList<T> implements Iterable<T> {
    private Node head;
    private int size;

    /**
     * Constructs the {@code SinglyLinkedList} with size 0 and head pointing to {@code null}
     */
    public SinglyLinkedList() {
        head = null;    //SinglyLinkedList starts empty
        size = 0;
    }


    /**
     * Returns the Node stored inside the head variable
     * @return Node
     */
    private Node


    /**
     * Adds a new value to the end of the {@code SinglyLinkedList} and increments size
     * @param data the value to be added
     */
    public void add(T data) {
        if (head == null) this.head = new Node(data);    //adding an element for the first time

        else {
            Node next = head.setNext(new Node(data)); //adding consequent elements
            this.head = next;   //replace head for progression through the List
            }

        size++;
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
    }


}