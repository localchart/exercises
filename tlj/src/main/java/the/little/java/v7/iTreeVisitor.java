package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public interface iTreeVisitor {
    int forBud();

    int forFlat(Fruit f, Tree t);

    int forSplit(Tree l, Tree r);
}
