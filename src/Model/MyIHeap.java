package Model;

import java.util.Map;

public interface MyIHeap<T> {
    int allocate(T res);
    T readAddr(Integer addr);
    void writeAddr(Integer addr, T val);
    void setContent(Map<Integer, T> newHeap);
    Map<Integer, T> getContent();
}
