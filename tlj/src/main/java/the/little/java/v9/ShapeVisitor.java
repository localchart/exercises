package the.little.java.v9;

/**
 * Created by 170182 on 2016/3/23.
 */
public interface ShapeVisitor {
    boolean forSquare(int s);

    boolean forCircle(int r);

    boolean forTrans(Point q, Shape s);
}
