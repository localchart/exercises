package the.little.java.v4;

/**
 * Created by 170182 on 2016/3/19.
 */
public interface Pie {
    Pie accept(PieVisitor visitor);

    public static void main(String[] args) {
        Pie p = new Top(new Integer(3), new Top(new Integer(2), new Top(new Integer(3), new Bot())));
        System.out.println(p);//Top{t=3, p=Top{t=2, p=Top{t=3, p=Bot{}}}}
        System.out.println(p.accept(new Rem(new Integer(2))));//Top{t=3, p=Top{t=3, p=Bot{}}}
        System.out.println(p.accept(new Subst(new Integer(5), new Integer(3))));//Top{t=5, p=Top{t=2, p=Top{t=5, p=Bot{}}}}
        System.out.println("--------------");
        p = new Top(new Anchovy(), new Top(new Integer(3), new Top(new Zero(), new Bot())));
        System.out.println(p);//Top{t=Anchovy{}, p=Top{t=3, p=Top{t=Zero{}, p=Bot{}}}}
        System.out.println(p.accept(new Rem(new Zero())));//Top{t=Anchovy{}, p=Top{t=3, p=Bot{}}}
        System.out.println("--------------");
        p = new Top(new Anchovy(), new Top(new Tuna(), new Top(new Anchovy(), new Top(new Tuna(), new Top(new Anchovy(), new Bot())))));
        System.out.println(p);//Top{t=Anchovy{}, p=Top{t=Tuna{}, p=Top{t=Anchovy{}, p=Top{t=Tuna{}, p=Top{t=Anchovy{}, p=Bot{}}}}}}
        System.out.println(p.accept(new LtdSubst(new Integer(2), new Salmon(), new Anchovy())));//Top{t=Salmon{}, p=Top{t=Tuna{}, p=Top{t=Salmon{}, p=Top{t=Tuna{}, p=Top{t=Anchovy{}, p=Bot{}}}}}}
        System.out.println("--------------");
    }
}

interface PieVisitor {
    Pie forBot();

    Pie forTop(Pie pie);
}

// closure
class LtdSubst implements PieVisitor {
    Integer i;
    Object n;
    Object o;

    public LtdSubst(Integer i, Object n, Object o) {
        this.i = i;
        this.n = n;
        this.o = o;
    }

    @Override
    public Pie forBot() {
        return new Bot();
    }

    @Override
    public Pie forTop(Pie pie) {
        Pie p = ((Top) pie).p;
        Object t = ((Top) pie).t;
        if (i == 0) {
            return new Top(t, p);
        } else if (o.equals(t)) {
            // nice job
            return new Top(n, p.accept(new LtdSubst(i - 1, n, o)));
        } else {
            return new Top(t, p.accept(this));
        }
    }
}

class Subst implements PieVisitor {
    Object n;
    Object o;

    public Subst(Object n, Object o) {
        this.n = n;
        this.o = o;
    }

    public Pie forBot() {
        return new Bot();
    }

    public Pie forTop(Pie pie) {
        Pie p = ((Top) pie).p;
        Object t = ((Top) pie).t;
        if (o.equals(t)) {
            return new Top(n, p.accept(this));
        } else {
            return new Top(t, p.accept(this));
        }
    }
}

class Rem implements PieVisitor {
    Object o;

    public Rem(Object o) {
        this.o = o;
    }

    public Pie forBot() {
        return new Bot();
    }

    public Pie forTop(Pie pie) {
        Pie p = ((Top) pie).p;
        Object t = ((Top) pie).t;
        if (o.equals(t)) {
            return p.accept(this);
        } else {
            return new Top(t, p.accept(this));
        }
    }
}

class Bot implements Pie {
    @Override
    public Pie accept(PieVisitor visitor) {
        return visitor.forBot();
    }

    @Override
    public String toString() {
        return "Bot{}";
    }
}

class Top implements Pie {
    Object t;
    Pie p;

    @Override
    public Pie accept(PieVisitor visitor) {
        return visitor.forTop(this);
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

