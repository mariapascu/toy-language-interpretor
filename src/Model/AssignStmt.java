package Model;
import Exception.MyException;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIHeap<Integer> heap = state.getHeap();
        int val = exp.eval(symTbl, heap);
        symTbl.put(id, val);
        return null;
    }

    @Override
    public String toString() {
        return "AssignStmt{" +
                "id='" + id + '\'' +
                ", exp=" + exp +
                '}';
    }
}
