package xyz.zzyymaggie.java.primer.common;

public class StringTest {
    public static void main(String[] args) {
        stringTest();
        System.out.println("===================stringToCharTest=================");
        stringToCharTest();
        System.out.println("===================charTest=================");
        charTest();
    }

    public static void stringTest() {
        String str1 = "abc";
        String str = new String("abc");
        System.out.println(str1 == str);
        String str2 = "abc";
        System.out.println(str1 == str2);
    }

    public static void stringToCharTest() {
        String str1 = "abc";
        System.out.println(str1.charAt(0));
        char[] chars = str1.toCharArray();
        for(int i=0;i<chars.length;i++) {
            System.out.print(chars[i] + " ");
        }
        System.out.println("");
        String s = String.valueOf('c'); //效率最高的方法
        String s1 = String.valueOf(new char[]{'c'}); //将一个char数组转换成String
        String s2 = Character.toString('c');
        System.out.println(s);
    }

    public static void charTest() {
        int x = 'a';//不会产生编译错误，因为'a'赋给x是隐式转换
        System.out.println(x);
        char ch = (char)x;//会产生编译错误，因为x类型比ch优先级高，必须强制类型转换，但是在C语言中这样是可以的
        System.out.println(ch);

        char ch2=97;//不会产生编译错误，
        //当ch2等于0-127中任意一个整形数据的时候都不会出错，但是超过这个范围就会出错。
        System.out.println(ch2);
    }
}
