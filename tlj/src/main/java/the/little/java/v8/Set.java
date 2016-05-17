package the.little.java.v8;

/**
 * Created by 170182 on 2016/3/22.
 */
public abstract class Set {
    Set add(Integer i) {
        if (mem(i)) {
            return this;
        } else {
            return new Add(i, this);
        }
    }

    abstract boolean mem(Integer i);

    abstract Set plus(Set t);

    abstract Set diff(Set t);

    abstract Set prod(Set t);
}

class Empty extends Set {
    @Override
    Set plus(Set t) {
        return t;
    }

    @Override
    Set diff(Set t) {
        return new Empty();
    }

    @Override
    Set prod(Set t) {
        return new Empty();
    }

    @Override
    boolean mem(Integer i) {
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Empty{");
        sb.append('}');
        return sb.toString();
    }
}

class Add extends Set {
    Integer i;
    Set s;

    @Override
    boolean mem(Integer n) {
        return i .equals(n) || s.mem(n);
    }

    @Override
    Set plus(Set t) {
        return s.plus(t.add(i));
    }

    @Override
    Set diff(Set t) {
        if (t.mem(i)) {
            return s.diff(t);
        } else {
            return s.diff(t).add(i);
        }
    }

    @Override
    Set prod(Set t) {
        if (t.mem(i)) {
            return s.prod(t).add(i);
        } else {
            return s.prod(t);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Add{");
        sb.append("i=").append(i);
        sb.append(", s=").append(s);
        sb.append('}');
        return sb.toString();
    }

    public Add(Integer i, Set s) {
        this.i = i;
        this.s = s;
    }
}
