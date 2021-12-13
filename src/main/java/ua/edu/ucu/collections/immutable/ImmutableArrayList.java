package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private Object[] arr;
    private int len;

    public ImmutableArrayList(Object[] elements) {
        this.arr = elements.clone();
        this.len = elements.length;
    }

    public ImmutableArrayList() {
        this.arr = new Object[0];
        this.len = 0;
    }


    @Override
    public ImmutableList add(Object e) {
        return this.add(this.size(), e);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        Object[] newArr = Arrays.copyOf(this.arr, this.size()+1);
        newArr[index] = e;

        for (int i = index+1; i <= this.size(); i++) {
            newArr[i] = this.arr[i-1];
        }
        return new ImmutableArrayList(newArr);

    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return this.addAll(this.size(), c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {

        if (index > this.size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newArr = Arrays.copyOf(this.arr, this.size()+c.length);
        int j = 0;
        while (j < c.length) {
            newArr[index] = c[j];
            j++;
            index++;
        }
        for (int i = index; i < c.length + this.size(); i++) {
            newArr[i] = this.arr[i-c.length];
        }
        return new ImmutableArrayList(newArr);

    }

    @Override
    public Object get(int index) {
        if (0 <= index && index < this.size() && this.len > 0) {
            return this.arr[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableList remove(int index) {
        if (index > this.size() || index < 0 || this.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] array = new Object[this.size()-1];
        int i = 0;
        for (int j = 0; j<this.size(); j++) {
            if (j!=index) {
                array[i] = this.arr[j];
                i++;
            }
        }
        return new ImmutableArrayList(array);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index > this.size() || index < 0 || this.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        Object[] array = Arrays.copyOf(this.arr, this.size());
        array[index] = e;
        return new ImmutableArrayList(array);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i<this.size(); i++) {
            if (this.arr[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.len;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        if (this.len == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(this.arr, this.len);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Object obj: this.arr) {
            str.append(obj);
            str.append(" ");
        }
        str.delete(this.size()*2-1, this.size()*2);
        return str.toString();
    }
}
