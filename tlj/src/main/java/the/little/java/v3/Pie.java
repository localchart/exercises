package the.little.java.v3;

/**
 * Created by 170182 on 2016/3/19.
 */
public abstract class Pie {
    RemA raFn = new RemA();
    RemFish rfFn = new RemFish();
    RemInt riFn = new RemInt();
    SubstFish sfFn = new SubstFish();
    SubstInt siFn = new SubstInt();

    Rem remFn = new Rem();
    Subst sFn = new Subst();

    abstract Pie rem(Object o);

    abstract Pie subst(Object n, Object o);

    abstract Pie remA();

    abstract Pie remFish(Fish f);

    abstract Pie remInt(Integer i);

    abstract Pie substFish(Fish f1, Fish f2);

    abstract Pie substInt(Integer i1, Integer i2);


    public static void main(String[] args) {
        Pie p = new Top(new Salmon(), new Top(new Anchovy(), new Top(new Tuna(), new Top(new Anchovy(), new Bot()))));
        System.out.println(p); //Top{t=Salmon{}, p=Top{t=Anchovy{}, p=Top{t=Tuna{}, p=Top{t=Anchovy{}, p=Bot{}}}}}
        System.out.println(p.remA()); //Top{t=Salmon{}, p=Top{t=Tuna{}, p=Bot{}}}
        System.out.println("--------------");
        p = new Top(new Anchovy(), new Bot());
        System.out.println(p);//Top{t=Anchovy{}, p=Bot{}}
        System.out.println(p.remFish(new Anchovy()));//Bot{}
        System.out.println(p.rem(new Anchovy()));//Bot{}
        System.out.println("--------------");
        p = new Top(new Integer(2), new Top(new Integer(3), new Top(new Integer(2), new Bot())));
        System.out.println(p);//Top{t=2, p=Top{t=3, p=Top{t=2, p=Bot{}}}}
        System.out.println(p.remInt(new Integer(2)));//Top{t=3, p=Bot{}}
        System.out.println(p.rem(new Integer(2)));//Top{t=3, p=Bot{}}
        System.out.println("--------------");
        p = new Top(new Anchovy(), new Top(new Integer(3), new Top(new Zero(), new Bot())));
        System.out.println(p);//Top{t=Anchovy{}, p=Top{t=3, p=Top{t=Zero{}, p=Bot{}}}}
        System.out.println(p.rem(new Integer(3)));//Top{t=Anchovy{}, p=Top{t=2, p=Bot{}}}
        System.out.println(p.rem(new Zero()));//Top{t=Anchovy{}, p=Top{t=3, p=Top{t=Zero{}, p=Bot{}}}}
        System.out.println("--------------");
        p = new Top(new Anchovy(), new Top(new Tuna(), new Top(new Anchovy(), new Bot())));
        System.out.println(p);//Top{t=Anchovy{}, p=Top{t=Tuna{}, p=Top{t=Anchovy{}, p=Bot{}}}}
        System.out.println(p.substFish(new Salmon(), new Anchovy()));//Top{t=Salmon{}, p=Top{t=Tuna{}, p=Top{t=Salmon{}, p=Bot{}}}}
        System.out.println(p.subst(new Salmon(), new Anchovy()));//Top{t=Salmon{}, p=Top{t=Tuna{}, p=Top{t=Salmon{}, p=Bot{}}}}
        System.out.println("--------------");
        p = new Top(new Integer(3), new Top(new Integer(2), new Top(new Integer(3), new Bot())));
        System.out.println(p);//Top{t=3, p=Top{t=2, p=Top{t=3, p=Bot{}}}}
        System.out.println(p.substInt(new Integer(5), new Integer(3)));//Top{t=5, p=Top{t=2, p=Top{t=5, p=Bot{}}}}
        System.out.println(p.subst(new Integer(5), new Integer(3)));//Top{t=5, p=Top{t=2, p=Top{t=5, p=Bot{}}}}
    }
}

