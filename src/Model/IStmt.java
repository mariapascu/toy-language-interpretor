package Model;
import Exception.MyException;

public interface IStmt {
    String toString();
    PrgState execute(PrgState state) throws MyException;
}

