package the.little.java.v1;

/**
 * Created by 170182 on 2016/3/17.
 */
public abstract class Pizza {


    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Pizza p1 = new Anchovy(new Olive(new Anchovy(new Anchovy(new Cheese(new Crust()))))).remA();
        System.out.println(p1); // Olive->Cheese->Crust

        Pizza p2 = new Sausage(new Olive(new Anchovy(new Sausage(new Cheese(new Crust()))))).remA();
        System.out.println(p2); // Sausage->Olive->Sausage->Cheese->Crust

        Pizza p3 = new Olive(new Anchovy(new Cheese(new Anchovy(new Crust())))).topAwC();
        System.out.println(p3); // Olive->Cheese->Anchovy->Cheese->Cheese->Anchovy->Crust

        Pizza p4 = (new Olive(new Anchovy(new Cheese(new Anchovy(new Crust()))))
                .remA())
                .topAwC();
        System.out.println(p4); //Olive->Cheese->Crust

        Pizza p5 = (new Olive(new Anchovy(new Cheese(new Anchovy(new Crust()))))
                .topAwC())
                .remA();
        System.out.println(p5); //Olive->Cheese->Cheese->Cheese->Crust

        Pizza p6 = (new Olive(new Anchovy(new Cheese(new Anchovy(new Crust()))))
                .subAbC());

        System.out.println(p6); //Olive->Cheese->Cheese->Cheese->Crust
    }

    abstract Pizza remA();
    abstract Pizza topAwC();
    abstract Pizza subAbC();
}

class Crust extends Pizza {
    @Override
    Pizza remA() {
        return new Crust();
    }

    @Override
    Pizza subAbC() {
        return new Crust();
    }

    @Override
    Pizza topAwC() {
        return new Crust();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

class Cheese extends Pizza {
    Pizza p;

    @Override
    Pizza subAbC() {
        return new Cheese(p.subAbC());
    }

    public Cheese(Pizza p) {
        this.p = p;
    }

    @Override
    Pizza topAwC() {
        return new Cheese(p.topAwC());
    }

    @Override
    Pizza remA() {
        return new Cheese(p.remA());
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "->" + p.toString();
    }
}

class Olive extends Pizza {
    Pizza p;

    @Override
    Pizza subAbC() {
        return new Olive(p.subAbC());
    }

    @Override
    Pizza topAwC() {
        return new Olive(p.topAwC());
    }

    @Override
    Pizza remA() {
        return new Olive(p.remA());
    }

    public Olive(Pizza p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "->" + p.toString();
    }
}

class Anchovy extends Pizza {
    Pizza p;

    @Override
    Pizza subAbC() {
        return new Cheese(p.subAbC());
    }

    @Override
    Pizza topAwC() {
        return new Cheese(new Anchovy(p.topAwC()));
    }

    @Override
    Pizza remA() {
        return p.remA();
    }

    public Anchovy(Pizza p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "->" + p.toString();
    }
}

class Sausage extends Pizza {
    Pizza p;

    @Override
    Pizza subAbC() {
        return new Sausage(p.subAbC());
    }

    @Override
    Pizza remA() {
        return new Sausage(p.remA());
    }

    @Override
    Pizza topAwC() {
        return new Sausage(p.topAwC());
    }

    public Sausage(Pizza p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "->" + p.toString();
    }
}