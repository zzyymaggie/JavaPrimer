package xyz.zzyymaggie.algorithm.self;

public class CuttingSteal2 {
    public int cut(int n) {
        int[] r = new int[n + 1];
        int[] p = new int[n + 1];
        /**
         * 初始化 int[] p
         * 长度	=	0	1	2	3	4	5	6	7	8	9	10
         * 价格	=	0	1	5	8	9	10	17	17	20	24	30
         * **/
        if (n < 11) p = new int[11];
        p[0] = 0;
        p[1] = 1;
        p[2] = 5;
        p[3] = 8;
        p[4] = 9;
        p[5] = 10;
        p[6] = 17;
        p[7] = 17;
        p[8] = 20;
        p[9] = 24;
        p[10] = 30;

        if (n == 0) {
            return 0;
        }
        for (int i = 0; i < r.length; i++) {
            r[i] = -1;
        }
        return cut_max(p, n, r);
    }

    private int cut_max(int[] p, int n, int[] r) {
        if (n == 0) {
            return 0;
        }
        if (r[n] >= 0) {
            return r[n];
        }
        int q = -1;
        for (int i = 1; i < n + 1; i++) {
            q = max(q, p[i] + cut_max(p, n - i, r));
        }
        r[n] = q;
        return q;
    }

    private int max(int a, int b) {
        if (a > b) return a;
        return b;
    }

    public static void main(String[] args) {
        CuttingSteal2 cuttingSteal2 = new CuttingSteal2();
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + " 最大收益：" + cuttingSteal2.cut(i));
        }
    }
}
