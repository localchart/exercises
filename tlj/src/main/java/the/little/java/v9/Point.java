package the.little.java.v9;

/**
 * Created by 170182 on 2016/3/23.
 */
public abstract class Point {
    final int x;
    final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean closerToO(Point p) {
        return distanceTo0() <= p.distanceTo0();
    }

    Point minus(Point p) {
        return new CartesianPt(x - p.x, y - p.y);
    }

    abstract int distanceTo0();
}

class CartesianPt extends Point {
    public CartesianPt(int x, int y) {
        super(x, y);
    }

    @Override
    int distanceTo0() {
        return (int) Math.hypot((double) x, (double) y);
    }

    @Override
    public String toString() {
        return "CartesianPt{} " + super.toString();
    }
}

class ManhattanPt extends Point {
    public ManhattanPt(int x, int y) {
        super(x, y);
    }

    @Override
    int distanceTo0() {
        return x + y;
    }

    @Override
    public String toString() {
        return "ManhattanPt{} " + super.toString();
    }
}

class ShadowedManhattanPt extends ManhattanPt {
    int xp;
    int yp;

    public ShadowedManhattanPt(int x, int y, int xp, int yp) {
        super(x, y);
        this.xp = xp;
        this.yp = yp;
    }

    @Override
    int distanceTo0() {
        return super.distanceTo0() + xp + yp;
    }

    @Override
    public String toString() {
        return "ShadowedManhattanPt{" +
                "xp=" + xp +
                ", yp=" + yp +
                "} " + super.toString();
    }
}

class ShadowedCartesianPt extends CartesianPt {
    int xp;
    int yp;

    public ShadowedCartesianPt(int x, int y, int xp, int yp) {
        super(x, y);
        this.xp = xp;
        this.yp = yp;
    }

    @Override
    int distanceTo0() {
        return new CartesianPt(x + xp, y + yp).distanceTo0();
    }

    @Override
    public String toString() {
        return "ShadowedCartesianPt{" +
                "xp=" + xp +
                ", yp=" + yp +
                "} " + super.toString();
    }
}