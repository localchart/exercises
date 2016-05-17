package the.little.java.v9;

/**
 * Created by 170182 on 2016/3/23.
 */
public abstract class Shape {
    abstract boolean accept(ShapeVisitor ask);
}

class Circle extends Shape {
    final int r;

    public Circle(int r) {
        this.r = r;
    }

    @Override
    boolean accept(ShapeVisitor ask) {
        return ask.forCircle(r);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "r=" + r +
                "} " + super.toString();
    }
}

class Union extends Shape {
    final Shape s;
    final Shape t;

    public Union(Shape s, Shape t) {
        this.s = s;
        this.t = t;
    }

    @Override
    boolean accept(ShapeVisitor ask) {
        return ((UnionVisitor) ask).forUnion(s, t);
    }
}

class Square extends Shape {
    final int s;

    public Square(int s) {
        this.s = s;
    }

    @Override
    boolean accept(ShapeVisitor ask) {
        return ask.forSquare(s);
    }

    @Override
    public String toString() {
        return "Square{" +
                "s=" + s +
                "} " + super.toString();
    }
}

class Trans extends Shape {
    Point q;
    Shape s;

    public Trans(Point q, Shape s) {
        this.q = q;
        this.s = s;
    }

    @Override
    boolean accept(ShapeVisitor ask) {
        return ask.forTrans(q, s);
    }

    @Override
    public String toString() {
        return "Trans{" +
                "q=" + q +
                ", s=" + s +
                "} " + super.toString();
    }
}
