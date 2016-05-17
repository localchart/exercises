package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public interface TreeVisitor {
    Object forBud();

    Object forFlat(Fruit f, Tree t);

    Object forSplit(Tree l, Tree r);
}
