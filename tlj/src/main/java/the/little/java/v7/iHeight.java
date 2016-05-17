package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public class iHeight implements iTreeVisitor {
    @Override
    public int forBud() {
        return 0;
    }

    @Override
    public int forFlat(Fruit f, Tree t) {
        return 1 + t.accept(this);
    }

    @Override
    public int forSplit(Tree l, Tree r) {
        return Math.max(l.accept(this),r.accept(this)) + 1;
    }
}
