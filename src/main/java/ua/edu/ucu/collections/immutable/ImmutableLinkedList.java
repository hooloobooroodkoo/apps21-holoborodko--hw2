package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public final class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int len;
    private Object[] values;

    public static class Node {
        private Object value;
        private Node next;

        public Node(Object val) {
            this.value = val;
        }
        public void setNext(Node node) {
            this.next = node;
        }
        public Node getNext() {
            return this.next;
        }
        public Object getValue() {
            return this.value;
        }
    }

    public ImmutableLinkedList(Object[] elements) {
        this.head = new Node(elements[0]);
        this.tail = new Node(elements[elements.length-1]);
        this.len = elements.length;
        this.values = Arrays.copyOf(elements, elements.length);
        Node current_node = this.head;
        for (int i = 1; i < elements.length-1; i++) {
            current_node.next = new Node(elements[i]);
            current_node = current_node.next;
        }
        current_node.next = this.tail;
        this.tail.next = null;
    }

    public ImmutableLinkedList() {
            this.head = null;
            this.tail = null;
            this.len = 0;

    }

    @Override
    public ImmutableList add(Object e) {
        return this.add(this.len, e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        if (index < 0 || index > this.len) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newLinkedList = Arrays.copyOf(this.values, this.len+1);
        newLinkedList[index] = e;
        for (int i = index; i < this.len; i++) {
            newLinkedList[i+1] = this.values[i];
        }
        return new ImmutableLinkedList(newLinkedList);
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return this.addAll(this.len, c);
    }
    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > this.len) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newLinkedList = Arrays.copyOf(this.values, this.len+c.length);
        int j = 0;
        while (j < c.length) {
            newLinkedList[index] = c[j];
            j++;
            index++;
        }
        for (int i = index; i < c.length + this.len; i++) {
            newLinkedList[i] = this.values[i-c.length];
        }

        return new ImmutableLinkedList(newLinkedList);
    }
    @Override
    public Object get(int index) {
        if (index < 0 || index >= this.len || this.len == 0) {
            throw new IndexOutOfBoundsException();
        }
        int i = 0;
        Node current = this.head;
        while (i != index) {
            current = current.next;
            i++;
        }
        return current.getValue();
    }

    public ImmutableList remove(int index) {
        if (index < 0 || index >= this.len || this.len == 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] array = new Object[this.len-1];
        int i = 0;
        for (int j = 0; j<this.len; j++) {
            if (j!=index) {
                array[i] = this.values[j];
                i++;
            }
        }
        return new ImmutableLinkedList(array);
    }

    public ImmutableList set(int index, Object e) {
        if (index > this.len || index < 0 || this.len == 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] array = Arrays.copyOf(this.values, this.len);
        array[index] = e;
        return new ImmutableLinkedList(array);
    }


    public int indexOf(Object e) {
        int i = 0;
        Node current = this.head;
        while (i!=this.len-1) {
            if (current.getValue() == e) {
                return i;
            }
            i ++;
            current = current.next;
        }
        return -1;

    }

    public int size() {
        return this.len;
    }

    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    public boolean isEmpty() {
        if (this.len == 0) {
            return true;
        }
        return false;
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.values, this.len);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node current_node = this.head;
        while (current_node!=null) {
            str.append(current_node.getValue());
            current_node = current_node.next;
            str.append(" ");
        }
//        str.deleteCharAt(this.len*2-1);
        return str.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        Object[] objects = new Object[this.len + 1];
        objects[0] = e;
        for (int i=0; i<this.len; i++){
            objects[i+1] = this.values[i];
        }
        return new ImmutableLinkedList(objects);
    }

    public ImmutableLinkedList addLast(Object e) {
        return null;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public Object getFirst() {
        return this.head.getValue();
    }

    public Object getLast() {
        return this.tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) this.remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) this.remove(this.len-1);
    }
}
