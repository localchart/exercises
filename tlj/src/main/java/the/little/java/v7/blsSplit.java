package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public class blsSplit implements bTreeVisitor {
    @Override
    public boolean forBud() {
        return true;
    }

    @Override
    public boolean forFlat(Fruit f, Tree t) {
        return false;
    }

    @Override
    public boolean forSplit(Tree l, Tree r) {
        return l.accept(this) && r.accept(this);
    }
}
