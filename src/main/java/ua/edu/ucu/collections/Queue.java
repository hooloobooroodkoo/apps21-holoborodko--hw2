package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;
import ua.edu.ucu.collections.immutable.Node;

public class Queue {
    private ImmutableLinkedList queue;

    public Queue() {
        this.queue = new ImmutableLinkedList();
    }

    public Queue(Object[] objects) {
        this.queue = new ImmutableLinkedList(objects);
    }

    public Object peek() {
        return this.queue.getLast();
    }

    public Object dequeue() {
        Object remove = this.peek();
        this.queue = this.queue.removeLast();
        return remove;
    }
    public void enqueue(Object e) {
        this.queue = this.queue.addFirst(e);
    }
    public String toString() {
        return this.queue.toString();
    }
}
