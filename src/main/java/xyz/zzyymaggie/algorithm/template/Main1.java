package xyz.zzyymaggie.algorithm.template;

import java.util.*;
public class Main1 {
    public static int lengthOfLast(String str) {
        String[] s =str.split(" ");
        return s[s.length-1].length();
    }
     
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String str = scan.nextLine();
            System.out.println(lengthOfLast(str));
        }
    }
}

/**
 * 模拟多行输入数字
 * import java.util.Scanner;
 * public class Main1 {
 *     public static void main(String[] args) {
 *         Scanner in = new Scanner(System.in);
 *         while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
 *             int a = in.nextInt();
 *             int b = in.nextInt();
 *             System.out.println(a + b);
 *         }
 *     }
 * }
 */
/** 模拟多行输入
public static void main(String[] args) {
	 for(int i=0;i<2;i++){
		 Scanner scan=new Scanner(System.in);
		 Scanner scan1=new Scanner(scan.nextLine());
		while(scan1.hasNext()){
			 int str1=scan1.nextInt();
			 System.out.print("输出数据"+str1);
		 }
	 }
 }
}
**/