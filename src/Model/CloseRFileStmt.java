package Model;

import Exception.MyException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {
    private Exp exp;

    public CloseRFileStmt(Exp exp) {
        this.exp = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        int key = exp.eval(state.getSymTable(), state.getHeap());
        Pair<String, BufferedReader> file = state.getFileTable().getValue(key);
        if (file == null) {
            throw new MyException("File does not exist!");
        }
        try {
            file.getValue().close();
        }
        catch(IOException e) {
            throw new MyException(e.getMessage());
        }
        state.getFileTable().delete(key);
        return null;
    }

    @Override
    public String toString() {
        return "CloseRFileStmt{" +
                "exp=" + exp +
                '}';
    }
}
