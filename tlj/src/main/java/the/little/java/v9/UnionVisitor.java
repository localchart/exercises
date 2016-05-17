package the.little.java.v9;

/**
 * Created by 170182 on 2016/5/11.
 */
public interface UnionVisitor extends ShapeVisitor {
    boolean forUnion(Shape s, Shape t);
}
