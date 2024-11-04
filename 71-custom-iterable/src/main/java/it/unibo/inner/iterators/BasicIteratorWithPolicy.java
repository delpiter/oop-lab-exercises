package it.unibo.inner.iterators;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class BasicIteratorWithPolicy<T> implements IterableWithPolicy<T> {

    private final T[] itemsToIterate;

    public BasicIteratorWithPolicy(T[] elements) {
        this.itemsToIterate = elements;
    }

    class InnerBasicIteratorWithPolicy implements Iterator<T> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return (this.currentIndex + 1) < BasicIteratorWithPolicy.this.itemsToIterate.length;
        }

        @Override
        public T next() {
            currentIndex++;
            return BasicIteratorWithPolicy.this.itemsToIterate[currentIndex];
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