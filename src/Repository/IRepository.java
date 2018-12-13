package Repository;

import Model.PrgState;
import Exception.MyException;

import java.util.List;

public interface IRepository {
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> prgList);
    void logPrgStateExec(PrgState currentPrg) throws MyException;
}