class Subst {
    Pie forBot(Object n, Object o) {
        return new Bot();
    }

    Pie forTop(Object t, Pie r, Object n, Object o) {
        if (o.equals(t)) {
            return new Top(n, r.subst(n, o));
        } else {
            return new Top(t, r.subst(n, o));
        }
    }
}

class SubstFish {
    Pie forBot(Fish f1, Fish f2) {
        return new Bot();
    }

    Pie forTop(Object t, Pie r, Fish n, Fish o) {
        if (o.equals(t)) {
            return new Top(n, r.substFish(n, o));
        } else {
            return new Top(t, r.substFish(n, o));
        }
    }
}

class SubstInt {
    Pie forBot(Integer i1, Integer i2) {
        return new Bot();
    }

    Pie forTop(Object t, Pie r, Integer n, Integer o) {
        if (o.equals(t)) {
            return new Top(n, r.substInt(n, o));
        } else {
            return new Top(t, r.substInt(n, o));
        }
    }
}

class RemA {
    Pie forBot() {
        return new Bot();
    }

    Pie forTop(Object t, Pie p) {
        if (t.equals(new Anchovy())) {
            return p.remA();
        } else {
            return new Top(t, p.remA());
        }
    }
}

class RemFish {
    Pie forBot(Fish f) {
        return new Bot();
    }

    Pie forTop(Object t, Pie p, Fish f) {
        if (f.equals(t)) {
            return p.remFish(f);
        } else {
            return new Top(t, p.remFish(f));
        }
    }
}

class RemInt {
    Pie forBot(Integer f) {
        return new Bot();
    }

    Pie forTop(Object t, Pie p, Integer i) {
        if (i.equals(t)) {
            return p.remInt(i);
        } else {
            return new Top(t, p.remInt(i));
        }
    }
}

class Rem {
    Pie forBot(Object o) {
        return new Bot();
    }

    Pie forTop(Object t, Pie p, Object o) {
        if (o.equals(t)) {
            return p.rem(o);
        } else {
            return new Top(t, p.rem(o));
        }
    }
}

class Bot extends Pie {
    @Override
    Pie subst(Object n, Object o) {
        return sFn.forBot(n, o);
    }

    @Override
    Pie remA() {
        return raFn.forBot();
    }

    @Override
    Pie remInt(Integer i) {
        return riFn.forBot(i);
    }

    @Override
    Pie remFish(Fish f) {
        return rfFn.forBot(f);
    }

    @Override
    Pie rem(Object o) {
        return remFn.forBot(o);
    }

    @Override
    Pie substFish(Fish f1, Fish f2) {
        return sfFn.forBot(f1, f2);
    }

    @Override
    Pie substInt(Integer i1, Integer i2) {
        return siFn.forBot(i1, i2);
    }

    @Override
    public String toString() {
        return "Bot{}";
    }
}

class Top extends Pie {
    Object t;
    Pie p;

    @Override
    Pie rem(Object o) {
        return remFn.forTop(t, p, o);
    }

    @Override
    Pie subst(Object n, Object o) {
        return sFn.forTop(t, p, n, o);
    }

    @Override
    Pie substFish(Fish f1, Fish f2) {
        return sfFn.forTop(t, p, f1, f2);
    }

    @Override
    Pie substInt(Integer i1, Integer i2) {
        return siFn.forTop(t, p, i1, i2);
    }

    @Override
    Pie remInt(Integer i) {
        return riFn.forTop(t, p, i);
    }

    @Override
    Pie remFish(Fish f) {
        return rfFn.forTop(t, p, f);
    }

    @Override
    Pie remA() {
        return raFn.forTop(t, p);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Top{");
        sb.append("t=").append(t);
        sb.append(", p=").append(p);
        sb.append('}');
        return sb.toString();
    }

    public Top(Object t, Pie p) {
        this.t = t;
        this.p = p;
    }
}


