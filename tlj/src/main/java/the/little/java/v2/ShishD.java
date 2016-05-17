package the.little.java.v2;

/**
 * Created by 170182 on 2016/3/15.
 */
public abstract class ShishD {
    OnlyOnions ooFn = new OnlyOnions();
    IsVegetarian ivFn = new IsVegetarian();

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
        return ooFn.forSkewer();
    }

    @Override
    boolean isVegetarian() {
        return ivFn.forSkewer();
    }
}

class Onion extends ShishD {
    ShishD s;

    @Override
    boolean onlyOnions() {
        return ooFn.forOnion(s);
    }

    @Override
    boolean isVegetarian() {
        return ivFn.forOnion(s);
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
        return ooFn.forLamb(s);
    }

    @Override
    boolean isVegetarian() {
        return ivFn.forLamb(s);
    }
}

class Tomato extends ShishD {
    ShishD s;

    @Override
    boolean onlyOnions() {
        return ooFn.forTomato(s);
    }

    public Tomato(ShishD s) {
        this.s = s;
    }

    @Override
    boolean isVegetarian() {
        return s.isVegetarian();
    }
}
