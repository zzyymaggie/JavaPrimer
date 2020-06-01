package xyz.zzyymaggie.algorithm.self.coins;

/**
 * 显示所有零钱组合的个数
 */
public class CoinChange2 {

    int count( int S[], int m, int n )
    {
        int i, j, x, y;

        // We need n+1 rows as the table is consturcted in bottom up manner using
        // the base case 0 value case (n = 0)
        int[][] table = new int[n+1][m];

        // Fill the enteries for 0 value case (n = 0)
        for (i=0; i<m; i++)
            table[0][i] = 1;

        // Fill rest of the table enteries in bottom up manner
        for (i = 1; i < n+1; i++)
        {
            for (j = 0; j < m; j++)
            {
                // Count of solutions including S[j]
                x = (i-S[j] >= 0)? table[i - S[j]][j]: 0;

                // Count of solutions excluding S[j]
                y = (j >= 1)? table[i][j-1]: 0;

                // total count
                table[i][j] = x + y;
            }
        }
        return table[n][m-1];
    }

    public static void main(String[] args) {
        CoinChange2 coinChange = new CoinChange2();
        int[] coins = new int[]{1, 2, 5, 10, 20, 50, 100};
        System.out.println(coinChange.count(coins, coins.length,259));
    }
}
