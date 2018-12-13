package Model;
import Exception.MyException;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIHeap<Integer> heap = state.getHeap();
        int val = exp.eval(symTbl, heap);
        if (val == 0) {
            return thenS.execute(state);
        }
        else {
            return elseS.execute(state);
        }
    }

    @Override
    public String toString() {
        return "IfStmt{" +
                "exp=" + exp.toString() +
                ", thenS=" + thenS.toString() +
                ", elseS=" + elseS.toString() +
                '}';
    }
}
