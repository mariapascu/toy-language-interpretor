package Model;

import java.util.List;

public class MyList<T> implements MyIList<T>{
    private List<T> list;

    public MyList(List<T> list) {
        this.list = list;
    }

    @Override
    public void add(T el) {
        list.add(el);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Out:\n");
        for (T elem : this.list) {
            result.append(elem.toString() + "\n");
        }
        return result.toString();
    }
}
