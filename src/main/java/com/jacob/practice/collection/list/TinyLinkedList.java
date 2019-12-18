package com.jacob.practice.collection.list;

import lombok.Data;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class TinyLinkedList<E> implements List<E>, Deque<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    @Data
    private class Node<E> {
        private E value;
        private Node<E> pre;
        private Node<E> next;

        public Node(E e, Node<E> pre, Node<E> next) {
            value = e;
            this.pre = pre;
            this.next = next;
        }
    }

    private void checkOutOfBounds(int index) {
        if (index < 0 || (size - index) < 1) {
            throw new IndexOutOfBoundsException("max index is " + (size - 1) + " while input index is " + index);
        }
    }

    private Node<E> getNode(int index) {
        Node<E> loopNext = head;

        // can also use recursion
        int listIndex = 0;
        while (Objects.nonNull(loopNext)) {
            if (index == listIndex) {
                return loopNext;
            }
            listIndex++;
            loopNext = loopNext.next;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean add(E e) {
        if(Objects.isNull(head)) {
            head = new Node<>(e, null, null);
            tail = head;
            size++;
            return true;
        }
        tail.next = new Node<>(e, tail, null);
        tail = tail.next;
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        checkOutOfBounds(index);
        Node<E> node = getNode(index);
        return node.value;
    }

    @Override
    public E set(int index, E element) {
        checkOutOfBounds(index);
        Node<E> currentNode = getNode(index);
        Node<E> freshNode = new Node<>(element, currentNode.pre, currentNode.next);
        currentNode.next.pre = freshNode;
        if (Objects.nonNull(currentNode.pre)) {
            currentNode.pre.next = freshNode;
        } else {
            head = freshNode;
        }
        return currentNode.value;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 && index - size > 0) {
            throw new IndexOutOfBoundsException("max index is " + size + " while input index is " + index);
        }
        Node<E> currentNode = getNode(index);
        Node<E> freshNode = new Node<>(element, currentNode.pre, currentNode);
        if (Objects.nonNull(currentNode.pre)) {
            currentNode.pre.next = freshNode;
        } else {
            head = freshNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        checkOutOfBounds(index);
        Node<E> currentNode = getNode(index);
        if (Objects.isNull(currentNode.pre)) {
            head = currentNode.next.pre;
            head.next = currentNode.next;
        } else {
            currentNode.next.pre = currentNode.pre;
            currentNode.pre.next = currentNode.next;
        }
        size--;
        return currentNode.value;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        add(size, e);
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
        // need to throw ex when the list is empty
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(--size);
    }

    @Override
    public E pollFirst() {
        return remove(0);
    }

    @Override
    public E pollLast() {
        return remove(--size);
    }

    @Override
    public E getFirst() {
        return get(0);
    }

    @Override
    public E getLast() {
        return get(--size);
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
    public Iterator<E> iterator() {

        return new Iterator<E>() {

            Node<E> currentNode = head;
            @Override
            public boolean hasNext() {
                return Objects.nonNull(currentNode);
            }

            @Override
            public E next() {
                E e = currentNode.value;
                currentNode = currentNode.next;
                return e;
            }
        };
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
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
        TinyLinkedList<Integer> linkedList = new TinyLinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        System.out.println(linkedList.get(2));
        System.out.println("size is " + linkedList.size());

        linkedList.set(2, 8);
        System.out.println("size is " + linkedList.size());
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));
        System.out.println(linkedList.get(3));

        linkedList.remove(2);
        System.out.println("size is " + linkedList.size());
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));

        linkedList.add(2, 7);
        System.out.println("size is " + linkedList.size());
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(1));
        System.out.println(linkedList.get(2));
        System.out.println(linkedList.get(3));


        System.out.println("foreach ");
        linkedList.stream().forEach(System.out::print);

        linkedList.pollFirst();
        System.out.println("after poll first " + linkedList.get(0));
        System.out.println("after poll first " + linkedList.get(1));
        System.out.println("after poll first " + linkedList.get(2));
        linkedList.pollFirst();
        System.out.println("after poll first " + linkedList.get(0));
        System.out.println("after poll first " + linkedList.get(1));
        linkedList.pollFirst();
        System.out.println("after poll first " + linkedList.get(0));
        linkedList.pollFirst();


    }

}
