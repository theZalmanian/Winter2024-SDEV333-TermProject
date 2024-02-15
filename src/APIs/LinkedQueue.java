package APIs;

import Stack.LinkedStack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    /**
     * A container which contains an item and a connection to another Node
     */
    private class Node {
        /**
         * The item stored within the Node
         */
        E item;

        /**
         * The node this Node is pointing to
         */
        Node next;

        /**
         * Creates a node, and stores the given item within it
         * @param item the item being stored in node
         */
        public Node(E item) {
            // store given item
            this.item = item;

            // node starts off disconnected
            next = null;
        }
    }

    /**
     * The front of the queue (least recently added node)
     */
    private Node front;

    /**
     * The back of the queue (most recently added node)
     */
    private Node back;

    /**
     * The number of items stored in this stack
     */
    private int size;

    /**
     * Constructs an empty LinkedStack
     */
    public LinkedQueue() {
        // queue starts off empty
        front = null;
        back = null;
        size = 0;
    }

    /**
     * Add an item to the queue.
     *
     * @param item the item to be added
     */
    @Override
    public void enqueue(E item) {
        // create new node containing given item
        Node newNode = new Node(item);

        // if the queue is empty
        if(isEmpty()) {
            // make new node front of queue
            front = newNode;
        }

        else {
            // if queue is not empty,
            // add new node to the back of queue
            front.next = newNode;
        }

        // account for new item in queue
        size++;
    }

    /**
     * Remove an item from the queue.
     *
     * @return the item that was removed
     */
    @Override
    public E dequeue() {
        // if queue contains no items, one cannot be removed
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot retrieve item from empty Queue");
        }

        // get requested item from node at front
        E requestedItem = front.item;

        // overwrite current node at front with next in line
        // if no more remain, front will become null
        front = front.next;

        // in the event that queue is now empty and front is null,
        // overwrite last item
        if(isEmpty()) {
            back = null;
        }

        // account for removal of item from stack
        size--;

        return requestedItem;
    }

    /**
     * Checks to see if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0 && front == null;
    }

    /**
     * Returns a count of the number of items in the queue.
     *
     * @return the number of items in the queue
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new LinkedQueueIterator();
    }

    /**
     * Implementation of an Iterator for the LinkedQueue class
     */
    private class LinkedQueueIterator implements Iterator<E>
    {
        /**
         * The current Node being tracked by the Iterator
         */
        private Node current;

        /**
         * Constructs a LinkedQueue iterator, with the front node tracked first
         */
        LinkedQueueIterator() {
            current = front;
        }

        /**
         * Checks if list contains another element, and returns true/false accordingly
         * @return true if list contains another element; otherwise false
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Gets and returns the start/front item in the list
         * @return the start/front item in the list
         */
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            // get item at start/front of list
            E currItem = current.item;

            // bring the next item over
            current = current.next;

            return currItem;
        }
    }
}