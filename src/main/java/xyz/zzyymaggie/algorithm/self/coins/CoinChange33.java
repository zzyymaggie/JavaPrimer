package xyz.zzyymaggie.algorithm.self.coins;

/**
 * @link https://blog.csdn.net/qq_33619378/article/details/82748576 方法二
 */
public class CoinChange33 {

    public static void test2(int n) {
        //纸币面额
        int money[] = {1, 2, 5, 10, 20, 50, 100};
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < 7; ++i) {
            for (int j = money[i]; j <= n; ++j) {
                dp[j] = (dp[j] + dp[j - money[i]]);
            }
        }
        System.out.println(dp[n]);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //指定200元的金额
        test2(200);
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "ms");
    }
}

