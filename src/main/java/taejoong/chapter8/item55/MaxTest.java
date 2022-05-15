package taejoong.chapter8.item55;

import java.util.*;

public class MaxTest {
    public static <E extends Comparable<E>> E maxThrowException(Collection<E> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("empty collection");
        }

        E result = null;
        for (E e : collection) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return result;
    }

    public static <E extends Comparable<E>> Optional<E> maxReturnOptional(Collection<E> collection) {
        if (collection.isEmpty()) {
            return Optional.empty();
        }

        E result = null;
        for (E e : collection) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }

        return Optional.of(result);
    }

    public static <E extends Comparable<E>> Optional<E> maxUsingStream(Collection<E> collection) {
        return collection.stream()
                .max(Comparator.naturalOrder());
    }
}
