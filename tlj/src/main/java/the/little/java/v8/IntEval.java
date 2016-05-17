package the.little.java.v8;

/**
 * Created by 170182 on 2016/3/23.
 */
public class IntEval extends Eval {
    public Object plus(Object l, Object r) {
        return new Integer(((Integer) l).intValue() + ((Integer) r).intValue());
    }

    public Object diff(Object l, Object r) {
        return new Integer(((Integer) l).intValue() - ((Integer) r).intValue());
    }

    public Object prod(Object l, Object r) {
        return new Integer(((Integer) l).intValue() * ((Integer) r).intValue());
    }
}
