package Model;
import Exception.MyException;

public class ArithExp extends Exp {
    private Exp exp1;
    private Exp exp2;
    private int op;      // 1 - +; 2 - -; 3 - *; 4 - /;

    public ArithExp(char op, Exp exp1, Exp exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        if (op == '+') {
            this.op = 1;
        }
        else if (op == '-') {
            this.op = 2;
        }
        else if (op == '*') {
            this.op = 3;
        }
        else if (op == '/') {
            this.op = 4;
        }
    }

    public int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws MyException {
        if (op == 1) {
            return exp1.eval(tbl, heap) + exp2.eval(tbl, heap);
        }
        else if (op == 2) {
            return exp1.eval(tbl, heap) - exp2.eval(tbl, heap);
        }
        else if (op == 3) {
            return exp1.eval(tbl, heap) * exp2.eval(tbl, heap);
        }
        else if (op == 4) {
            if (exp2.eval(tbl, heap) == 0) {
                throw new MyException("Division by zero!");
            }
            return exp1.eval(tbl, heap) / exp2.eval(tbl, heap);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "ArithExp{" +
                "exp1=" + exp1.toString() +
                ", exp2=" + exp2.toString() +
                ", op=" + op +
                '}';
    }
}
