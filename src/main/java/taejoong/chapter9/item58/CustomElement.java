package taejoong.chapter9.item58;

class CustomElement<T> {
    T data;
    CustomElement<T> next;

    public CustomElement(T data, CustomElement<T> next) {
        this.data = data;
        this.next = next;
    }

    public void setNext(CustomElement<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }
    public CustomElement<T> getNext() {
        return next;
    }
}
