package the.little.java.v8;

/**
 * Created by 170182 on 2016/3/22.
 */
public interface Expr {
    Object accept(ExprVisitor ask);
}

class Plus implements Expr {
    Expr l;
    Expr r;

    public Plus(Expr l, Expr r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public Object accept(ExprVisitor ask) {
        return ask.forPlus(l, r);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Plus{");
        sb.append("l=").append(l);
        sb.append(", r=").append(r);
        sb.append('}');
        return sb.toString();
    }
}

class Diff implements Expr {
    Expr l;
    Expr r;

    @Override
    public Object accept(ExprVisitor ask) {
        return ask.forDiff(l, r);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Diff{");
        sb.append("l=").append(l);
        sb.append(", r=").append(r);
        sb.append('}');
        return sb.toString();
    }

    public Diff(Expr l, Expr r) {
        this.l = l;
        this.r = r;
    }
}

class Prod implements Expr {
    Expr l;
    Expr r;

    @Override
    public Object accept(ExprVisitor ask) {
        return ask.forProd(l, r);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Prod{");
        sb.append("l=").append(l);
        sb.append(", r=").append(r);
        sb.append('}');
        return sb.toString();
    }

    public Prod(Expr l, Expr r) {
        this.l = l;
        this.r = r;
    }
}

class Const implements Expr {
    Object o;

    @Override
    public Object accept(ExprVisitor ask) {
        return ask.forConst(o);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Const{");
        sb.append("o=").append(o);
        sb.append('}');
        return sb.toString();
    }

    public Const(Object o) {
        this.o = o;
    }
}