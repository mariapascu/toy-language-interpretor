package Repository;

import Model.PrgState;
import Exception.MyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Repository implements IRepository{
    private List<PrgState> prg;
    private String logFilePath;
    private PrintWriter printWriter;
    private boolean firstWrite;

    public Repository(List<PrgState> prg, String fileName) {
        this.prg = prg;
        this.logFilePath = fileName;
        this.firstWrite = true;
    }

    @Override
    public List<PrgState> getPrgList() {
        return this.prg;
    }

    @Override
    public void setPrgList(List<PrgState> prgList) {
        this.prg = prgList;
    }

    @Override
    public void logPrgStateExec(PrgState currentPrg) throws MyException {
        try {
            if (this.firstWrite) {
                PrintWriter firstPrintWriter = new PrintWriter(this.logFilePath);
                firstPrintWriter.println("");
                firstPrintWriter.close();
                this.firstWrite = false;
            }
            this.printWriter = new PrintWriter(new FileWriter(this.logFilePath, true));
            this.printWriter.println("ID: " + currentPrg.getId());
            this.printWriter.println("Exe Stack:");
            this.printWriter.println(currentPrg.getStk().toString());
            this.printWriter.println("SymTable:");
            this.printWriter.println(currentPrg.getSymTable().toString());
            this.printWriter.println("Out:");
            this.printWriter.println(currentPrg.getOut().toString());
            this.printWriter.println("Heap:");
            this.printWriter.println(currentPrg.getHeap().toString());
            this.printWriter.close();
        }
        catch (IOException e) {
            throw new MyException(e.getMessage());
        }
    }
}
