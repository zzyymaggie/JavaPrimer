package xyz.zzyymaggie.algorithm.self;

/**
 * 26进制与十进制互转：0:a, 1:b; 2:c....;25:z
 */
public class NumberUtil {
    // 主函数
    public static void main(String[] args) {
        String numStr = tranString(713);
        System.out.println("713 =>" + numStr);
        int number = trans("bbb");
        System.out.println("bbb =>" + number);
    }

    public static int trans(String str) {
        int count = 0;
        for (int i = str.length() - 1, j = 1; i >= 0; i--, j *= 26){
            char c = str.charAt(i);
            if (c < 'a' || c > 'z') return 0;
            count += ((int)c - 'a') * j;
        }
        return count;
    }

    public static String tranString(int n) {
        String s = "";
        while (n > 0){
            int m = n % 26;
            s = (char)(m + 'a') + s;
            n = (n - m) / 26;
        }
        return s;
    }
}
