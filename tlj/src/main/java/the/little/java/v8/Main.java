package the.little.java.v8;

/**
 * Created by 170182 on 2016/3/22.
 */
public class Main {
    public static void main(String[] args) {
        Object o = new Plus(new Const(new Integer(7)),
                new Prod(new Diff(new Const(new Integer(4)), new Const(new Integer(3))),
                        new Const(new Integer(5)))).accept(new IntEval());
        System.out.println(o.toString());
        System.out.println("--------------");
        o = new Prod(new Const(new Empty().add(new Integer(7))),
                new Const(new Empty().add(new Integer(3)))).accept(new SetEval());
        System.out.println(o.toString());
        System.out.println("--------------");
        o = new Plus(new Const(new Empty().add(new Integer(7)).add(new Integer(5))),
                new Prod(new Diff(new Const(new Empty().add(new Integer(4))),
                        new Const(new Empty().add(new Integer(3)))),
                        new Const(new Empty().add(new Integer(5))))).accept(new SetEval());
        System.out.println(o.toString());
    }
}
