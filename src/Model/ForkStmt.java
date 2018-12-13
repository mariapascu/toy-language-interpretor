package Model;

import java.util.Stack;

public class ForkStmt implements IStmt {
    private IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> forkStack = new MyStack<>(new Stack<>());
        forkStack.push(stmt);
        MyIDictionary<String, Integer> forkDict = new MyDictionary<>(state.getSymTable().getAll());
        int forkID = state.getId() * 10;
        PrgState forkPrg = new PrgState(forkStack, forkDict, state.getOut(), state.getFileTable(), state.getHeap(), forkID);
        return forkPrg;
    }
}
