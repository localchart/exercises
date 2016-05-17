package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public class iOccurs implements iTreeVisitor {
    Fruit n;

    public iOccurs(Fruit n) {
        this.n = n;
    }

    @Override
    public int forBud() {
        return 0;
    }

    @Override
    public int forFlat(Fruit f, Tree t) {
        if (f.equals(n)) {
            return 1 + t.accept(this);
        } else {
            return t.accept(this);
        }
    }

    @Override
    public int forSplit(Tree l, Tree r) {
        return l.accept(this) + r.accept(this);
    }
}
