package Model;
import Exception.MyException;

public interface MyIStack<T> {
    T pop() throws MyException;
    T top() throws  MyException;
    void push(T v);
    boolean empty();
    String toString();
}
