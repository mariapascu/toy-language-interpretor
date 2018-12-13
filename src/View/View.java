package View;
import Model.*;
import Repository.*;
import Controller.*;

import java.io.BufferedReader;
import java.util.*;

public class View {
    public static void main(String args[]) {
        System.out.println("Give output file: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();

        /*IStmt ex3 = new CompStmt( new ForkStmt(new CompStmt(new AssignStmt("a",new ConstExp(400)), new PrintStmt(new VarExp("a")))),new CompStmt(new AssignStmt("a", new ConstExp(1)),
                new WhileStmt(new BoolExp("<", new VarExp("a"), new ConstExp(5)),
                        new CompStmt(new PrintStmt(new VarExp("a")),
                                new AssignStmt("a", new ArithExp('+', new VarExp("a"), new ConstExp(1)))))));*/
        //IStmt ex3 = new CompStmt(new OpenRFileStmt("a", "input.txt"), new PrintStmt(new VarExp("a")));
        IStmt ex3 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                    new CompStmt(new NewStmt("a", new ConstExp(22)),
                    new CompStmt(new ForkStmt(
                            new CompStmt(new WriteHeapStmt("a", new ConstExp(30)),
                                    new CompStmt(new AssignStmt("v", new ConstExp(32)),
                                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))))),
                    new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a"))))));
        MyIStack<IStmt> stk3 = new MyStack<IStmt>(new Stack<IStmt>());
        stk3.push(ex3);
        MyIDictionary<String, Integer> symTbl3 = new MyDictionary<String, Integer>(new HashMap<String, Integer>());
        MyIList<Integer> out3 = new MyList<Integer>(new ArrayList<Integer>());
        MyITable<Integer, String, BufferedReader> fileTable3 = new MyTable<>();
        MyIHeap<Integer> heap3 = new MyHeap<>(new HashMap<>());
        PrgState state3 = new PrgState(stk3, symTbl3, out3, fileTable3, heap3, 1);
        List<PrgState> prgList = new ArrayList<PrgState>();
        prgList.add(state3);
        IRepository repo3 = new Repository(prgList, fileName);
        Controller ctrl3 = new Controller(repo3, true);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        //menu.addCommand(new RunExample("1", "A2",ctrl1));
        //menu.addCommand(new RunExample("2","A3 a",ctrl2));
        menu.addCommand(new RunExample("3","A3 b",ctrl3));
        menu.show();



    }
}
