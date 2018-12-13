package Model;
import Exception.MyException;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Integer> out = state.getOut();
        out.add(exp.eval(state.getSymTable(), state.getHeap()));
        return null;
    }

    @Override
    public String toString() {
        return "PrintStmt{" +
                "exp=" + exp.toString() +
                '}';
    }
}
