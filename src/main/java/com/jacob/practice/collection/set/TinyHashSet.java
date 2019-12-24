package com.jacob.practice.collection.set;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class TinyHashSet<E> implements Set<E> {

    private int hashBucketCapacity = 2;
    private List<List<E>> hashValueArray = new ArrayList<>(hashBucketCapacity);

    public TinyHashSet() {
        IntStream.range(0, hashBucketCapacity).forEach(i -> hashValueArray.add(new ArrayList<>()));
    }

    private int simpleHash(int hashCode) {
        return hashCode % hashBucketCapacity;
    }

    private void rehash() {

    }

    @Override
    public boolean add(E e) {
        if (Objects.isNull(e)) {
            throw new NullPointerException();
        }

        rehash();

        int hashCode = e.hashCode();
        int index = simpleHash(hashCode);
        return hashValueArray.get(index).add(e);
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            int hashBucketIndex = 0;
            int arrayIndex = 0;

            @Override
            public boolean hasNext() {
                if (hashBucketIndex >= hashValueArray.size()) {
                   return false;
                }

                if (Objects.isNull(hashValueArray.get(hashBucketIndex))
                || hashValueArray.get(hashBucketIndex).size() <= arrayIndex) {
                    hashBucketIndex++;
                    arrayIndex = 0;
                    return hasNext();
                }
                return true;
            }

            @Override
            public E next() {
                E e = hashValueArray.get(hashBucketIndex).get(arrayIndex);
                arrayIndex++;
                return e;
            }
        };
    }

    @Override
    public int size() {
        return hashValueArray.stream().mapToInt(list -> list.size()).sum();
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
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    public static void main(String[] args) {
        TinyHashSet<Integer> set = new TinyHashSet<>();
        set.add(1);
        set.add(4);
        set.add(8);
        set.add(11);
        set.add(184);
        set.add(22);
        set.add(84);
        set.add(77);
        set.stream().forEach(System.out::println);
    }

}
