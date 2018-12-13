package Model;
import Exception.MyException;

import java.io.BufferedReader;

public class PrgState {
    private int id;
    private MyIStack<IStmt> exeStack;
    private MyIDictionary<String, Integer> symTable;
    private MyIList<Integer> out;
    private MyITable<Integer, String, BufferedReader> fileTable;
    private MyIHeap<Integer> heap;
    private IStmt originalProgram;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl, MyIList<Integer> ot, MyITable<Integer, String, BufferedReader> fileTable, MyIHeap<Integer> heap, int id) {
        this.exeStack = stk;
        this.symTable = symtbl;
        this.out = ot;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = id;
    }
    public MyIStack<IStmt> getStk() {
        return this.exeStack;
    }

    public MyIList<Integer> getOut() {
        return this.out;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public MyITable<Integer, String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public MyIHeap<Integer> getHeap() {
        return heap;
    }

    public int getId() {
        return this.id;
    }

    public Boolean isNotCompleted() {
        return !this.exeStack.empty();
    }

    public PrgState oneStep() throws MyException {
        if (!this.isNotCompleted()) {
            throw new MyException("Empty execution stack!");
        }
        IStmt crtStmt = this.exeStack.pop();
        return crtStmt.execute(this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.id);
        result.append( "\n");
        result.append(this.exeStack.toString());
        result.append(this.symTable.toString());
        result.append(this.out.toString());
        result.append(this.heap.toString());
        return result.toString();
    }
}
