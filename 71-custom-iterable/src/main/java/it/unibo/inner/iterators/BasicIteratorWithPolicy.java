package it.unibo.inner.iterators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class BasicIteratorWithPolicy<T> implements IterableWithPolicy<T> {

    private final T[] itemsToIterate;

    public BasicIteratorWithPolicy(final T[] elements) {
        this.itemsToIterate = Arrays.copyOf(elements, elements.length);
    }

    private class InnerBasicIteratorWithPolicy implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return this.currentIndex < itemsToIterate.length;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T item = itemsToIterate[currentIndex];
                currentIndex++;
                return item;
            } else {
                throw new NoSuchElementException();
            }

        }

    }

    @Override
    public Iterator<T> iterator() {
        return new InnerBasicIteratorWithPolicy();
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        throw new UnsupportedOperationException("Unimplemented method 'setIterationPolicy'");
    }

}