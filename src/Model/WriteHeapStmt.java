package Model;
import Exception.MyException;

public class WriteHeapStmt implements IStmt {
    private String var;
    private Exp exp;

    public WriteHeapStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        Integer addr = state.getSymTable().get(var);
        if (var == null) {
            throw new MyException("No variable!");
        }
        Integer val = this.exp.eval(state.getSymTable(), state.getHeap());
        state.getHeap().writeAddr(addr, val);
        return null;
    }

    @Override
    public String toString() {
        return "WriteHeapStmt{" +
                "var='" + var + '\'' +
                ", exp=" + exp +
                '}';
    }
}
