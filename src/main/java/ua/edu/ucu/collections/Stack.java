package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList stack;

    public Stack() {
        this.stack = new ImmutableLinkedList();
    }

    public Stack(Object[] objects) {
        this();
        for (int i = 0; i < objects.length; i++) {
            push(objects[i]);
        }
    }

    public Object peek() {
        return this.stack.getFirst();
    }

    public void push(Object e) {
        this.stack = this.stack.addFirst(e);
    }

    public Object pop() {
        Object remove = this.stack.getFirst();
        this.stack = this.stack.removeFirst();
        return remove;
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }
}
