package taejoong.chapter9.item58;

import java.util.Iterator;

class CustomIterator<T> implements Iterator<T> {

    CustomElement<T> currentElement;

    public CustomIterator(CustomList<T> customList) {
        currentElement = customList.getHead();
    }

    public boolean hasNext() {
        return currentElement != null;
    }

    public T next() {
        T data = currentElement.getData();
        currentElement = currentElement.getNext();
        return data;
    }
}
