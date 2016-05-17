package the.little.java.v3;

/**
 * Created by 170182 on 2016/3/19.
 */
public abstract class Fish {
}

class Anchovy extends Fish{
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Anchovy;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Anchovy{");
        sb.append('}');
        return sb.toString();
    }
}

class Salmon extends Fish{
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Salmon;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Salmon{");
        sb.append('}');
        return sb.toString();
    }
}
class Tuna extends Fish{
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tuna{");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Tuna;
    }
}
