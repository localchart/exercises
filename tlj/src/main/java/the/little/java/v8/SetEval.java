package the.little.java.v8;

/**
 * Created by 170182 on 2016/3/23.
 */
public class SetEval extends Eval {
    @Override
    Object plus(Object l, Object r) {
        return ((Set) l).plus((Set) r);
    }

    @Override
    Object diff(Object l, Object r) {
        return ((Set) l).diff((Set) r);
    }

    @Override
    Object prod(Object l, Object r) {
        return ((Set) l).prod((Set) r);
    }
}
