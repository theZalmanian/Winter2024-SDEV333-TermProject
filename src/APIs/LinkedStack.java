package APIs;

import List.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
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
     * The top of the stack (most recently added node)
     */
    private Node top;

    /**
     * The number of items stored in this stack
     */
    private int size;

    /**
     * Add an item to the stack.
     *
     * @param item the item to be added
     */
    @Override
    public void push(E item) {
        // create new node containing given item
        Node newNode = new Node(item);

        // if the list is not empty
        if(!isEmpty()) {
            // point new node at top, so it isn't lost
            newNode.next = top;
        }

        // override top with newly created node
        // (making new node first node in stack)
        top = newNode;

        // account for new item in stack
        size++;
    }

    /**
     * Removes the most recently added item from the stack.
     *
     * @return the item that was removed
     */
    @Override
    public E pop() {
        // if stack contains no items, one cannot be removed
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot retrieve item from empty Stack");
        }

        // get requested item from topmost node
        E requestedItem = top.item;

        // overwrite top with node "under it",
        // if no more remain, top will become null, making stack empty
        top = top.next;

        // account for removal of item from stack
        size--;

        return requestedItem;
    }

    /**
     * Returns the item at the top of the stack.
     * Does not modify the stack or the item at the top.
     *
     * @return item at the top of the stack.
     */
    @Override
    public E peek() {
        // if buffer contains no items, one cannot be retrieved
        if(isEmpty()) {
            throw new NoSuchElementException("Cannot retrieve item from empty Stack");
        }

        // get and return item stored in topmost node
        return top.item;
    }

    /**
     * Checks to see if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == null && size == 0;
    }

    /**
     * Returns a count of the number of items in the stack.
     *
     * @return the number of items in the stack
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
        return null;
    }
}