package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public interface tTreeVisitor {
    Tree forBud();

    Tree forFlat(Fruit f, Tree t);

    Tree forSplit(Tree l, Tree r);
}
