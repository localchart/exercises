package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public class Occurs implements TreeVisitor {
    Fruit n;

    public Occurs(Fruit n) {
        this.n = n;
    }

    @Override
    public Object forBud() {
        return new Integer(0);
    }

    @Override
    public Object forFlat(Fruit f, Tree t) {
        if (f.equals(n)) {
            return new Integer(1 + ((Integer) t.accept(this)).intValue());
        } else {
            return t.accept(this);
        }
    }

    @Override
    public Object forSplit(Tree l, Tree r) {
        return new Integer(((Integer) l.accept(this)).intValue() + ((Integer) r.accept(this)).intValue());
    }
}
