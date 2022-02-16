package icamp;

import java.util.Iterator;
import java.util.function.Consumer;

public class DirectionalIterator<E> implements Iterator<E> {
    private Direction direction;

    public DirectionalIterator(Direction direction) {
        this.direction = direction;
    }

    public boolean hasNext() {
        return false;
    }

    public E next() {
        return null;
    }

    public void remove() {
        Iterator.super.remove();
    }

    public void forEachRemaining(Consumer<? super E> action) {
        Iterator.super.forEachRemaining(action);
    }

    public static enum Direction {
        Forward,
        Backward
    }
}
