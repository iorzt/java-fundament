package com.jacob.practice.collection.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TinyArrayList<E> implements List<E> {

    private Integer capacity = 2;
    // place to store value
    private Object element[] = new Object[capacity];
    // the empty element position after the last element value
    private Integer tailPosition = 0;

    /**
     * check the current capacity can store new element
     * @return false if there is no capacity
     */
    private boolean checkCapacity() {
        if (tailPosition + 1 > capacity) {
            return false;
        }
        return true;
    }

    /**
     * scale up the array capacity
     *
     */
    private void scaleUp() {
        capacity = capacity * 2;
        Object[] newElement = new Object[capacity];
        for (int i = 0; i < element.length; i++) {
            newElement[i] = element[i];
        }
        element = newElement;
    }

    /**
     * check the incoming index is below the array capacity
     * @param index
     */
    private void checkOutOfBoundsCond(int index) {
        if(index > tailPosition) {
            throw new IndexOutOfBoundsException("max length is " + (tailPosition - 1) + " but input position is " + index);
        }
    }

    @Override
    public boolean add(E e) {
        // check the capacity
        if (!checkCapacity()) {
            // scale up capacity
            scaleUp();
        }

        element[tailPosition] = e;
        tailPosition ++;
        return true;
    }

    @Override
    public E get(int index) {
        checkOutOfBoundsCond(index);

        return (E) element[index];
    }

    @Override
    public E set(int index, E element) {
        checkOutOfBoundsCond(index);
        E previousElement = (E) this.element[index];
        this.element[index] = element;
        return previousElement;
    }

    @Override
    public void add(int index, E element) {
        checkCapacity();
        for (int i = tailPosition; i >= index ; i--) {
            this.element[i + 1] = this.element[i];
        }
        this.element[index] = element;
        tailPosition ++;
    }

    @Override
    public E remove(int index) {
        checkOutOfBoundsCond(index);
        E previousE = (E) element[index];
        for (int i = index; i < tailPosition; i++) {
            element[i] = element[i + 1];
        }
        tailPosition --;
        return previousE;
    }

    @Override
    public int size() {
        return tailPosition - 1;
    }

    @Override
    public boolean isEmpty() {
        return tailPosition == 0;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int iteratorTailPosition = 0;

            @Override
            public boolean hasNext() {
                return iteratorTailPosition < tailPosition;
            }

            @Override
            public E next() {
                E e =  (E) element[iteratorTailPosition];
                iteratorTailPosition ++;
                return e;
            }
        };
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    public static void main(String[] args) {
        List<Integer> list = new TinyArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);
        list.add(5);

        list.set(2, 7);
        list.remove(3);
        list.add(3, 6);

        list.forEach(System.out::println);
        System.out.println(list.isEmpty());
        System.out.println(list.size());

    }
}
