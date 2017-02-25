package test;

public interface GenericStack<T> {
    public void push(T obj);

    public boolean hasNext();

    public boolean hasRoom();

    public T pop();
}
