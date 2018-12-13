package Model;
import Exception.MyException;

public class NewStmt implements IStmt {
    private String var;
    private Exp exp;

    public NewStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        int res = this.exp.eval(state.getSymTable(), state.getHeap());
        int addr = state.getHeap().allocate(res);
        state.getSymTable().put(var, addr);
        return null;
    }

    @Override
    public String toString() {
        return "NewStmt{" +
                "var='" + var + '\'' +
                ", exp=" + exp +
                '}';
    }
}
