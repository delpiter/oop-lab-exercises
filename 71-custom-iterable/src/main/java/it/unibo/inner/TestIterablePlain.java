package it.unibo.inner;

import static it.unibo.inner.test.Assertions.assertContentEqualsInOrder;

import java.util.List;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.iterators.BasicIteratorWithPolicy;

public class TestIterablePlain {

    private TestIterablePlain() {
    }

    private static <T> IterableWithPolicy<T> getIterableWithPolicy(T[] elements) {
        return new BasicIteratorWithPolicy<>(elements);
    }

    public static void main(final String[] args) {
        String[] test1 = { "pippo", "pluto", "paperino" };

        IterableWithPolicy<String> evenIterable = getIterableWithPolicy(test1);
        assertContentEqualsInOrder(evenIterable, List.of("pippo", "pluto", "paperino"));
        System.out.println("done evenIterable");

        String[] test2 = {};

        IterableWithPolicy<String> emptyIterable = getIterableWithPolicy(test2);
        assertContentEqualsInOrder(emptyIterable, List.of());
        System.out.println("done emptyIterable");

        String[] test3 = { "foo" };

        IterableWithPolicy<String> oneIterable = getIterableWithPolicy(test3);
        assertContentEqualsInOrder(oneIterable, List.of("foo"));
        System.out.println("done oneIterable");
    }
}
