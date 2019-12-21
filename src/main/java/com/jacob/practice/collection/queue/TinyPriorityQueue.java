package com.jacob.practice.collection.queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class TinyPriorityQueue<E extends Comparable<E>> implements Queue<E> {

    // use array to mock (min) heap, and the index 0 will not store value.
    // to simplify it, use dynamic array.
    private List<E> array = new ArrayList<>();

    @Override
    public boolean add(E e) {
        array.add(e);
        if (0 == (array.size() - 1)) {
            return true;
        }

        // add the value and compare with parent value, than do swap util meeting the requirement of heap
        for (int i = array.size() - 1; i > 0;) {
            int parentIndex = (i - 1) / 2;
            if (e.compareTo(array.get(parentIndex)) > 0) {
                break;
            } else {
                array.set(i, array.get(parentIndex));
                array.set(parentIndex, e);
                i = parentIndex;
            }
        }
        return true;
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
        E result = array.get(0);
        // set last value to the root, rm the last
        array.set(0, array.get(array.size() - 1));
        array.remove(array.size() - 1);

        // from top-down order to compare with child
        for (int parentIndex = 0; parentIndex < ((array.size() - 1) / 2);) {
            int leftChildIndex = 2 * parentIndex + 1;
            int rightChildIndex = 2 * parentIndex + 2;

            if (array.get(parentIndex).compareTo(array.get(leftChildIndex)) <= 0
            && array.get(parentIndex).compareTo(array.get(rightChildIndex)) <= 0) {
                break;
            } else if (array.get(leftChildIndex).compareTo(array.get(rightChildIndex)) > 0) {
                E temp = array.get(parentIndex);
                array.set(parentIndex, array.get(rightChildIndex));
                array.set(rightChildIndex, temp);
                parentIndex = rightChildIndex;
            } else {
                E temp = array.get(parentIndex);
                array.set(parentIndex, array.get(leftChildIndex));
                array.set(leftChildIndex, temp);
                parentIndex = leftChildIndex;
            }
        }
        return result;
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
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
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
    public boolean remove(Object o) {
        return false;
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
        TinyPriorityQueue<Integer> priorityQueue = new TinyPriorityQueue<>();
        priorityQueue.add(8);
        priorityQueue.add(9);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(7);
        priorityQueue.add(-1);
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());
        System.out.println(priorityQueue.poll());

    }
}
