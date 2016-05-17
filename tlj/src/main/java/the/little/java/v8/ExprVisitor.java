package the.little.java.v8;

/**
 * Created by 170182 on 2016/3/22.
 */
public interface ExprVisitor {
    Object forPlus(Expr l, Expr r);

    Object forDiff(Expr l, Expr r);

    Object forProd(Expr l, Expr r);

    Object forConst(Object o);
}
