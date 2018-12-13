package Model;
import Exception.MyException;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    private Exp exp;
    private String var;

    public ReadFileStmt(Exp exp, String var) {
        this.exp = exp;
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        int key = exp.eval(state.getSymTable(), state.getHeap());
        Pair<String, BufferedReader> file = state.getFileTable().getValue(key);
        if (file == null) {
            throw new MyException("File does not exist!");
        }
        String line;
        try {
            line = file.getValue().readLine();
        }
        catch (IOException e) {
            throw new MyException(e.getMessage());
        }
        int val = 0;
        if (line != null) {
            val = Integer.valueOf(line);
        }
        state.getSymTable().put(this.var, val);
        return null;
    }

    @Override
    public String toString() {
        return "ReadFileStmt{" +
                "exp=" + exp +
                ", var='" + var + '\'' +
                '}';
    }
}
