package the.little.java.v7;

import java.util.HashMap;

/**
 * Created by 170182 on 2016/3/21.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(11111111);
        System.out.println(new Bud().accept(new blsFlat()));//true
        boolean b = new Split(
                new Split(
                        new Bud(),
                        new Split(new Bud(), new Bud())),
                new Split(
                        new Bud(),
                        new Split(new Bud(), new Bud()))).accept(new blsSplit());
        System.out.println(b); //true
        System.out.println("--------------");
        int i = new Split(new Bud(), new Bud()).accept(new iHeight());
        System.out.println(i); //1
        i = new Split(
                new Split(
                        new Flat(new Fig(), new Bud()),
                        new Flat(new Fig(), new Bud())),
                new Flat(new Fig(), new Flat(new Lemon(), new Flat(new Apple(), new Bud())))
        ).accept(new iHeight());
        System.out.println(i); //4
        i = new Split(
                new Split(new Bud(), new Bud()),
                new Flat(new Fig(), new Flat(new Lemon(), new Flat(new Apple(), new Bud())))
        ).accept(new iHeight());
        System.out.println(i); //4
        System.out.println("--------------");
        Tree t = new Split(
                new Split(new Flat(new Fig(), new Bud()), new Flat(new Fig(), new Bud())),
                new Flat(new Fig(), new Flat(new Lemon(), new Flat(new Apple(), new Bud()))));

        //Split{l=Split{l=Flat{f=Fig{}, t=Bud{}}, r=Flat{f=Fig{}, t=Bud{}}}, r=Flat{f=Fig{}, t=Flat{f=Lemon{}, t=Flat{f=Apple{}, t=Bud{}}}}}
        System.out.println(t);
        //Split{l=Split{l=Flat{f=Apple{}, t=Bud{}}, r=Flat{f=Apple{}, t=Bud{}}}, r=Flat{f=Apple{}, t=Flat{f=Lemon{}, t=Flat{f=Apple{}, t=Bud{}}}}}
        System.out.println(t.accept(new tSubst(new Apple(), new Fig())));
        System.out.println("--------------");
        t = new Split(
                new Split(new Flat(new Fig(), new Bud()), new Flat(new Fig(), new Flat(new Pear(), new Bud()))),
                new Flat(new Pear(), new Flat(new Lemon(), new Flat(new Peach(), new Flat(new Apple(), new Bud())))));
        //Split{l=Split{l=Flat{f=Fig{}, t=Bud{}}, r=Flat{f=Fig{}, t=Flat{f=Pear{}, t=Bud{}}}}, r=Flat{f=Pear{}, t=Flat{f=Lemon{}, t=Flat{f=Peach{}, t=Flat{f=Apple{}, t=Bud{}}}}}}
        System.out.println(t);
        System.out.println(t.accept(new mOccurs(new HashMap<String, Integer>())));//{Apple=1, Pear=2, Fig=2, Lemon=1, Peach=1}
        System.out.println("--------------");
        t = new Split(
                new Split(new Flat(new Fig(), new Bud()), new Flat(new Fig(), new Bud())),
                new Flat(new Fig(), new Flat(new Lemon(), new Flat(new Apple(), new Bud()))));
        // Split{l=Split{l=Flat{f=Fig{}, t=Bud{}}, r=Flat{f=Fig{}, t=Bud{}}}, r=Flat{f=Fig{}, t=Flat{f=Lemon{}, t=Flat{f=Apple{}, t=Bud{}}}}}
        System.out.println(t);
        System.out.println(t.accept(new iOccurs(new Fig())));//3
    }
}
