package Controller;
import Model.PrgState;
import Repository.IRepository;
import Exception.MyException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    private ExecutorService executor;
    private boolean flag;

    public Controller(IRepository repo, boolean flag) {
        this.repo = repo;
        this.flag = flag;
    }

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer, Integer> heap) {
        return heap.entrySet()
                .stream()
                .filter(e->symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    void oneStepForAllPrg(List<PrgState> prgList) {
        prgList.forEach(p -> System.out.println(p.toString()));
        prgList.forEach(p -> {try {this.repo.logPrgStateExec(p);} catch (MyException e) {System.out.println(e.getMessage());}});
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        List<PrgState> newPrgList = null;
        try {
            newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        if (newPrgList != null) {
            prgList.addAll(newPrgList);
        }
        this.repo.setPrgList(prgList);
    }

    public void allSteps() {
        this.executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        while (prgList.size() > 0) {
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgList());
            prgList.forEach(p -> p.getHeap()
                    .setContent(conservativeGarbageCollector(p.getSymTable().getValues(),
                            p.getHeap().getContent())));
        }
        executor.shutdownNow();
        this.repo.getPrgList().forEach(p -> p.getFileTable().getValues()
                .forEach(e -> {try{e.getValue().close();}
                catch(IOException ex) {System.out.println(ex.getMessage());}}));
        this.repo.getPrgList().forEach(p -> p.getFileTable().getTable().entrySet().clear());
        this.repo.getPrgList().forEach(p -> System.out.println(p.toString()));
        this.repo.setPrgList(prgList);


        /*
        try {
            PrgState crt = this.repo.getPrgList().get(0);
            while (!crt.getStk().empty()) {
                if (flag) {
                    System.out.println(crt.toString());
                }
                this.repo.logPrgStateExec(crt);
                crt.oneStep();
                this.repo.getPrgList().get(0).getHeap().setContent(
                        conservativeGarbageCollector(this.repo.getPrgList().get(0).getSymTable().getValues(),
                                this.repo.getPrgList().get(0).getHeap().getContent()));
            }
            if (flag) {
                System.out.println(crt.toString());
            }
            this.repo.logPrgStateExec(crt);
        }
        catch (MyException e) {
            System.out.println(e.getMessage());
        }
        finally {
            this.repo.getPrgList().get(0).getFileTable().getValues()
                    .forEach(e -> {try{e.getValue().close();}
                    catch(IOException ex) {System.out.println(ex.getMessage());}});
            this.repo.getPrgList().get(0).getFileTable().getTable().entrySet().clear();
        }*/
    }
}
