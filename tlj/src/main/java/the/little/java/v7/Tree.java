package the.little.java.v7;

import java.util.Map;

/**
 * Created by 170182 on 2016/3/21.
 */
public abstract class Tree {
    abstract boolean accept(bTreeVisitor v);

    abstract int accept(iTreeVisitor ask);

    abstract Tree accept(tTreeVisitor ask);

    abstract Map accept(mTreeVisitor ask);

    abstract Object accept(TreeVisitor ask);
}

class Bud extends Tree {
    @Override
    Object accept(TreeVisitor ask) {
        return ask.forBud();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bud{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    Tree accept(tTreeVisitor ask) {
        return ask.forBud();
    }

    @Override
    int accept(iTreeVisitor ask) {
        return ask.forBud();
    }

    @Override
    Map accept(mTreeVisitor ask) {
        return ask.forBud();
    }

    @Override
    boolean accept(bTreeVisitor v) {
        return v.forBud();
    }
}

class Flat extends Tree {
    Fruit f;
    Tree t;

    @Override
    Tree accept(tTreeVisitor ask) {
        return ask.forFlat(f, t);
    }

    @Override
    Object accept(TreeVisitor ask) {
        return ask.forFlat(f, t);
    }

    @Override
    Map accept(mTreeVisitor ask) {
        return ask.forFlat(f, t);
    }

    public Flat(Fruit f, Tree t) {
        this.f = f;
        this.t = t;
    }

    @Override
    int accept(iTreeVisitor ask) {
        return ask.forFlat(f, t);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flat{");
        sb.append("f=").append(f);
        sb.append(", t=").append(t);
        sb.append('}');
        return sb.toString();
    }

    @Override
    boolean accept(bTreeVisitor v) {
        return v.forFlat(f, t);
    }
}

class Split extends Tree {
    Tree l;
    Tree r;

    @Override
    Object accept(TreeVisitor ask) {
        return ask.forSplit(l, r);
    }

    @Override
    Map accept(mTreeVisitor ask) {
        return ask.forSplit(l, r);
    }

    @Override
    Tree accept(tTreeVisitor ask) {
        return ask.forSplit(l, r);
    }

    @Override
    int accept(iTreeVisitor ask) {
        return ask.forSplit(l, r);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Split{");
        sb.append("l=").append(l);
        sb.append(", r=").append(r);
        sb.append('}');
        return sb.toString();
    }

    @Override
    boolean accept(bTreeVisitor v) {
        return v.forSplit(l, r);
    }

    public Split(Tree l, Tree r) {
        this.l = l;
        this.r = r;
    }
}
