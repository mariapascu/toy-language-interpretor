package Model;
import Exception.MyException;

public class VarExp extends Exp {
    String id;

    public VarExp(String id) {
        this.id = id;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws MyException {
        return tbl.get(id);
    }

    @Override
    public String toString() {
        return "VarExp{" +
                "id='" + id + '\'' +
                '}';
    }
}
