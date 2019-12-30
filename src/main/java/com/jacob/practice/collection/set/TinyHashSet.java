package com.jacob.practice.collection.set;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class TinyHashSet<E> implements Set<E> {

    private int hashBucketCapacity = 2;

    private int rehashFactor = 0; // max length of array in bucket

    private List<List<E>> hashValueArray = new ArrayList<>(hashBucketCapacity);

    public TinyHashSet() {
        IntStream.range(0, hashBucketCapacity).forEach(i -> hashValueArray.add(new ArrayList<>()));
    }

    private int simpleHash(int hashCode) {
        return hashCode % hashBucketCapacity;
    }

    private boolean checkExist(List<E> list, E e) {
        if (list.size() == 0) {
            return false;
        }
        for (E innerE: list) {
            if (e.equals(innerE)) {
                return true;
            }
        }
        return false;
    }

    private void refreshRehashFactor(int size) {
        if (size > rehashFactor) {
            rehashFactor = size;
        }
    }

    // when list size more than 4, double increase the bucket list
    private void rehash() {
        if (rehashFactor <= 2) {
            return;
        }

        rehashFactor = 0;
        hashBucketCapacity *= 2;

        List<List<E>> oldHashValueArray = hashValueArray;
        hashValueArray = new ArrayList<>(hashBucketCapacity);
        IntStream.range(0, hashBucketCapacity).forEach(i -> hashValueArray.add(new ArrayList<>()));
        oldHashValueArray.forEach(array -> array.forEach(e -> add(e)));
    }

    @Override
    public boolean add(E e) {
        if (Objects.isNull(e)) {
            throw new NullPointerException();
        }

        rehash();

        int hashCode = e.hashCode();
        int index = simpleHash(hashCode);
        boolean isExist = checkExist(hashValueArray.get(index), e);
        if (isExist) {
            return false;
        }
        refreshRehashFactor(hashValueArray.get(index).size() + 1);
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
        set.add(8);
        set.add(11);
        set.add(184);
        set.add(22);
        set.add(84);
        set.add(77);
        set.add(76);
        set.add(66);
        set.add(36);
        set.add(16);
        set.stream().forEach(System.out::println);
    }

}
