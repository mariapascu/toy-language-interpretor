package Model;
import Exception.MyException;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<T>();
    }

    public MyStack(Stack<T> stack) {
        this.stack = stack;
    }

    @Override
    public T pop() throws MyException {
        if (this.stack.empty()) {
            throw new MyException("Empty stack!");
        }
        return this.stack.pop();
    }

    @Override
    public T top() throws MyException {
        if (this.stack.empty()) {
            throw new MyException("Empty stack!");
        }
        return this.stack.peek();
    }

    @Override
    public void push(T v) {
        this.stack.push(v);
    }

    @Override
    public boolean empty() {
        return stack.empty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Exe Stack:\n");
        for(int i = this.stack.size() - 1; i >= 0; i--) {
            result.append(this.stack.get(i).toString());
            result.append("\n");
        }
        return result.toString();
    }
}
