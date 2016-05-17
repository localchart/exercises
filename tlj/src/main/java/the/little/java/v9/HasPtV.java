package the.little.java.v9;

/**
 * Created by 170182 on 2016/5/11.
 */
public class HasPtV implements ShapeVisitor {
    private final Point p;

    public HasPtV(Point p) {
        this.p = p;
    }

    public ShapeVisitor newHasPtV(Point p) {
        return new HasPtV(p);
    }

    @Override
    public boolean forSquare(int s) {
        return p.x <= s && p.y <= s;
    }

    @Override
    public boolean forCircle(int r) {
        return p.distanceTo0() <= r;
    }

    @Override
    public boolean forTrans(Point q, Shape s) {
        return s.accept(newHasPtV(p.minus(q)));
    }
}
