package com.jacob.practice.collection.queue;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

public class TinyArrayDeque<E> implements Deque<E> {

    private int incr = 2; // increment value
    private int capacity = incr * 2 + 1;

    transient Object[] array = new Object[capacity];

    private int preHeadIndex = incr;
    private int afterTailIndex = incr;


    private void increaseCapacity() {
        if (afterTailIndex - preHeadIndex - 1 < capacity
        && preHeadIndex > -1
        && afterTailIndex < capacity) {
            return;
        }

        // double increase
        Object[] oldArray = array;

        array = new Object[incr * 2 + capacity];
        System.arraycopy(oldArray, 0, array, incr, capacity);
        preHeadIndex = incr - 1;
        afterTailIndex = afterTailIndex + incr;
        capacity = incr * 2 + capacity;
    }

    @Override
    public void addFirst(E e) {
        if (Objects.isNull(e)) {
            throw new NullPointerException();
        }
        increaseCapacity();
        array[preHeadIndex] = e;
        if (afterTailIndex - preHeadIndex == 0) {
            afterTailIndex++;
        }
        preHeadIndex--;
        for (Object i: array) {
            System.out.print(i);
        }
        System.out.println();
    }

    @Override
    public void addLast(E e) {
        if (Objects.isNull(e)) {
            throw new NullPointerException();
        }
        increaseCapacity();
        array[afterTailIndex] = e;
        if (afterTailIndex - preHeadIndex == 0) {
            preHeadIndex--;
        }
        afterTailIndex++;
        for (Object i: array) {
            System.out.print(i);
        }
        System.out.println();
    }

    @Override
    public E pollFirst() {
        if (afterTailIndex - preHeadIndex == 0) {
            return null;
        }

        return (E) array[++preHeadIndex];
    }

    @Override
    public E pollLast() {
        if (afterTailIndex - preHeadIndex == 0) {
            return null;
        }
        return (E) array[--afterTailIndex];
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
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
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public boolean isEmpty() {
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

    public static void main(String[] args) {
        TinyArrayDeque<Integer> arrayDeque = new TinyArrayDeque<>();

        arrayDeque.addFirst(3);
        arrayDeque.addFirst(2);
        arrayDeque.addFirst(1);
        arrayDeque.addLast(4);
        arrayDeque.addLast(5);
        arrayDeque.addLast(6);

        System.out.println(arrayDeque.pollFirst());
        System.out.println(arrayDeque.pollFirst());
        System.out.println(arrayDeque.pollFirst());
        System.out.println(arrayDeque.pollLast());
        System.out.println(arrayDeque.pollLast());


        arrayDeque.addLast(5);
        arrayDeque.addFirst(3);
        arrayDeque.addFirst(2);
        arrayDeque.addLast(6);
        arrayDeque.addLast(7);
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(0);
        arrayDeque.addFirst(-1);
        arrayDeque.addFirst(-2);
        arrayDeque.addLast(8);
        arrayDeque.addLast(9);
        arrayDeque.addLast(10);
        arrayDeque.addLast(11);
        arrayDeque.addLast(12);
        arrayDeque.addLast(13);

        System.out.println();
        System.out.println(arrayDeque.pollFirst());
        System.out.println(arrayDeque.pollFirst());
        System.out.println(arrayDeque.pollFirst());
        System.out.println(arrayDeque.pollLast());
        System.out.println(arrayDeque.pollLast());
    }
}
