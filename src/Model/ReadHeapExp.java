package Model;
import Exception.MyException;

public class ReadHeapExp extends Exp {
    private String var;

    public ReadHeapExp(String var) {
        this.var = var;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws MyException {
        Integer addr = tbl.get(var);
        if (addr == null) {
            throw new MyException("No variable!");
        }
        Integer val = heap.readAddr(addr);
        if (val == null) {
            throw new MyException("No value!");
        }
        return val;
    }

    @Override
    public String toString() {
        return "ReadHeapExp{" +
                "var='" + var + '\'' +
                '}';
    }
}
