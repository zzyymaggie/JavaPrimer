package xyz.zzyymaggie.algorithm.self.coins;

/**
 * @link https://www.icode9.com/content-4-450562.html
 */
public class CoinChange3 {

    public static int count(int amount, int[] coins) {
        int len = coins.length;
        int[][] table = new int[len + 1][amount + 1];
        for (int i = 0; i < (len + 1); i++) {
            table[i][0] = 1;
            for (int j = 1; j < (amount + 1); j++) {
                table[i][j] = 0;
            }
        }
        for (int i = 1; i < (len + 1); i++) {
            for (int j = 1; j < amount + 1; j++) {
                table[i][j] += table[i - 1][j]; //不包含物品i的组合数
                if ((j - coins[i - 1]) >= 0) { //如果还能放，将物品i放进去
                    table[i][j] += table[i][j - coins[i - 1]];
                }
            }
        }
        return table[len][amount];
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[] coins = new int[]{1, 2, 5, 10, 20, 50, 100};
        System.out.println(count(200, coins));
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间：" + (endTime - startTime) + "ms");
    }
}
