package it.unibo.inner.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class BasicIteratorWithPolicy<T> implements IterableWithPolicy<T> {

    private final T[] itemsToIterate;
    private Predicate<T> predicate;

    public BasicIteratorWithPolicy(final T[] elements, final Predicate<T> predicate) {
        this.itemsToIterate = Arrays.copyOf(elements, elements.length);
        this.predicate = predicate;
    }

    public BasicIteratorWithPolicy(final T[] elements) {
        this(elements, (elem) -> {
            return true;
        });
    }

    private class InnerBasicIteratorWithPolicy implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            if (getNextFilteredElementIndex() >= 0) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T item = itemsToIterate[getNextFilteredElementIndex()];
                currentIndex = getNextFilteredElementIndex() + 1;
                return item;
            } else {
                throw new NoSuchElementException();
            }
        }

        private int getNextFilteredElementIndex() {
            int nextItemIndex = -1;
            boolean foundNext = false;
            int tmpIndex = currentIndex;
            while (!foundNext && tmpIndex < itemsToIterate.length) {
                if (predicate.test(itemsToIterate[tmpIndex])) {
                    foundNext = true;
                    nextItemIndex = tmpIndex;
                }
                tmpIndex++;
            }
            return nextItemIndex;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new InnerBasicIteratorWithPolicy();
    }

    @Override
    public void setIterationPolicy(final Predicate<T> filter) {
        this.predicate = filter;
    }

}