package the.little.java.v4;

/**
 * Created by 170182 on 2016/3/19.
 */
public abstract class Num {
}

class OneMoreThan extends Num {
    Num n;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OneMoreThan{");
        sb.append("n=").append(n);
        sb.append('}');
        return sb.toString();
    }

    public OneMoreThan(Num n) {
        this.n = n;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OneMoreThan ? n.equals(((OneMoreThan) obj).n) : false;
    }
}

class Zero extends Num {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Zero{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Zero;
    }
}
