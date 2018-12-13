package Model;
import Exception.MyException;

abstract class Exp {
    abstract int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws MyException;
}
