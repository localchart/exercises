package the.little.java.v2;

/**
 * Created by 170182 on 2016/3/17.
 */
public class IsVegetarian {
    boolean forSkewer() {
        return true;
    }

    boolean forOnion(ShishD s) {
        return s.isVegetarian();
    }

    boolean forLamb(ShishD s) {
        return false;
    }

    boolean forTomato(ShishD s) {
        return false;
    }
}
