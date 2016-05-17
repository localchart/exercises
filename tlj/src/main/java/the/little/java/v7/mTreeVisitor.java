package the.little.java.v7;

import java.util.Map;

/**
 * Created by 170182 on 2016/3/21.
 */
public interface mTreeVisitor {
    Map forBud();

    Map forFlat(Fruit f, Tree t);

    Map forSplit(Tree l, Tree r);
}
