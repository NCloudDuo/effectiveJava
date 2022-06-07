package taejoong.chapter9.item58;

import java.util.Iterator;

class CustomList<T> implements Iterable<T> {

    CustomElement<T> head;
    CustomElement<T> tail;

    public void add(T data) {
        CustomElement<T> node = new CustomElement<>(data, null);
        if (head == null)
            head = tail = node;
        else {
            tail.setNext(node);
            tail = node;
        }
    }

    public CustomElement<T> getHead() {
        return head;
    }

    public Iterator<T> iterator() {
        return new CustomIterator<T>(this);
    }
}
