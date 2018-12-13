package Model;
import Exception.MyException;
import com.sun.xml.internal.ws.api.model.MEP;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {
    private static int id;
    private String varName;
    private String filename;

    public OpenRFileStmt(String varName, String filename) {
        this.varName = varName;
        this.filename = filename;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        try {
            for (Pair<String, BufferedReader> value : state.getFileTable().getValues()) {
                if (value.getKey() == this.filename)
                    throw new MyException("File name already exists!");
            }
            File f = new File(this.filename);
            if (!f.exists()) {
                throw new MyException("File does not exist!");
            }
            MyIDictionary<String, Integer> symTbl = state.getSymTable();
            MyITable<Integer, String, BufferedReader> fileTable = state.getFileTable();
            BufferedReader reader = new BufferedReader(new FileReader(this.filename));
            fileTable.put(id, filename, reader);
            symTbl.put(this.varName, id);
            id++;
        }
        catch (FileNotFoundException e) {
            throw new MyException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "OpenRFileStmt{" +
                "varName='" + varName + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
