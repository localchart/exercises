package the.little.java.v1;

/**
 * Created by 170182 on 2016/3/15.
 */
public abstract class ShishD {
    abstract boolean onlyOnions();

    abstract boolean isVegetarian();

    public static void main(String[] args) {
        test1();
        System.out.println("----------------------");
        test2();
        System.out.println("----------------------");
    }

    private static void test2() {
        boolean a = new Skewer().isVegetarian();
        boolean b = new Onion(new Skewer()).isVegetarian();
        boolean c = new Onion(new Onion(new Onion(new Onion(new Skewer())))).isVegetarian();
        boolean d = new Onion(new Onion(new Lamb(new Tomato(new Skewer())))).isVegetarian();
        boolean e = new Onion(new Onion(new Tomato(new Lamb(new Skewer())))).isVegetarian();

        System.out.println(a); // true
        System.out.println(b); // true
        System.out.println(c); // true
        System.out.println(d); // false
        System.out.println(e); // false
    }

    public static void test1() {
        boolean a = new Skewer().onlyOnions();
        boolean b = new Onion(new Skewer()).onlyOnions();
        boolean c = new Onion(new Onion(new Onion(new Onion(new Skewer())))).onlyOnions();
        boolean d = new Onion(new Onion(new Lamb(new Tomato(new Skewer())))).onlyOnions();

        System.out.println(a); // true
        System.out.println(b); // true
        System.out.println(c); // true
        System.out.println(d); // false
    }
}

class Skewer extends ShishD {
    @Override
    boolean onlyOnions() {
        return true;
    }

    @Override
    boolean isVegetarian() {
        return true;
    }
}

class Onion extends ShishD {
    ShishD s;

    @Override
    boolean onlyOnions() {
        return s.onlyOnions();
    }

    @Override
    boolean isVegetarian() {
        return s.isVegetarian();
    }

    public Onion(ShishD s) {
        this.s = s;
    }
}

class Lamb extends ShishD {
    ShishD s;

    public Lamb(ShishD s) {
        this.s = s;
    }

    @Override
    boolean onlyOnions() {
        return false;
    }

    @Override
    boolean isVegetarian() {
        return false;
    }
}

class Tomato extends ShishD {
    ShishD s;

    @Override
    boolean onlyOnions() {
        return false;
    }

    public Tomato(ShishD s) {
        this.s = s;
    }

    @Override
    boolean isVegetarian() {
        return s.isVegetarian();
    }
}
