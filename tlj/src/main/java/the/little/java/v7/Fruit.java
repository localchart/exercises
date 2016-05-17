package the.little.java.v7;

/**
 * Created by 170182 on 2016/3/21.
 */
public abstract class Fruit {
}

class Peach extends Fruit {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Peach{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Peach;
    }
}

class Apple extends Fruit {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Apple{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Apple;
    }
}

class Pear extends Fruit {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pear{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Pear;
    }
}

class Lemon extends Fruit {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lemon{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Lemon;
    }
}

class Fig extends Fruit {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fig{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Fig;
    }
}
