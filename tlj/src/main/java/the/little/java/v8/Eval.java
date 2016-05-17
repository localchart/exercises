package the.little.java.v8;

/**
 * Created by 170182 on 2016/3/22.
 */
public abstract class Eval implements ExprVisitor {
    @Override
    public Object forPlus(Expr l, Expr r) {
        return plus(l.accept(this), r.accept(this));
    }

    @Override
    public Object forDiff(Expr l, Expr r) {
        return diff(l.accept(this), r.accept(this));
    }

    @Override
    public Object forProd(Expr l, Expr r) {
        return prod(l.accept(this), r.accept(this));
    }

    @Override
    public Object forConst(Object o) {
        return o;
    }

    abstract Object plus(Object l, Object r);

    abstract Object diff(Object l, Object r);

    abstract Object prod(Object l, Object r);
}
