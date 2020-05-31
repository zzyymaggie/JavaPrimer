package xyz.zzyymaggie.algorithm.self;

/**
 * Excel数字和编号互转
 * 26进制的变种 1:a,2:b;3:c...;26:z
 * 26进制：0:a, 1:b; 2:c....;25:z
 */
public class ExcelNumberUtil {
    // 主函数
    public static void main(String[] args) {
        String numStr = tranString(703);
        System.out.println("703 =>" + numStr);
        int number = trans("aaa");
        System.out.println("aaa =>" + number);
    }

    public static int trans(String str) {
        int count = 0;
        for (int i = str.length() - 1, j = 1; i >= 0; i--, j *= 26){
            char c = str.charAt(i);
            if (c < 'a' || c > 'z') return 0;
            count += ((int)c - ('a' - 1)) * j;
        }
        return count;
    }

    public static String tranString(int n) {
        String s = "";
        while (n > 0){
            int m = n % 26;
            s = (char)(m + 'a' - 1) + s;
            n = (n - m) / 26;
        }
        return s;
    }
}