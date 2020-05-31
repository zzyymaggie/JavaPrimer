package xyz.zzyymaggie.algorithm.dp;

/**
 * 采用自顶向下解法
 **/
public class CuttingSteel {

    private static int[] r = new int[11];//最大收益备忘录
    private static int[] p = new int[11]; //定义价格

    /**
     * 切割钢筋备忘录
     * int[] p 官方定义的价格
     * int n 表示n节长的钢筋
     **/
    public int MEMOIZEDP_CUT_ROD(int n) {
        r = new int[n + 1];
        p = new int[n + 1];
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

        /**
         * 初始化 int[]
         * **/
        for (int i = 0; i < r.length; i++) {
            r[i] = -1;
        }
        return MEMOIZED_CUT_ROD_AUX(p, n, r);
    }

    /**
     * 获取切割钢筋最大收益
     * 参数说明:
     * int[] p 官方定义价格
     * int n 当前钢筋节数
     * int r[] 最大收益备忘录
     **/
    private int MEMOIZED_CUT_ROD_AUX(int[] p, int n, int r[]) {
        int q = -1; //最大值
        if (r[n] >= 0) return r[n];
        if (n == 0) {
            q = 0;
        } else if (q == -1) {
            for (int i = 1; i <= n; i++) {
                q = max(q, p[i] + MEMOIZED_CUT_ROD_AUX(p, n - i, r));
            }
        }
        r[n] = q; //缓存最大收益
        return q;
    }

    /**
     * 返回两个数中最大的值
     **/
    private int max(int a, int b) {
        return Math.max(a, b);
    }

    public static void main(String[] args) {
        CuttingSteel cuttingSteel = new CuttingSteel();
        for (int i = 0; i <= 50; i++) {
            int maxProfit = cuttingSteel.MEMOIZEDP_CUT_ROD(i);
            System.out.println(i + "节钢筋的最大利益为 : " + maxProfit);
        }
    }
}
