package Model;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt first, IStmt snd) {
        this.first = first;
        this.snd = snd;
    }

    public String toString() {
        return "(" + this.snd.toString() + " " + this.first.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(this.snd);
        stk.push(this.first);
        return null;
    }

}
