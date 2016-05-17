package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public class tSubst implements tTreeVisitor {
    Fruit f1;
    Fruit f2;

    public tSubst(Fruit f1, Fruit f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public Tree forBud() {
        return new Bud();
    }

    @Override
    public Tree forFlat(Fruit f, Tree t) {
        if (f.equals(f2)) {
            return new Flat(f1, t.accept(this));
        } else {
            return new Flat(f, t.accept(this));
        }
    }

    @Override
    public Tree forSplit(Tree l, Tree r) {
        return new Split(l.accept(this), r.accept(this));
    }
}
