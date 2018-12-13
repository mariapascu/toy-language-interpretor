package Model;
import Exception.MyException;

public class BoolExp extends Exp {
    private String type;
    private Exp e1;
    private Exp e2;

    public BoolExp(String type, Exp e1, Exp e2) {
        this.type = type;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIHeap<Integer> heap) throws MyException{
        int left = e1.eval(tbl, heap);
        int right = e2.eval(tbl, heap);
        switch (this.type) {
            case "<":
                return (left < right) ? 1 : 0;
            case "<=":
                return (left <= right) ? 1 : 0;
            case "==":
                return (left == right) ? 1 : 0;
            case "!=":
                return (left != right) ? 1 : 0;
            case ">":
                return (left > right) ? 1 : 0;
            case ">=":
                return (left >= right) ? 1 : 0;
            default:
                throw new MyException("Invalid operator!");
        }

    }

    @Override
    public String toString() {
        return "BoolExp{" +
                "type='" + type + '\'' +
                ", e1=" + e1 +
                ", e2=" + e2 +
                '}';
    }
}
