package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public interface bTreeVisitor {
    boolean forBud();

    boolean forFlat(Fruit f, Tree t);

    boolean forSplit(Tree l, Tree r);
}
