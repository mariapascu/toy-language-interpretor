package Model;

import java.util.Map;

public class MyHeap<T> implements MyIHeap<T> {
    int address;
    Map<Integer, T> heap;

    public MyHeap(Map<Integer, T> heap) {
        this.heap = heap;
        this.address = 0;
    }

    @Override
    public int allocate(T res) {
        address++;
        this.heap.put(address, res);
        return address;
    }

    @Override
    public T readAddr(Integer addr) {
        return this.heap.get(addr);
    }

    @Override
    public void writeAddr(Integer addr, T val) {
        this.heap.put(addr, val);
    }

    @Override
    public void setContent(Map<Integer, T> newHeap) {
        this.heap = newHeap;
    }

    @Override
    public Map<Integer, T> getContent() {
        return this.heap;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Heap:\n");
        for (Integer key : this.heap.keySet()) {
            result.append(key.toString() + " --> " + this.heap.get(key).toString() + "\n");
        }
        return result.toString();
    }
}
