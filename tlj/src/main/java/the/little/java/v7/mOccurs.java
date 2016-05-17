package the.little.java.v7;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 170182 on 2016/3/21.
 */
public class mOccurs implements mTreeVisitor {
    Map<String, Integer> map;

    public mOccurs(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public Map<String, Integer> forBud() {
        return map;
    }

    @Override
    public Map<String, Integer> forFlat(Fruit f, Tree t) {
        Map<String, Integer> nap = new HashMap<>(map);
        String name = f.getClass().getSimpleName();
        int i = nap.getOrDefault(name, 0);
        nap.put(name, i + 1);
        return t.accept(new mOccurs(nap));
    }

    @Override
    public Map<String, Integer> forSplit(Tree l, Tree r) {
        return r.accept(new mOccurs(l.accept(this)));
    }
}
