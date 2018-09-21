package xyz.zzyymaggie.java.primer.random;


/**
 * 要获取一个[x,y]的int类型的随机数 | 左闭右闭
 */
public class RandomDemo {
    public static void main(String[] args) {
        double x = 7.5;
        double y = 12.5;
        double d = x + Math.random() * y % (y - x + 1);
        System.out.println(d);

    }
}
