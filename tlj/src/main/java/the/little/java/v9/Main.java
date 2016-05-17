package the.little.java.v9;

/**
 * Created by 170182 on 2016/3/23.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Circle(10).accept(new HasPtV(new CartesianPt(10, 10))));
        System.out.println(new Square(10).accept(new HasPtV(new CartesianPt(10, 10))));
        System.out.println(new Trans(new CartesianPt(5, 6), new Circle(10)).accept(new HasPtV(new CartesianPt(10, 10))));
        System.out.println(new Trans(new CartesianPt(5, 4), new Trans(new CartesianPt(5, 4), new Circle(10))).accept(new HasPtV(new CartesianPt(10, 10))));

        boolean accept = new Trans(new CartesianPt(3, 7),
                new Union(new Square(10), new Circle(10))).accept(new UnionHasPtV(new CartesianPt(13, 17)));
        System.out.println(accept);
    }
}
