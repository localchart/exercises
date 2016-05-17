package the.little.java.v9;

/**
 * Created by 170182 on 2016/5/11.
 */
public class UnionHasPtV extends HasPtV implements UnionVisitor {
    public UnionHasPtV(Point p) {
        super(p);
    }

    public ShapeVisitor newHasPtV(Point p){
        return new UnionHasPtV(p);
    }

    @Override
    public boolean forUnion(Shape s, Shape t) {
        return s.accept(this) || t.accept(this);
    }
}
