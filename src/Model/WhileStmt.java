package Model;
import Exception.MyException;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> exeStack = state.getStk();
        int res = exp.eval(state.getSymTable(), state.getHeap());
        if (res != 0) {
            exeStack.push(this);
            exeStack.push(stmt);
        }
        return null;
    }

    @Override
    public String toString() {
        return "WhileStmt{" +
                "exp=" + exp +
                ", stmt=" + stmt +
                '}';
    }
}
