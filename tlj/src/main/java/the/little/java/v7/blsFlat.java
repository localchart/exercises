package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public class blsFlat implements bTreeVisitor {
    @Override
    public boolean forBud() {
        return true;
    }

    @Override
    public boolean forFlat(Fruit f, Tree t) {
        return t.accept(this);
    }

    @Override
    public boolean forSplit(Tree l, Tree r) {
        return false;
    }
}
