package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public class IsSplit implements TreeVisitor {
    @Override
    public Object forBud() {
        return new Boolean(true);
    }

    @Override
    public Object forFlat(Fruit f, Tree t) {
        return new Boolean(false);
    }

    @Override
    public Object forSplit(Tree l, Tree r) {
        return ((Boolean) l.accept(this)).booleanValue() && ((Boolean) r.accept(this)).booleanValue();
    }
}
